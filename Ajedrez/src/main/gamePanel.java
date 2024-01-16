package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import piece.*;

public class gamePanel extends JPanel implements Runnable {

	public static final int WIDTH = 1100;
	public static final int HEIGHT = 800;
	final int FPS = 60;
	Thread gameThread;
	Board board = new Board();
	Mouse mouse = new Mouse();

	public static ArrayList<Pieces> pieces = new ArrayList<>();
	public static ArrayList<Pieces> simPieces = new ArrayList<>();

	Pieces activeP,revisionDeJaque;

	public static final int WHITE = 0;
	public static final int BLACK = 1;
	int currentColor = WHITE;

	boolean canMove;
	boolean validSquare;
	boolean gameOver;

	public gamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.black);

		addMouseMotionListener(mouse);
		addMouseListener(mouse);
	}

	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
		setPieces();
		copyPieces(pieces, simPieces);
	}

	public void setPieces() {

		pieces.add(new Peon(WHITE, 0, 6));
		pieces.add(new Peon(WHITE, 1, 6));
		pieces.add(new Peon(WHITE, 2, 6));
		pieces.add(new Peon(WHITE, 3, 6));
		pieces.add(new Peon(WHITE, 4, 6));
		pieces.add(new Peon(WHITE, 5, 6));
		pieces.add(new Peon(WHITE, 6, 6));
		pieces.add(new Peon(WHITE, 7, 6));
		pieces.add(new Torre(WHITE, 0, 7));
		pieces.add(new Torre(WHITE, 7, 7));
		pieces.add(new Caballo(WHITE, 1, 7));
		pieces.add(new Caballo(WHITE, 6, 7));
		pieces.add(new Alfil(WHITE, 2, 7));
		pieces.add(new Alfil(WHITE, 5, 7));
		pieces.add(new Reina(WHITE, 3, 7));
		pieces.add(new Rey(WHITE, 4, 4));

		pieces.add(new Peon(BLACK, 0, 1));
		pieces.add(new Peon(BLACK, 1, 1));
		pieces.add(new Peon(BLACK, 2, 1));
		pieces.add(new Peon(BLACK, 3, 1));
		pieces.add(new Peon(BLACK, 4, 1));
		pieces.add(new Peon(BLACK, 5, 1));
		pieces.add(new Peon(BLACK, 6, 1));
		pieces.add(new Peon(BLACK, 7, 1));
		pieces.add(new Torre(BLACK, 0, 0));
		pieces.add(new Torre(BLACK, 7, 0));
		pieces.add(new Caballo(BLACK, 1, 0));
		pieces.add(new Caballo(BLACK, 6, 0));
		pieces.add(new Alfil(BLACK, 2, 0));
		pieces.add(new Alfil(BLACK, 5, 0));
		pieces.add(new Reina(BLACK, 3, 0));
		pieces.add(new Rey(BLACK, 4, 0));

	}

	private void copyPieces(ArrayList<Pieces> source, ArrayList<Pieces> target) {

		target.clear();
		for (int i = 0; i < source.size(); i++) {
			target.add(source.get(i));
		}
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta <= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {

		if (mouse.pressed) {
			if (activeP == null) {
				for (Pieces pieces : simPieces) {
					if (pieces.color == currentColor && pieces.col == mouse.x / Board.SQUARE_SIZE
							&& pieces.row == mouse.y / Board.SQUARE_SIZE) {

						activeP = pieces;
					}
				}
			} else {
				simulate();
			}
		}

		if (mouse.pressed == false) {
			if (activeP != null) {
				if (validSquare) {

					copyPieces(simPieces, pieces);
					activeP.updatePosition();

					
					if(reyEnJaque()) {
						//TODO: posible jaque
					}
					changePlayer();

				} else {
					copyPieces(simPieces, pieces);
					activeP.resetPosition();

				}
				activeP = null;
			}
		}
	}

	private void simulate() {
		canMove = false;
		validSquare = false;

		copyPieces(simPieces, pieces);

		if (activeP != null) {
			activeP.x = mouse.x - Board.HALF_SQUARE_SIZE;
			activeP.y = mouse.y - Board.HALF_SQUARE_SIZE;

			activeP.col = activeP.getCol(activeP.x);
			activeP.row = activeP.getRow(activeP.y);

			if (activeP.canMove(activeP.col, activeP.row)) {

				canMove = true;

				if (activeP.superPonerP != null) {
					simPieces.remove(activeP.superPonerP.getIndex());
				}

				if (ilegal(activeP) == false && oponentePuedeCapturarRey() == false) {

					validSquare = true;
				}
			}
		}
	}

	private boolean ilegal(Pieces rey) {

		if (rey.type == Type.REY) {
			for (Pieces pieces : simPieces) {
				if (pieces != rey && pieces.color != rey.color && pieces.canMove(rey.col, rey.row)) {
					return true;
				}
			}
		}

		return false;
	}
	
	private boolean oponentePuedeCapturarRey() {
		
		Pieces rey = getKing(false);
		
		for(Pieces pieces : simPieces) {
			if(pieces.color != rey.color && pieces.canMove(rey.col, rey.row)) {
				return true;
			}
		}
		
		return false;
	}
	
	private Pieces getKing(boolean opponent) {
		
		Pieces rey = null;
		
		for(Pieces pieces : simPieces) {
			if(opponent) {
				if(pieces.type == Type.REY && pieces.color != currentColor) {
					rey = pieces;
				}
			}else {
				if(pieces.type == Type.REY && pieces.color == currentColor) {
					rey = pieces;
				}
			}
		}
		return rey;
	}
	
	private boolean reyEnJaque() {
		
		Pieces rey  = getKing(true);
		
		if(activeP.canMove(rey.col,rey.row)) {
			revisionDeJaque = activeP;
			return true;
		}else {
			revisionDeJaque = null;
		}
		
		return false;
	}

	public void changePlayer() {

		if (currentColor == WHITE) {
			currentColor = BLACK;
		} else {
			currentColor = WHITE;
		}
		activeP = null;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		board.draw(g2);

		for (Pieces p : simPieces) {
			p.draw(g2);
		}

		if (activeP != null) {
			if (canMove) {
				if(ilegal(activeP) || oponentePuedeCapturarRey()) {
					g2.setColor(Color.gray);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
					g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE,
							Board.SQUARE_SIZE);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}else {
					g2.setColor(Color.white);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
					g2.fillRect(activeP.col * Board.SQUARE_SIZE, activeP.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE,
							Board.SQUARE_SIZE);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				}
			}

			activeP.draw(g2);
		}

		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setFont(new Font("Book Antiqua", Font.PLAIN, 20));
		g2.setColor(Color.white);

		if (currentColor == WHITE) {
			g2.drawString("Turno del blanco", 840, 550);
			if(revisionDeJaque != null && revisionDeJaque.color == BLACK) {
				g2.setColor(Color.red);
				g2.drawString("El REY", 840, 650);
				g2.drawString("esta en JAQUE", 840, 700);
			}
		} else {
			g2.drawString("Turno del negro", 840, 250);
			if(revisionDeJaque != null && revisionDeJaque.color == WHITE) {
				g2.setColor(Color.red);
				g2.drawString("El REY", 840, 100);
				g2.drawString("esta en JAQUE", 840, 150);
			}

		}

	}

}

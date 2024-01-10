package main;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;


import piece.*;

public class gamePanel extends JPanel implements Runnable {
	
	public static  final int WIDTH = 800;
	public static final int HEIGHT = 800;
	final int FPS = 60;
	Thread gameThread;
	Board board = new Board();
	
	public static ArrayList<Pieces> pieces =new ArrayList<>();
	public static ArrayList<Pieces> simPieces = new ArrayList<>();
	
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	int currentColor= WHITE;
	
	public gamePanel() {
		setPreferredSize(new Dimension (WIDTH,HEIGHT));
		setBackground(Color.black);
	}
	
	public void launchGame() {
		gameThread = new Thread(this);
		gameThread.start();
		setPieces();
		copyPieces(pieces, simPieces);
	}
	
	public void setPieces() {
		
		pieces.add(new Peon(WHITE,0,6));
		pieces.add(new Peon(WHITE,1,6));
		pieces.add(new Peon(WHITE,2,6));
		pieces.add(new Peon(WHITE,3,6));
		pieces.add(new Peon(WHITE,4,6));
		pieces.add(new Peon(WHITE,5,6));
		pieces.add(new Peon(WHITE,6,6));
		pieces.add(new Peon(WHITE,7,6));
		pieces.add(new Torre(WHITE,0,7));
		pieces.add(new Torre(WHITE,7,7));
		pieces.add(new Caballo(WHITE,1,7));
		pieces.add(new Caballo(WHITE,6,7));
		pieces.add(new Alfil(WHITE,2,7));
		pieces.add(new Alfil(WHITE,5,7));
		pieces.add(new Reina(WHITE,3,7));
		pieces.add(new Rey(WHITE,4,7));

	}
	
	private void copyPieces(ArrayList<Pieces> source, ArrayList<Pieces> target) {
		
		target.clear();
		for(int i =0;i<source.size();i++) {
			target.add(source.get(i));
		}
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime  = System.nanoTime();
			
			delta += (currentTime - lastTime)/	drawInterval;
			lastTime = currentTime;
			
			if(delta<= 	1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	
	public void update() {
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		board.draw(g2);
		
		for(Pieces p : simPieces) {
			p.draw(g2);
		}
		
	}


}

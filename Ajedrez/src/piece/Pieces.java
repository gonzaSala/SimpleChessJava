package piece;

import java.awt.Graphics2D;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Board;
import main.Type;
import main.gamePanel;

public class Pieces {

	public Type type;

	public BufferedImage image;
	public int x, y;
	public int col, row, preCol, preRow;
	public int color;
	public Pieces superPonerP;
	public boolean moved;

	public Pieces(int color, int col, int row) {

		this.color = color;
		this.col = col;
		this.row = row;

		x = getX(col);
		y = getY(row);
		preCol = col;
		preRow = row;
	}

	public BufferedImage getImage(String imagePath) {

		BufferedImage image = null;
		try {

			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return image;
	}

	public int getX(int col) {
		return col * Board.SQUARE_SIZE;
	}

	public int getY(int row) {
		return row * Board.SQUARE_SIZE;
	}

	public int getCol(int x) {
		return (x + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
	}

	public int getRow(int y) {
		return (y + Board.HALF_SQUARE_SIZE) / Board.SQUARE_SIZE;
	}

	public void updatePosition() {

		x = getX(col);
		y = getY(row);
		preCol = getCol(x);
		preRow = getRow(y);
		
		moved= true;
	}

	public void resetPosition() {
		col = preCol;
		row = preRow;
		x = getX(col);
		y = getY(row);
	}

	public int getIndex() {
		for (int i = 0; i < gamePanel.simPieces.size(); i++) {
			if (gamePanel.simPieces.get(i) == this) {
				return i;
			}
		}

		return 0;
	}

	public boolean canMove(int targetCol, int targetRow) {
		return false;
	}

	public boolean isWhitinBoard(int targetCol, int targetRow) {
		if (targetCol >= 0 && targetCol <= 7 && targetRow > 0 && targetRow <= 7) {
			return true;
		}
		return false;
	}

	public boolean isSameSquare(int targetCol, int targetRow) {
		if (targetCol == preCol && targetRow == preRow) {
			return true;
		}

		return false;
	}

	public Pieces getSuperPonerP(int targetCol, int targetRow) {
		for (Pieces piece : gamePanel.simPieces) {
			if (piece.col == targetCol && piece.row == targetRow && piece != this) {
				return piece;
			}
		}

		return null;
	}

	public boolean isValidSquare(int targetCol, int targetRow) {

		superPonerP = getSuperPonerP(targetCol, targetRow);
		if (superPonerP == null) {
			return true;
		} else {
			if (superPonerP.color != this.color) {
				return true;
			} else {
				superPonerP = null;
			}
		}

		return false;
	}

	public boolean piecesEnLaMismaLinea(int targetCol, int targetRow) {
		for (int c = preCol - 1; c > targetCol; c--) {
			for (Pieces pieces : gamePanel.simPieces) {
				if (pieces.col == c && pieces.row == targetRow) {
					superPonerP = pieces;
					return true;
				}
			}
		}

		for (int c = preCol + 1; c < targetCol; c++) {
			for (Pieces pieces : gamePanel.simPieces) {
				if (pieces.col == c && pieces.row == targetRow) {
					superPonerP = pieces;
					return true;
				}
			}
		}

		for (int r = preRow - 1; r > targetRow; r--) {
			for (Pieces pieces : gamePanel.simPieces) {
				if (pieces.col == targetCol && pieces.row == r) {
					superPonerP = pieces;
					return true;
				}
			}
		}

		for (int r = preRow + 1; r < targetRow; r++) {
			for (Pieces pieces : gamePanel.simPieces) {
				if (pieces.col == targetCol && pieces.row == r) {
					superPonerP = pieces;
					return true;
				}
			}
		}

		return false;
	}

	public boolean piecesEnDiagonal(int targetCol, int targetRow) {
		if (targetRow < preRow) {
			for (int c = preCol - 1; c > targetCol; c--) {
				int diferencia = Math.abs(c - preCol);
				for (Pieces pieces : gamePanel.simPieces) {
					if (pieces.col == c && pieces.row == preRow - diferencia) {
						superPonerP = pieces;
						return true;
					}
				}
			}
			for (int c = preCol + 1; c < targetCol; c++) {
				int diferencia = Math.abs(c - preCol);
				for (Pieces pieces : gamePanel.simPieces) {
					if (pieces.col == c && pieces.row == preRow - diferencia) {
						superPonerP = pieces;
						return true;
					}
				}
			}
		}

		if (targetRow > preRow) {
			for (int c = preCol - 1; c > targetCol; c--) {
				int diferencia = Math.abs(c - preCol);
				for (Pieces pieces : gamePanel.simPieces) {
					if (pieces.col == c && pieces.row == preRow + diferencia) {
						superPonerP = pieces;
						return true;
					}
				}
			}
			for (int c = preCol + 1; c < targetCol; c++) {
				int diferencia = Math.abs(c - preCol);
				for (Pieces pieces : gamePanel.simPieces) {
					if (pieces.col == c && pieces.row == preRow + diferencia) {
						superPonerP = pieces;
						return true;
					}
				}
			}
		}

		return false;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
	}

}

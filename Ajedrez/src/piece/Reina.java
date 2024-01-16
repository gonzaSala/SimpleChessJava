package piece;

import main.Type;
import main.gamePanel;

public class Reina extends Pieces{

	public Reina(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.REINA;

		
		if(color==gamePanel.WHITE) {
			image = getImage("/piece/w-reina");
		}else {
			image = getImage("/piece/b-reina");

		}
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWhitinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false) {
			if(targetCol == col || targetRow ==row) {
				if(isValidSquare(targetCol,targetRow) && piecesEnLaMismaLinea(targetCol,targetRow) == false) {
					return true;
				}
			}
			
			if(Math.abs(targetCol-preCol) == Math.abs(targetRow - preRow) && piecesEnDiagonal(targetCol, targetRow)==false) {
				return true;
			}
		}
		
		return false;
	}
	

}

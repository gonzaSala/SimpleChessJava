package piece;

import main.Type;
import main.gamePanel;

public class Peon extends Pieces {

	public Peon(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.PEON;

		
		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-peon");
		}else {
			image = getImage("/piece/b-peon");

		}
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWhitinBoard(targetCol,targetRow) && isSameSquare(targetCol,targetRow) == false) {
			int moveValue;
			if(color==gamePanel.WHITE) {
				moveValue = -1;
			}else {
				moveValue= 1;
			}
			
			superPonerP = getSuperPonerP(targetCol,targetRow);
			
			if(targetCol == preCol && targetRow == preRow + moveValue && superPonerP == null) {
				return true;
			}
			
			if(targetCol == preCol && targetRow == preRow + moveValue *2 && superPonerP == null && moved == false
				&& piecesEnLaMismaLinea(targetCol,targetRow)== false) {
				return true;
			}
			
			if(Math.abs(targetCol - preCol )== 1 && targetRow == preRow + moveValue && superPonerP != null
					&& superPonerP.color != color) {
				return true;
			}
				
			
		}
		return false;
	}

}

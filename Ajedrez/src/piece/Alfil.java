package piece;

import main.gamePanel;
import main.Type;

public class Alfil extends Pieces{

	public Alfil(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.ALFIL;

		if(color== gamePanel.WHITE){
			image = getImage("/piece/w-alfil");
		}else {
			image = getImage("/piece/b-alfil");

		}
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWhitinBoard(targetCol, targetRow) && isSameSquare(targetCol,targetRow) == false) {
			if(Math.abs(targetCol - preCol) == Math.abs(targetRow - preRow)) {
				if(isValidSquare(targetCol, targetRow) && piecesEnDiagonal(targetCol,targetRow) == false) {
					return true;
				}
			}
		}
		
		return false;
	}

}

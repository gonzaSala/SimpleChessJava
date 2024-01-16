package piece;

import main.Type;
import main.gamePanel;

public class Torre extends Pieces {

	public Torre(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.TORRE;

		
		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-torre");
		}else {
			image = getImage("/piece/b-torre");

		}
	
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWhitinBoard(targetCol,targetRow) && isSameSquare(targetCol, targetRow)) {
			if(targetCol == preCol || targetRow == preRow) {
				if(isValidSquare(targetCol,targetRow) && piecesEnLaMismaLinea(targetCol,targetRow) == false) {
					return true;
				}
			}
		}
		
		return false;
	}

}

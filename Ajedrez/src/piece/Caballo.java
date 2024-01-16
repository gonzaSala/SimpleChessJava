package piece;

import main.Type;
import main.gamePanel;

public class Caballo extends Pieces {

	public Caballo(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.CABALLO;

		if(color == gamePanel.WHITE) {
			image= getImage("/piece/w-caballo");
		}else {
			image = getImage("/piece/b-caballo");

		}
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		if(isWhitinBoard(targetCol,targetRow)) {
			if(Math.abs(targetCol- preCol) * Math.abs(targetRow-preRow) ==2) {
				if(isValidSquare(targetCol, targetRow)) {
					return true;
				}
			}
		}
		
		return false;
	}

}

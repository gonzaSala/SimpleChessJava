package piece;

import main.Type;
import main.gamePanel;

public class Rey extends Pieces{

	public Rey(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.REY;


		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-rey");
		}else {
			image = getImage("/piece/b-rey");

		}
	
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		
		if (isWhitinBoard(targetCol,targetRow)) {
			
			if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow)==1 ||
					Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1) {
				
				if(isValidSquare(targetCol,targetRow)) {
					
					return true;
				}
			}
		}
		return false;
	}
	
	

}

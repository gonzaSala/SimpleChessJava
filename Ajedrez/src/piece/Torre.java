package piece;

import main.gamePanel;

public class Torre extends Pieces {

	public Torre(int color, int col, int row) {
		super(color, col, row);
		
		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-torre");
		}
	
	
	}

}

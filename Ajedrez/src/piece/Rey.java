package piece;

import main.gamePanel;

public class Rey extends Pieces{

	public Rey(int color, int col, int row) {
		super(color, col, row);

		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-rey");
		}
	
	}

}

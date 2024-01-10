package piece;

import main.gamePanel;

public class Peon extends Pieces {

	public Peon(int color, int col, int row) {
		super(color, col, row);
		
		if(color == gamePanel.WHITE) {
			image = getImage("/piece/w-peon");
		}
	}

}

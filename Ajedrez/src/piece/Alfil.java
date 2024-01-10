package piece;

import main.gamePanel;

public class Alfil extends Pieces{

	public Alfil(int color, int col, int row) {
		super(color, col, row);

		if(color== gamePanel.WHITE){
			image = getImage("/piece/w-alfil");
		}
	}

}

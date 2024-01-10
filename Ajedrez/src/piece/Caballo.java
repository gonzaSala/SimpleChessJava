package piece;

import main.gamePanel;

public class Caballo extends Pieces {

	public Caballo(int color, int col, int row) {
		super(color, col, row);

		if(color == gamePanel.WHITE) {
			image= getImage("/piece/w-caballo");
		}
	}

}

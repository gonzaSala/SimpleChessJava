package piece;

import main.gamePanel;

public class Reina extends Pieces{

	public Reina(int color, int col, int row) {
		super(color, col, row);
		
		if(color==gamePanel.WHITE) {
			image = getImage("/piece/w-reina");
		}
	}
	
	

}

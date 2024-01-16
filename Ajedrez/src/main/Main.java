package main;

import javax.swing.*;

public class Main {
	public static void main (String[]args) {
		
		JFrame window = new JFrame ("Ajedrez");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		gamePanel gp = new gamePanel();
		window.add(gp);
		window.setVisible(true);
		window.pack();
		
		
		window.setLocationRelativeTo(null);
		
		gp.launchGame();
		
		
	}
}

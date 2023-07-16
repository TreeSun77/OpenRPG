package Main;

import javax.imageio.IIOException;
import javax.swing.*;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("RPG OPEN");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);


		gamePanel.startGameTread();
	}
}

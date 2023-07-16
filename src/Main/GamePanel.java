package Main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //768
	final int screenHeight = tileSize * maxScreenRow; // 576

	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	Thread gameTread;
	Player player = new Player(this, keyH);
	//set default player position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;


	public GamePanel() throws IOException {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void startGameTread() {
		this.gameTread = new Thread(this);
		gameTread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameTread != null) {

			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void update() {
	player.update();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D) g;
		player. draw(g2);


		g2.dispose();
	}
}

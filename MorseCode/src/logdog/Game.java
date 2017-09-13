package logdog;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import logdog.graphics.Screen;
import logdog.input.Keyboard;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -5582170548642915241L;
	public static final int WIDTH = 32 * 32, HEIGHT = 16 * 16, SCALE = 1;
	private static String title = "Morse Code";
	private boolean running = false;

	private JFrame frame;
	private Thread thread;
	private Screen screen; // User defined
	private Keyboard key;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(size);
		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT); // User defined
		key = new Keyboard();
		addKeyListener(key);
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("UPS: " + updates + "\tFrames: " + frames);
				updates = 0;
				frames = 0;
			}

		}
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "DISPLAY");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println("Error joining thread in method 'stop'");
			e.printStackTrace();
		}
	}

	int updatesSinceKeyDown = 0;
	int updatesSinceKeyUp = 0;
	String currentCode = "";

	public void update()
	{
		Keyboard.update();
		if(Keyboard.space) {
			updatesSinceKeyDown++;
			updatesSinceKeyUp = 0;
		}
		else {
			updatesSinceKeyDown = 0;
			updatesSinceKeyUp++;
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		screen.renderMorse(Keyboard.currentCode, 0, HEIGHT/2, 0x00ffff);
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.frame.setResizable(false);
		game.frame.setTitle(title);

		game.frame.add(game);
		game.frame.pack();

		game.frame.setLocation(0, 700);
		game.frame.setBackground(Color.black);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);

		game.start();

	}

}

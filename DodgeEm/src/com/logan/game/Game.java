package com.logan.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.logan.game.entities.Block;
import com.logan.game.entities.Player;
import com.logan.game.graphics.Screen;
import com.logan.game.input.Keyboard;
import com.logan.game.input.Mouse;
import com.logan.game.level.Level;
import com.logan.game.screens.Button;
import com.logan.game.screens.UI.UIManager;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean running = false;
	public static final int WIDTH = 400, HEIGHT = WIDTH / 16 * 9, SCALE = 3;
	private static String title = "Dodge EM' v0.0";

	public static int boundrySize = 1000;
	private int dir = 0;
	private JFrame frame;
	private Thread thread;
	private Screen screen;
	private Random rand;
	private Keyboard key;
	private Level level;
	public static Player player;
	public static int deltaX, deltaY;
	private Mouse mouse;
	public UIManager ui;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);

		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT);
		rand = new Random();
		player = new Player(WIDTH / 2, HEIGHT / 2);
		key = new Keyboard();
		level = new Level();
		mouse = new Mouse();
		ui = new UIManager();

		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		level.initLevel(1);

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void run() {
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
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		int speed = 4;

		if (Keyboard.up) {
			dir = 4;

		}
		if (Keyboard.down) {
			dir = 2;

		}
		if (Keyboard.left) {
			dir = 3;

		}
		if (Keyboard.right) {
			dir = 1;

		}

		if (dir == 1)
			xOffset += speed;
		if (dir == 2)
			yOffset += speed;
		if (dir == 3)
			xOffset -= speed;
		if (dir == 4)
			yOffset -= speed;

		if (xOffset + player.x < 0)
			xOffset = -player.x;

		if (xOffset + player.x > boundrySize - player.size)
			xOffset = -player.x + boundrySize - player.size;

		if (yOffset + player.y < 0)
			yOffset = -player.y;

		if (yOffset + player.y > boundrySize - player.size)
			yOffset = -player.y + boundrySize - player.size;

		if (Keyboard.pause) {
			for (int i = 0; i < Button.buttons.size(); i++) {
				Button.buttons.get(i).visible = true;
				ui.update();
			}
		} else {
			for (int i = 0; i < Button.buttons.size(); i++) {
				Button.buttons.get(i).visible = false;
				level.update();
			}
		}

		deltaX = xOffset - lastX;
		deltaY = yOffset - lastY;
		key.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear(0);
		level.render(screen);
		if (key.pause)
			ui.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

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

		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setBackground(Color.white);
		game.frame.setVisible(true);

		game.start();

	}

}

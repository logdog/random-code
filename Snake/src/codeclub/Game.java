package codeclub;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;

import codeclub.Food.State;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = -5582170548642915241L;
	public static final int WIDTH = 76, HEIGHT = 40, SCALE = 12;
	private static String title = "Snake";
	private boolean running = false;

	private JFrame frame;
	private Thread thread;
	private Screen screen; // User defined
	private Keyboard key; // User defined
	private Snake snake; // User defined

	private String hs;
	private int score = 1;

	public Food[] foods = new Food[7];

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game()
	{
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setSize(size);
		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT); // User defined
		key = new Keyboard(); // User defined
		snake = new Snake(WIDTH / 2, HEIGHT / 2, 0x00a5ff);
		addKeyListener(key);
	}

	public void reset()
	{
		frame.setTitle(title + ". Use WASD or arrow keys to move. Don't cross over yourself!");
		key.reset();
		snake.alive = true;

		Scanner scan;
		hs = "0";
		try
		{
			scan = new Scanner(new File("res/Highscore.txt"));
			hs = scan.next();
		} catch (Exception e)
		{
			System.out.println("cannot open file");
			System.exit(1);
		}

		snake.kill();
		snake.headX = WIDTH / 2;
		snake.headY = HEIGHT / 2;

		for (int i = 0; i < foods.length; i++)
			foods[i] = new Food((int) (Math.random() * Game.WIDTH), (int) (Math.random() * Game.HEIGHT), 0xffa500);
		foods[foods.length-1].status = State.WHITE;
		foods[foods.length-1].col = 0xffffff;
		snake.add(9);
	}

	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1)
			{
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				updates = 0;
				frames = 0;
			}

		}
	}

	public synchronized void start()
	{
		reset();

		running = true;
		thread = new Thread(this, "DISPLAY");
		thread.start();
	}

	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			System.out.println("Error joining thread in method 'stop'");
			e.printStackTrace();
		}
	}

	public void end()
	{
		snake.alive = false;

		frame.setTitle("Game Over...    " + "Score: " + score + "    |    Highscore: " + hs + "                "+ "\tPress SPACE to play again!");
		if (score > Integer.parseInt(hs))
		{
			frame.setTitle("***  New Highscore! (" + score + ")   ***" + "\tPress SPACE to play again!");
			PrintWriter output = null;
			try
			{
				output = new PrintWriter(new File("res/Highscore.txt"));
				output.println(score);
				output.close();
			} catch (Exception e)
			{
				System.out.println("Coulnd't save highscore");
			}
		}
	}

	public void update()
	{
		if(snake.outBounds())
			snake.alive = false;
		
		if (snake.alive)
		{
			snake.update();

			if (snake.crossOver())
			{
				end();
				return;
			}

			if (key.up)
				snake.move(0, -Snake.SIZE);
			else if (key.down)
				snake.move(0, Snake.SIZE);
			else if (key.left)
				snake.move(-Snake.SIZE, 0);
			else if (key.right)
				snake.move(Snake.SIZE, 0);

			for (int i = 0; i < foods.length; i++)
			{
				foods[i].update();
				if (snake.headX == foods[i].x && snake.headY == foods[i].y)
				{
					if(foods[i].status == State.ORANGE)
						snake.add(5);
					else if(foods[i].status == State.RED)
						snake.add(8);
					else if(foods[i].status == State.WHITE)
						snake.add(10);
					else if(foods[i].status == State.PINK)
						snake.add(12);
					else
						snake.add(5);
					foods[i].move();
					score = snake.getScore();
					if (score <= Integer.parseInt(hs))
						frame.setTitle("Score: " + score + "     |     " + "Highscore: " + hs);
					else
						frame.setTitle("***  New Highscore! (" + score + ")   ***");
				}
			}
		} else
		{
			if (key.space)
				reset();
		}
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		snake.render(screen);
		for (Food f : foods)
			f.render(screen);

		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
		Game game = new Game();

		game.frame.setResizable(false);

		game.frame.add(game);
		game.frame.pack();

		game.frame.setLocationRelativeTo(null);
		game.frame.setBackground(Color.cyan);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);

		game.start();

	}

}

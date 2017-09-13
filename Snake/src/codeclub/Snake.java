package codeclub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake
{
	private List<Integer> xCoords = new ArrayList<Integer>();
	private List<Integer> yCoords = new ArrayList<Integer>();
	public int col;
	public static final int SIZE = 1;
	boolean alive;

	public int headX;
	public int headY;

	public Snake(int x, int y, int col)
	{
		xCoords.add(x);
		yCoords.add(y);
		this.col = col;
	}

	public void update()
	{
		headX = xCoords.get(0);
		headY = yCoords.get(0);
	}

	public void move(int x, int y)
	{
		int nextX = headX + x;
		int nextY = headY + y;

		for (int i = xCoords.size() - 1; i < 0; i--)
		{
			xCoords.set(i - 1, xCoords.get(i));
			yCoords.set(i - 1, yCoords.get(i));
		}
		xCoords.add(0, nextX);
		yCoords.add(0, nextY);

		xCoords.remove(xCoords.size() - 1);
		yCoords.remove(yCoords.size() - 1);
	}

	public void add(int n)
	{
		while (n > 0)
		{
			xCoords.add(-SIZE);
			yCoords.add(-SIZE);
			n--;
		}
	}

	public void shorten(int n)
	{
		while (n > 0)
		{
			xCoords.remove(xCoords.size() - 1);
			yCoords.remove(yCoords.size() - 1);
			n--;
		}
	}

	public void kill()
	{
		while (xCoords.size() > 1)
		{
			xCoords.remove(0);
			yCoords.remove(0);
		}
	}

	public boolean crossOver()
	{
		for (int i = 1; i < xCoords.size(); i++)
		{
			if (headX == xCoords.get(i) && headY == yCoords.get(i))
				return true;
		}
		return false;
	}

	public boolean outBounds()
	{
		return (headX < 0 || headY < 0 || headX >= Game.WIDTH || headY >= Game.HEIGHT);
	}

	public int getScore()
	{
		return xCoords.size();
	}

	public void render(Screen screen)
	{
		for (int i = 0; i < xCoords.size(); i++)
			screen.fillRect(xCoords.get(i), yCoords.get(i), SIZE, SIZE, col);
	}
}

package codeclub;

public class Screen
{
	public int[] pixels;
	private final int WIDTH, HEIGHT;
	
	public Screen(int w, int h)
	{
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
	}
	
	public void clear()
	{
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void invert()
	{
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0xffffff - pixels[i];
	}

	public void drawRect(int xc, int yc, int w, int h, int col)
	{
		for (int y = 0; y < h; y++)
		{
			int yp = y + yc;
			if (yp < 0 || yp >= HEIGHT)
				continue;
			for (int x = 0; x < w; x++)
			{
				int xp = x + xc;
				if (xp < 0 || xp >= WIDTH)
					continue;
				if (x == 0 || y == 0 || x == w - 1 || y == h - 1)
					pixels[xp + yp * WIDTH] = col;
			}
		}
	}

	public void fillRect(int xc, int yc, int w, int h, int col)
	{
		for (int y = 0; y < h; y++)
		{
			int yp = y + yc;
			if (yp < 0 || yp >= HEIGHT)
				continue;
			for (int x = 0; x < w; x++)
			{
				int xp = x + xc;
				if (xp < 0 || xp >= WIDTH)
					continue;

				pixels[xp + yp * WIDTH] = col;
			}
		}
	}
}

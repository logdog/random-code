package logdog.graphics;


public class Sprite
{
	public int width, height;
	public int[] pixels;
	public int x, y;
	private SpriteSheet sheet;

	// Sprites Here

	public static Sprite pink = new Sprite(16, 0xff00ff);
	public static Sprite yellow = new Sprite(16, 0xffff00);
	public static Sprite blue = new Sprite(16, 0xff);
	public static Sprite black = new Sprite(16, 0);
	
	// blocks
	


	// End Sprites

	public Sprite(int size, int color)
	{
		width = size;
		height = size;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		this.x = x * width;
		this.y = y * height;
		this.sheet = sheet;
		load();
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		this(size, size, x, y, sheet);
	}

	private void load()
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

	private void setColor(int color)
	{
		for (int i = 0; i < width * height; i++)
		{
			pixels[i] = color;
		}
	}

}

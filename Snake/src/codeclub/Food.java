package codeclub;

public class Food
{
	public int x, y, col;
	private final int defaultColor;
	public static final int SIZE = Snake.SIZE;
	private int time;
	private int timeCap;
	public State status;
	
	public Food(int x, int y, int col)
	{
		this.x = x;
		this.y = y;
		this.col = col;
		defaultColor = col;
		timeCap = (int)(Math.random() * 60 * 10) + 60 * 5;
		status = State.ORANGE;
	}
	
	public enum State
	{
		ORANGE, RED, WHITE, PINK
	}
	
	public void update()
	{
		time++;
		if(time >= timeCap)
			move();
		else if(timeCap - time <= 90)
		{
			if(status == State.ORANGE)
			{
				status = State.RED;
				col = 0xff0000;
			}
			else if(status == State.WHITE)
			{
				status = State.PINK;
				col = 0xff00ff;
			}
		}
	}
	
	public void move()
	{
		x = (int)(Math.random() * Game.WIDTH);
		y = (int)(Math.random() * Game.HEIGHT);
		time = 0;
		if(status == State.RED)
		{
			status = State.ORANGE;
			col = defaultColor;
		}
		else if(status == State.PINK)
		{
			status = State.WHITE;
			col = 0xffffff;
		}
	}
	
	public void render(Screen screen)
	{
		screen.fillRect(x, y, SIZE, SIZE, col);
	}
}

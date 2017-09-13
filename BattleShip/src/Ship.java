
public abstract class Ship
{
	public int[][] coords;
	public final int SIZE;

	public Ship(int size)
	{
		SIZE = size;
		coords = new int[SIZE][2];
		for (int i = 0; i < SIZE; i++)
			coords[i][0] = coords[i][1] = -1;
	}
	
	public boolean isFloating()
	{
		for(int i = 0; i < coords.length; i++)
		{
			if(coords[i][0] == -1)
				continue;
			return true;
		}
		return false;
	}
	

	public boolean hasShip(int col, int row)
	{
		for (int[] a : coords)
		{
			if (col == a[0] && row == a[1])
				return true;
		}
		return false;
	}
	
	public int getIndex(int col, int row)
	{
		for(int i = 0; i < coords.length; i++)
		{
			if(coords[i][0] == col && coords[i][1] == row)
				return i;
		}
		return 0;
	}
	
	public void createCoords(Ship[] boats, int index)
	{
		int[][] temp = new int[boats[index].SIZE][2];
		// 0 is vertical, 1 is horizontal

			for (int x = 0; x < boats[index].SIZE; x++)
			{
				temp[x][0] = -1;
				temp[x][1] = -1;
			}
			int direction = (int) (Math.random() * 2);
			int col = (int) (Math.random() * 10);
			int row = (int) (Math.random() * 10);
			
			//System.out.println("Boat #" + index +"Direction: " + direction + ", col: " + col + ", row: " + row);
			for (int j = 0; j < boats[index].SIZE; j++)
			{
				while (hasNegative(temp))
				{
					for(int k = 0; k < boats.length; k++)
					{
						if(k != index)
						{
							if(boats[k].hasShip(col, row))
							{
								for (int x = 0; x < boats[index].SIZE; x++)
								{
									temp[x][0] = -1;
									temp[x][1] = -1;
								}
								col = (int) (Math.random() * 10);
								row = (int) (Math.random() * 10);
								direction = (int) (Math.random() * 2);
								j = 0;
								continue;
							}
						}
					}
					if (!(boats[index].hasShip(col, row)) && col < 10 && row < 10)
					{
						temp[j][0] = col;
						temp[j][1] = row;
						switch (direction)
							{
							case 0:
							{
								row++;
								break;
							}
							case 1:
							{
								col++;
								break;
							}
							}
						break;
					} else
					{
						for (int x = 0; x < boats[index].SIZE; x++)
						{
							temp[x][0] = -1;
							temp[x][1] = -1;
						}
						col = (int) (Math.random() * 10);
						row = (int) (Math.random() * 10);
						direction = (int) (Math.random() * 2);
						j = 0;
						//System.out.print("else... ");
						//System.out.println("Direction: " + direction + ", col: " + col + ", row: " + row);
					}
				}
			}
		
		setCoords(temp);
	}
	
	private boolean hasNegative(int[][] c)
	{
		for(int i = 0; i < c.length; i++)
			{
				for(int j = 0; j < c[i].length; j++)
				{
					if(c[i][j] < 0)
						return true;
				}
			}
		return false;
	}
	
	public void setCoord(int index)
	{
		coords[index][0] = coords[index][1] = -1;
	}

	public void setCoords(int[][] pos)
	{
		for (int i = 0; i < SIZE; i++)
		{
			for (int k = 0; k < 2; k++)
			{
				coords[i][k] = pos[i][k];
			}
		}
	}
}

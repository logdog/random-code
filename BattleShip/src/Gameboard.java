
public class Gameboard
{

	public final int SIZE;
	public char[][] board;

	public Gameboard(int size)
	{
		SIZE = size;
		board = new char[SIZE][SIZE];
		createBoard();
	}

	public void createBoard()
	{
		for (int i = 0; i < SIZE; i++)
		{
			for (int j = 0; j < SIZE; j++)
			{
				board[i][j] = ' ';
			}
		}
	}

	public void displayBoard()
	{
		System.out.print("\tA  ");
		System.out.print("B  ");
		System.out.print("C  ");
		System.out.print("D  ");
		System.out.print("E  ");
		System.out.print("F  ");
		System.out.print("G  ");
		System.out.print("H  ");
		System.out.print("I  ");
		System.out.print("J  ");
		System.out.println("\n");
		
		for (int i = 0; i < SIZE; i++)
		{
			System.out.printf("%2d\t", (i + 1));
			for (int j = 0; j < SIZE; j++)
			{
				System.out.print(board[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public void setHit(int col, int row)
	{
		board[row][col] = 'X';
	}

	public void setMiss(int col, int row)
	{
		board[row][col] = '*';
	}

	public char getChar(int col, int row)
	{
		return board[row][col];
	}

}

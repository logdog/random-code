import java.util.Scanner;
public class Game
{
	private Scanner scan;
	private Gameboard board;
	private int[][] guesses = new int[100][2];
	
	public Ship[] boats;
	
	public Game()
	{
		scan = new Scanner(System.in);
		board = new Gameboard(10);
		boats = new Ship[5];
		boats[0] = new AircraftCarrier();
		boats[1] = new Battleship();
		boats[2] = new Submarine();
		boats[3] = new Destroyer();
		boats[4] = new Patrol();
		
		boats[0].createCoords(boats, 0);
		boats[1].createCoords(boats, 1);
		boats[2].createCoords(boats, 2);
		boats[3].createCoords(boats, 3);
		boats[4].createCoords(boats, 4);
		
		for(int i = 0; i < guesses.length; i++)
		{
			for(int j = 0; j < guesses[i].length; j++)
			{
				guesses[i][j] = -1;
			}
		}
		run();
	}
	
	public void run()
	{
		int round = 0;
		while(boatsFloating() && round < 65)
		{
			round++;
			board.displayBoard();
			
			System.out.printf("Round %d: Fire at?\n", round);
			System.out.print("X Coord: ");
			String x = "";
			do
			{
				x = scan.nextLine();
				x = x.trim();
				
			} while(!(x.equalsIgnoreCase("A") || x.equalsIgnoreCase("B") || x.equalsIgnoreCase("C") || x.equalsIgnoreCase("D") ||
					x.equalsIgnoreCase("E") || x.equalsIgnoreCase("F") || x.equalsIgnoreCase("G") || x.equalsIgnoreCase("H") ||
					x.equalsIgnoreCase("I") || x.equalsIgnoreCase("J")));
			int xc;
			x = x.toUpperCase();
			char xchar = x.toCharArray()[0];
			
			switch(xchar)
			{
			case 'A' : xc = 0; break;
			case 'B' : xc = 1; break;
			case 'C' : xc = 2; break;
			case 'D' : xc = 3; break;
			case 'E' : xc = 4; break;
			case 'F' : xc = 5; break;
			case 'G' : xc = 6; break;
			case 'H' : xc = 7; break;
			case 'I' : xc = 8; break;
			case 'J' : xc = 9; break;
			default  : xc = 0; break;
			}
			
			//hello world
			
			System.out.print("Y Coord: ");
			int y = -1;
			do
			{
				try
				{
					y = scan.nextInt();
				}
				catch (Exception e)
				{
					String err = scan.nextLine();
				}
			} while(y < 1 || y > 10);
			
			y--;
			

			if(!alreadyGuesses(xc, y))
			{
				if(fire(xc, y))
				{
					board.setHit(xc, y);
				}
				else
				{
					board.setMiss(xc, y);
				}
			}
			else
				System.out.println("You already guessed that!");
			
			guesses[round][0] = xc;
			guesses[round][1] = y;
					
		}
		if(!boatsFloating())
		{
			board.displayBoard();
			System.out.println("Congradulations! Keep working for that high score!\nPost your score on edmodo");
		}
		else
		{
			System.out.println("Sorry, you took too long.. Here are the answers");
			showAnswers();
		}
		
	}
	
	public boolean alreadyGuesses(int xc, int y)
	{
		for(int i = 0; i < guesses.length; i++)
		{
			if(guesses[i][0] == xc && guesses[i][1] == y)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean fire(int col, int row)
	{
		for(Ship s : boats)
		{
			if(s.hasShip(col, row))
			{
				s.setCoord(s.getIndex(col, row));
				if(!s.isFloating())
					System.out.println("You sunk my " + s.getClass().getSimpleName() + "!");
				return true;
			}
		}
		return false;
	}
	
	public boolean boatsFloating()
	{
		for(Ship s : boats)
		{
			if(s.isFloating())
				return true;
		}
		return false;
	}
	
	public void showAnswers()
	{
		for(int i = 0; i < boats.length; i++)
		{
			for(int j = 0; j < boats[i].SIZE; j++)
			{
				if(boats[i].coords[j][0] != -1 && boats[i].coords[j][1] != -1)
					board.setHit(boats[i].coords[j][0], boats[i].coords[j][1]);
			}
			System.out.println();
		}
		board.displayBoard();
	}

	public static void main(String[] args)
	{
		new Game();
	}
	
}

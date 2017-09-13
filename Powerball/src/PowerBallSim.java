public class PowerBallSim
{
	public static void main(String[] args)
	{
		int count = 1;
		int[] ans = { (int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1,
				(int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1,
				(int) (Math.random() * 26) + 1 };

		while (true)
		{
			int[] guesses = { (int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1,
					(int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1, (int) (Math.random() * 69) + 1,
					(int) (Math.random() * 26) + 1 };

			
			boolean won = true;

			for (int i = 0; i < ans.length - 1; i++)
			{
				if (guesses[0] == ans[i] || guesses[1] == ans[i] || guesses[2] == ans[i] || guesses[3] == ans[i]
						|| guesses[4] == ans[i])
				{
					won = true;
					continue;
				} else
				{
					won = false;
					break;
				}
			}

			if (won && guesses[5] == ans[5])
			{
			

				System.out.println("Here is your ticket");
				System.out.println("-------------------------------------------------");
				for (int i = 0; i < guesses.length; i++)
					System.out.print(guesses[i] + "\t");
				System.out.println("\n-------------------------------------------------\n\n");

				System.out.println("Here is the winning ticket");
				System.out.println("-------------------------------------------------");
				for (int i = 0; i < ans.length; i++)
					System.out.print(ans[i] + "\t");
				System.out.println("\n-------------------------------------------------");
				System.out.println("count:" + count);
				break;
			} else
			{
				//System.out.println("LOSER");
				count++;
				continue;
			}

			
		}
		

		
	}

}

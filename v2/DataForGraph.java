import java.io.*;
import java.util.*;

class DataForGraph
{
	private int numberOfGames;
	private int[] numberOfWinners;
	private int getMax(int n)
	{
		int max = 0, arr = 0;
		for(int i = n; i < n+100; i++)
		{
			if(numberOfWinners[i] == 1)
				arr++;
			else
				arr--;
			if(arr > max)
				max = arr;
		}
		return max+1;
	}
	public int getNumberOfGames()
	{
		return numberOfGames;
	}
	public int[][] getY(int n)
	{
		int [][]y = new int[100][2];
		y[0][0] = 550-5*getMax(n);
		for( int i = 0; i < 100; i++)
		{
			if(numberOfWinners[i+n] == 1)
		 		y[i][1] = y[i][0] + 5;
		 	else
		 		y[i][1] = y[i][0] - 5;
		 	if(i+1 != 100)
				y[i+1][0] = y[i][1];
		}
		return y;
	}
	DataForGraph() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("DataForGraph.txt"));
		String str1 = br.readLine();
		numberOfGames = Integer.valueOf(str1);
		if(numberOfGames!=0)
		{
			numberOfWinners = new int[numberOfGames];
			String str = br.readLine();
			String a[] = str.split("\t");
			for (int i = 0; i < numberOfGames; i++)
				numberOfWinners[i] = Integer.valueOf(a[i]);
		}
	}
	public void update() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("DataForGraph.txt"));
		String str1 = br.readLine();
		numberOfGames = Integer.valueOf(str1);
		if(numberOfGames!=0)
		{
			numberOfWinners = new int[numberOfGames];
			String str = br.readLine();
			String a[] = str.split("\t");
			for (int i = 0; i < numberOfGames; i++)
				numberOfWinners[i] = Integer.valueOf(a[i]);
		}
	}
	public void save() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("DataForGraph.txt"));
		pw.print(numberOfGames + "\n");
		for (int i = 0; i < numberOfGames; i++)
			pw.print(numberOfWinners[i] + "\t");
		pw.close();
	}
	public void save(int newWinner) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("DataForGraph.txt"));
		pw.print(numberOfGames+1 + "\n");
		for (int i = 0; i < numberOfGames; i++)
			pw.print(numberOfWinners[i] + "\t");
		pw.print(newWinner);
		pw.close();
	}
}

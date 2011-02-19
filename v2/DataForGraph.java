import java.io.*;
import java.util.*;

class DataForGraph
{
	private int numberOfGames;
	private int[] numberOfWinners;

	public int HEIGHT = 70;
	public int WIDTH = 70;
	public int NET = 5;
	public int INDENT = 50;

	private int getMax(int n)
	{
		int max = 0, arr = 0;
		if(numberOfGames < WIDTH)
			for(int i = n; i < n + numberOfGames ; i++)
			{
				if(numberOfWinners[i] == 1)
					arr++;
				else
					arr--;
				if(arr > max)
					max = arr;
			}
		else
			for(int i = n; i < n + WIDTH ; i++)
			{
				if(numberOfWinners[i] == 1)
					arr++;
				else
					arr--;
				if(arr > max)
					max = arr;
			}
		return max + 1;
	}
	public int getH(int n)
	{
		int max = 0,min = 0, arr = 0;
		for(int i = n; i < n + WIDTH; i++)
		{
			if(numberOfWinners[i] == 1)
				arr++;
			else
				arr--;
			if(arr < min)
				min = arr;
			if(arr > max)
				max = arr;
		}
		return min + max;
	}
	public int getNumberOfGames()
	{
		return numberOfGames;
	}
	public int[][] getY(int n)
	{
		if(numberOfGames < WIDTH)
		{
			int [][]y = new int[numberOfGames][2];
			y[0][0] = HEIGHT*NET + INDENT - NET*getMax(n);
			for( int i = 0; i < numberOfGames; i++)
			{
				if(numberOfWinners[i] == 1)
			 		y[i][1] = y[i][0] + NET;
			 	else
			 		y[i][1] = y[i][0] - NET;
			 	if(i+1 != numberOfGames)
					y[i+1][0] = y[i][1];
			}
			return y;
		}
		int [][]y = new int[WIDTH][2];
		y[0][0] = HEIGHT*NET + INDENT - NET*getMax(n);
		for( int i = 0; i < WIDTH; i++)
		{
			if(numberOfWinners[i+n] == 1)
		 		y[i][1] = y[i][0] + NET;
		 	else
		 		y[i][1] = y[i][0] - NET;
		 	if(i+1 != WIDTH)
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

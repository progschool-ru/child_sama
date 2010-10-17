import java.io.*;
import java.util.*;

class Move
{
	private int X=3;
	private int Y=3;
	private int[] board; 
/*	public void setXY(String x, String y)
	{
		this.x = Integer.valueOf(x);
		this.y = Integer.valueOf(y);
	}
	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
*/
	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	} 
}

class Data
{
	int move;
	Move[] moves;
	Data() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String str1 = br.readLine();
		move = Integer.valueOf(str1);
		moves = new Move[move];
		for (int i = 0; i < move; i++)
		{
			moves[i] = new Move();
			for(int j = 0; j < moves[i].getY; j++) {
				String str = br.readLine();
				for(int l = 0; l < moves[i].getX; l++)
						
/*			String str2 = br.readLine();
			moves[i].priceOld = Integer.valueOf(str3);
			String str3 = br.readLine();
			moves[i].price = Integer.valueOf(str4);
			String str4 = br.readLine();
			String a[] = str5.split("\t");
			halls[v].setXY(a[0], a[1]);
			halls[v].setSoldTickets();
			String str6 = br.readLine();
			String b[] = str6.split("\t");
			halls[v].setSoldTickets(b);
*/
		}
	}
	public void save() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(trade + "\n");
		pw.print(hall + "\n");
		for (int v = 0; v < hall; v++)
		{
			pw.print(halls[v].priceOld + "\n");
			pw.print(halls[v].price + "\n");
			pw.print(halls[v].getX() + "\t" + halls[v].getY() + "\n");
			if (halls[v].getSoldTickets(0) == 0)
			{
				pw.print(0 + "\n");
			}
			else
			{
				for (int a = 0; a < halls[v].getSumSoldTickets(); a++)
				{
					pw.print(halls[v].getSoldTickets(a) + "\t");
				}
				pw.print("\n");
			}
		}
		pw.close();
	}
}
class SAMA
{





}
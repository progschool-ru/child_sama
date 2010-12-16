import java.io.*;
import java.util.*;

class List
{
	private int numberOfBF;
	private int[] NSteps;
	private String[] BFs;
	public int getNumberOfBF()
	{
		return numberOfBF;
	}
	public int[] getNSteps()
	{
		return NSteps;
	}
	public String[] getBFs()
	{
		return BFs;
	}
	List() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("list.txt"));
		String str = br.readLine();
		numberOfBF = Integer.valueOf(str);
		NSteps = new int[numberOfBF];
		BFs = new String[numberOfBF];
		for (int i = 0; i < numberOfBF; i++)
		{
			BFs[i] = br.readLine();
			String str2 = br.readLine();
			NSteps[i] = Integer.valueOf(str2);
		}
	}
	public void save(String BF, int NStep) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("list.txt"));
		pw.print(numberOfBF+1 + "\n");
		for (int i = 0; i < numberOfBF; i++)
		{
			pw.print(BFs[i] + "\n");
			pw.print(NSteps[i] + "\n");
		}
		pw.print(BF + "\n");
		pw.print(NStep);
		pw.close();
	}
	public void newList() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("list.txt"));
		pw.print(0);
		pw.close();
	}
}
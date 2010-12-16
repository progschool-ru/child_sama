import java.io.*;
import java.util.*;

class Data
{
	private int numberOfBF;
	DataSteps[] dataSteps;
	public int getNumberOfBF()
	{
		return numberOfBF;
	}
	Data(int playerNumber) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String str = br.readLine();
		numberOfBF = Integer.valueOf(str);
		dataSteps = new  DataSteps[numberOfBF];
		for (int i = 0; i < numberOfBF; i++)
		{
			dataSteps[i] = new DataSteps();
			String str1 = br.readLine();
			dataSteps[i].setBF(str1);
			String str2 = br.readLine();
			int numberOfSteps = Integer.valueOf(str2);
			dataSteps[i].steps = new Step[numberOfSteps];
			dataSteps[i].setNumberOfSteps(numberOfSteps);
			for(int j = 0; j < numberOfSteps; j++)
			{
				dataSteps[i].steps[j] = new Step(playerNumber, str1);
				String str3 = br.readLine();
				String b[] = str3.split("\t");
				dataSteps[i].steps[j].setOldNumber(Integer.valueOf(b[0]));
				dataSteps[i].steps[j].setNewNumber(Integer.valueOf(b[1]));
				dataSteps[i].steps[j].setPoint(Integer.valueOf(b[2]));
			}
		}
	}
	public void save() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(numberOfBF + "\n");
		for (int i = 0; i < numberOfBF; i++)
		{
			pw.print(dataSteps[i].getBF() + "\n");
			pw.print(dataSteps[i].getNumberOfSteps() + "\n");
			for (int j = 0; j < dataSteps[i].getNumberOfSteps(); j++)
			{
				pw.print(dataSteps[i].steps[j].getOldNumber() + "\t");
				pw.print(dataSteps[i].steps[j].getNewNumber() + "\t");
				pw.print(dataSteps[i].steps[j].getPoint() + "\n");
			}
		}
		pw.close();
	}
	public void save(DataSteps newStep) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(numberOfBF+1 + "\n");
		for (int i = 0; i < numberOfBF; i++)
		{
			pw.print(dataSteps[i].getBF() + "\n");
			pw.print(dataSteps[i].getNumberOfSteps() + "\n");
			for (int j = 0; j < dataSteps[i].getNumberOfSteps(); j++)
			{
				pw.print(dataSteps[i].steps[j].getOldNumber() + "\t");
				pw.print(dataSteps[i].steps[j].getNewNumber() + "\t");
				pw.print(dataSteps[i].steps[j].getPoint() + "\n");
			}
		}
		pw.print(newStep.getBF() + "\n");
		pw.print(newStep.getNumberOfSteps() + "\n");
		for (int j = 0; j < newStep.getNumberOfSteps(); j++)
		{
			pw.print(newStep.steps[j].getOldNumber() + "\t");
			pw.print(newStep.steps[j].getNewNumber() + "\t");
			pw.print(newStep.steps[j].getPoint() + "\n");
		}
		pw.close();
	}
}

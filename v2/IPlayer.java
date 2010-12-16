import java.io.*;
import java.util.*;

interface IPlayer
{
	public Step doStep(Step step, int n) throws IOException;
	public String getName();
	public void carrotAndStick(int numberOfTheWinner) throws IOException;
}

abstract class Player implements IPlayer
{
	protected String name;
	protected int playerNumber;
	protected void nameInit(int playerNumber) throws IOException
	{
		this.playerNumber = playerNumber;
	}
	public String getName()
	{
		return name;
	}
	public void carrotAndStick(int numberOfTheWinner) throws IOException
	{
		List l = new List();
		Data d = new Data(numberOfTheWinner);
		int NSteps[] = l.getNSteps();
		String BFs[] = l.getBFs();
		for(int i = 0; i < l.getNumberOfBF(); i++)
			for(int j = 0; j < d.getNumberOfBF(); j++)
				if(d.dataSteps[j].getBF().equals(BFs[i]))
				{
					if (numberOfTheWinner == 2)
						d.dataSteps[j].steps[NSteps[i]] = carrot(d.dataSteps[j].steps[NSteps[i]]);
					else
						d.dataSteps[j].steps[NSteps[i]] = stick(d.dataSteps[j].steps[NSteps[i]]);
				}
		d.save();
	}
	private static Step carrot(Step step) throws IOException
	{
		step.setPoint(step.getPoint()+3);
		return step;
	}
	private static Step stick(Step step) throws IOException
	{
		if(step.getPoint()-1 > 0)
			step.setPoint(step.getPoint()-1);
		else
			step.setPoint(0);
		return step;
	}
}

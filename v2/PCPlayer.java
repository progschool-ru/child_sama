import java.io.*;
import java.util.*;

class PCPlayer extends Player
{
	public PCPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Step step, int n) throws IOException
	{
		if(playerNumber != step.getPlayerNumber())
			return step;
		if(step.getOldNumber() == -1)
		{
			step = choiceStep(step);
		}
		return step;
	}
	private Step choiceStep(Step step) throws IOException
	{
		DataSteps ds = initDS(step);
		step = stepRandom(ds);
		step = step.stepProc();
		return step;
	}
	private DataSteps initDS(Step step) throws IOException
	{
		Data d = new Data(playerNumber);
		for(int i = 0; i < d.getNumberOfBF(); i++)
			if(d.dataSteps[i].getBF().equals(step.getBF()))
				return d.dataSteps[i];
		DataSteps ds = newDataStep(step);
		d.save(ds);
		return ds;
	}
	private DataSteps newDataStep(Step step) throws IOException
	{
		DataSteps ds = new DataSteps();
		ds.setBF(step.getBF());
		ds.setSteps(step.getNumberOfSteps(playerNumber));
		ds.setSteps(step.getSteps(playerNumber));
		return ds;
	}
	private Step stepRandom(DataSteps ds) throws IOException
	{
		Random random = new Random ();
		List l = new List();
		int r = random.nextInt(ds.getAllPoint()+1);
		for (int i = 0; i < ds.getNumberOfSteps(); i++)
		{
			r = r - ds.steps[i].getPoint();
			if(r <= 0 )
			{
				l.save(ds.getBF(), i);
				return ds.steps[i];
			}
		}
		return ds.steps[ds.getNumberOfSteps()-1];
	}
}
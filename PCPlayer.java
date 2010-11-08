import java.io.*;
import java.util.*;

class PCPlayer extends Player
{
	public PCPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Game g) throws IOException
	{
		Step step = new Step();
		step.setPlayerNumber(playerNumber);
		System.out.println("Сейчас ходит игрок №"+playerNumber+" - "+name);
		g.getBattleFieldInSystem();
		step = choiceStep(g, step);
		return step;
	}
	private Step choiceStep(Game g, Step step) throws IOException
	{
		DataSteps ds = initDS(g);
		step = stepRandom(ds);
		return step;
	}
	private DataSteps initDS(Game g) throws IOException
	{
		Data d = new Data(playerNumber);
		for(int i = 0; i < d.getNumberOfBF(); i++)
			if(d.dataSteps[i].getBF().equals(g.getBF()))
				return d.dataSteps[i];
		DataSteps ds = newDataStep(g);
		d.save(ds);
		return ds;
	}
	private DataSteps newDataStep(Game g) throws IOException
	{
		DataSteps ds = new DataSteps();
		ds.setBF(g.getBF());
		ds.setSteps(g.getNumberOfSteps(playerNumber));
		ds.setSteps(g.getSteps(playerNumber));
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
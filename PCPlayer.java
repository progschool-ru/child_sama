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
		step = ds.steps[0];
		return step;
	}
	private DataSteps initDS(Game g) throws IOException
	{
		Data d = new Data();
		for(int i = 0; i < d.numberOfBF; i++)
			if(d.dataSteps[i].getBF().equals(g.getBF()))
				return d.dataSteps[i];
		return newDataStep(g);
	}
	private DataSteps newDataStep(Game g) throws IOException
	{
		DataSteps ds = new DataSteps();
		ds.setBF(g.getBF());
		ds.setSteps(g.getNumberOfSteps(playerNumber));
		ds.setSteps(g.getSteps(playerNumber));
		return ds;
	}
}
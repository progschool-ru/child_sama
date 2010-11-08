import java.io.*;
import java.util.*;

class SAMA
{
	private static final String GAME = "game";
	private static final String NEW = "new";
	private static final String EXIT = "exit";
	public static void main(String []args) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean Exit=false;
		while(Exit == false)
		{
			String choice=br.readLine();
			if(choice.equals(GAME))
				game();
			else if(choice.equals(NEW))
			{
			}
			else if(choice.equals(EXIT))
				Exit=true;
		}
	}
	private static void game() throws IOException
	{
		List l = new List();
		l.newList();
		Game g = new Game();
		Step step = new Step();
		g.setNewBattleField();
		IPlayer p1 = new HumanPlayer(1);
		IPlayer p2 = new PCPlayer(2);
		int numberOfTheWinner = 0;
		String theWinner = "Победителя нет";
		while(g.getGameOver(1) == 0)
		{
			step = p1.doStep(g);
			g.step(step);
			if(g.getGameOver(2) != 0)
			{
				numberOfTheWinner = g.getGameOver(2);
				break;
			}
			step = p2.doStep(g);
			g.step(step);
		}
		if(numberOfTheWinner == 0)
			numberOfTheWinner = g.getGameOver(1);
		System.out.println("Победил игрок №"+numberOfTheWinner);
		carrotAndStick(numberOfTheWinner);
	}
	private static void carrotAndStick(int numberOfTheWinner) throws IOException
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
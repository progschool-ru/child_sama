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
	public static void game() throws IOException
	{
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
	}
}
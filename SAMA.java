import java.io.*;
import java.util.*;

class SAMA
{
	public static void main(String []args) throws IOException
	{
		Text t = new Text();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean Exit=false;		
		while(Exit == false)
		{
			System.out.println(t.NEW_GAME);
			String choice=br.readLine();
			if(choice.equals(t.GAME))
				game();
			else if(choice.equals(t.NEW))
			{
			}
			else if(choice.equals(t.EXIT))
				Exit=true;
		}
	}
	private static void game() throws IOException
	{
		Text t = new Text();
		List l = new List();
		l.newList();
		Game g = new Game();
		Step step = new Step();
		g.setNewBattleField();
		IPlayer p1 = new HumanPlayer(1);
		IPlayer p2 = new PCPlayer(2);
		int numberOfTheWinner = 0;
		while(g.getGameOver(1) == 0)
		{
			step = p1.doStep(g);
			g.step(step);
			if(g.getGameOver(2) != 0)
			{
				numberOfTheWinner = 1;
				p2.carrotAndStick(numberOfTheWinner);
				System.out.println(t.WINER_NUMBER+" "+numberOfTheWinner+" - "+p1.getName());
				break;
			}
			step = p2.doStep(g);
			g.step(step);
		}
		if(numberOfTheWinner == 0)
		{
			numberOfTheWinner = 2;
			p2.carrotAndStick(numberOfTheWinner);
			System.out.println(t.WINER_NUMBER+" "+numberOfTheWinner+" - "+p2.getName());
		}
	}
}
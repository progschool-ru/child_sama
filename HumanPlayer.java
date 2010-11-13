import java.io.*;
import java.util.*;

class HumanPlayer extends Player
{
	public HumanPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Game g) throws IOException
	{
		Text t = new Text();
		Step step = new Step();
		step.setPlayerNumber(playerNumber);
		System.out.println(t.NOW_GO_PLAYER+" "+playerNumber+" - "+name);
		g.getBattleFieldInSystem();
		for(;;)
		{
			step = choicePawn(g, step);
			step = choiceSquare(g, step);
			if(step.getPlayerNumber() == playerNumber)
				break;
			step.setPlayerNumber(playerNumber);
		}
		return step;
	}
	protected Step choicePawn(Game g, Step step) throws IOException
	{
		Text t = new Text();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for (;;)
		{
			System.out.println(t.ENTER_THE_PAWNs_COORDINATES);
			String choice = br.readLine();
			try
			{
				String []XY = choice.split(" ");
				step.setOldX(Integer.valueOf(XY[0]));
				step.setOldY(Integer.valueOf(XY[1]));
			}
			catch (NumberFormatException e)
			{
				System.out.println(t.HOW_ENTER_THE_PAWNs_COORDINATES);
				continue;
			}
			catch (ArrayIndexOutOfBoundsException e2)
			{
				System.out.println(t.HOW_ENTER_THE_PAWNs_COORDINATES);
				continue;
			}
			if(g.getBattleField(step.getOldY(), step.getOldX()) != playerNumber)
			{
				System.out.println(t.PAWN_NO);
				g.getBattleFieldInSystem();
				continue;
			}
			return step;
		}
	}
	protected Step choiceSquare(Game g, Step step)throws IOException
	{
		Text t = new Text();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			System.out.println(t.ENTER_THE_CAGEs_COORDINATES);
			System.out.println(t.IF_YOU_WANT_TO_CHANGE_PAWN);
			String choice = br.readLine();
			if(choice.equals(t.CHOOSE_PAWN))
			{
				step.setPlayerNumber(0);
				return step;
			}
			try
			{
				String []XY = choice.split(" ");
				step.setNewX(Integer.valueOf(XY[0]));
				step.setNewY(Integer.valueOf(XY[1]));
			}
			catch (NumberFormatException e) {
				System.out.println(t.HOW_ENTER_THE_CAGEs_COORDINATES);
				continue;
			}
			catch (ArrayIndexOutOfBoundsException e2)
			{
				System.out.println(t.HOW_ENTER_THE_CAGEs_COORDINATES);
				continue;
			}
			if(g.getBattleField(step.getNewY(), step.getNewX()) == playerNumber)
			{
				System.out.println(t.PAWN_YES);
				g.getBattleFieldInSystem();
				continue;
			}
			int m;
			if(playerNumber == 1)
				m=-1;
			else
				m=1;
			if(step.getNewY()-step.getOldY() == m)
			{
				if(step.getNewX()-step.getOldX() > 1 || step.getNewX()-step.getOldX() < -1)
				{
					System.out.println(t.PROGRESS_IN_THE_IMPOSSIBLE);
					continue;
				}
				if(step.getNewX()-step.getOldX() != 0 && g.getBattleField(step.getNewY(), step.getNewX()) == 0)
				{
					System.out.println(t.PROGRESS_IN_THE_IMPOSSIBLE);
					continue;
				}
				if(step.getNewX()-step.getOldX() == 0 && g.getBattleField(step.getNewY(), step.getNewX()) != 0)
				{
					System.out.println(t.PROGRESS_IN_THE_IMPOSSIBLE);
					continue;
				}
			}
			else
			{
				System.out.println(t.PROGRESS_IN_THE_IMPOSSIBLE);
				continue;
			}
			return step;
		}
	}
}
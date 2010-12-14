import java.io.*;
import java.util.*;

class HumanPlayer extends Player
{
	public HumanPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Step step, int n) throws IOException
	{
		if(step.getOldNumber() == -1)
			step = choicePawn(step, n);
		else
			step = choiceSquare(step, n);
		return step;
	}
	protected Step choicePawn(Step step, int n) throws IOException
	{

		if(step.getNC(n) == step.getPlayerNumber())
			step.setOldNumber(n);
		return step;
	}
	protected Step choiceSquare(Step step, int n)throws IOException
	{
		if(step.getNC(n) == step.getPlayerNumber())
		{
			step.setOldNumber(n);
			return step;
		}
		int m, w;
		if(step.getPlayerNumber() == 2)
		{
			m = -1;
			w = 1;
		}
		else
		{
			m = 1;
			w = 2;
		}
		if(step.getY(n) - step.getY(step.getOldNumber()) == m)
		{
			if((step.getX(n) - step.getX(step.getOldNumber()) == 1 || step.getX(n) - step.getX(step.getOldNumber()) == -1) && step.getNC(n) == w)
			{
				step.setNC(n , step.getPlayerNumber());
				step.setNC(step.getOldNumber() , 0);
				step.setOldNumber(-1);
				step.setPlayerNumber(w);
			}
			if(step.getX(n) - step.getX(step.getOldNumber()) == 0 && step.getNC(n) == 0)
			{
				step.setNC(n , step.getPlayerNumber());
				step.setNC(step.getOldNumber() , 0);
				step.setOldNumber(-1);
				step.setPlayerNumber(w);
			}
		}
		return step;
	}
}
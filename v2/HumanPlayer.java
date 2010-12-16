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
		if(playerNumber != step.getPlayerNumber())
			return step;
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
		step.setNewNumber(n);
		if(step.getY(n) - step.getY(step.getOldNumber()) == step.getCourse(step.getPlayerNumber()))
		{
			if((step.getX(n) - step.getX(step.getOldNumber()) == 1 || step.getX(n) - step.getX(step.getOldNumber()) == -1) && step.getNC(n) == step.getW(step.getPlayerNumber()))
				step = step.stepProc();
			if(step.getX(n) - step.getX(step.getOldNumber()) == 0 && step.getNC(n) == 0)
				step = step.stepProc();
		}
		return step;
	}
}
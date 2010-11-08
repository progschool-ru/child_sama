import java.io.*;
import java.util.*;

class Game
{
	private int X=3;
	private int Y=3;
	private boolean gameOver = false;
	private int[][] battleField;
	public String getBF()
	{
		String str = "";
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				str = str + String.valueOf(battleField[i][j]);
		return str;
	}
	private boolean getPossHead(int playerNumber, int y, int x)
	{
		try
		{
			if(battleField[y+getCourse(playerNumber)][x] != 0)
				return false;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	private boolean getPossLeft(int playerNumber, int y, int x)
	{
		try
		{
			if(battleField[y+getCourse(playerNumber)][x-1] == playerNumber)
				return false;
			if(battleField[y+getCourse(playerNumber)][x-1] == 0)
				return false;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	private boolean getPossRight(int playerNumber, int y, int x)
	{
		try
		{
			if(battleField[y+getCourse(playerNumber)][x+1] == playerNumber)
				return false;
			if(battleField[y+getCourse(playerNumber)][x+1] == 0)
				return false;
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return false;
		}
		return true;
	}
	private int getCourse(int playerNumber)
	{
		if(playerNumber == 1)
			return -1;
		return 1;
	}
	public int getNumberOfSteps(int playerNumber)
	{
		int NumberOfSteps = 0;;
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(battleField[i][j] == playerNumber)
				{
					if(getPossLeft(playerNumber, i, j))
						NumberOfSteps++;
					if(getPossHead(playerNumber, i, j))
						NumberOfSteps++;
					if(getPossRight(playerNumber, i, j))
						NumberOfSteps++;
				}
		return  NumberOfSteps;
	}
	public Step[] getSteps(int playerNumber) throws IOException
	{
		Step[] steps = new Step [getNumberOfSteps(playerNumber)];
		int s = 0;
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(battleField[i][j] == playerNumber)
				{
					if(getPossLeft(playerNumber, i, j))
					{
						steps[s] = new Step();
						steps[s].setOldX(j);
						steps[s].setOldY(i);
						steps[s].setNewX(j-1);
						steps[s].setNewY(i+getCourse(playerNumber));
						steps[s].setPoint(15);
						steps[s].setPlayerNumber(playerNumber);
						s++;
					}
					if(getPossHead(playerNumber, i, j))
					{
						steps[s] = new Step();
						steps[s].setOldX(j);
						steps[s].setOldY(i);
						steps[s].setNewX(j);
						steps[s].setNewY(i+getCourse(playerNumber));
						steps[s].setPoint(15);
						steps[s].setPlayerNumber(playerNumber);
						s++;
					}
					if(getPossRight(playerNumber, i, j))
					{
						steps[s] = new Step();
						steps[s].setOldX(j);
						steps[s].setOldY(i);
						steps[s].setNewX(j+1);
						steps[s].setNewY(i+getCourse(playerNumber));
						steps[s].setPoint(15);
						steps[s].setPlayerNumber(playerNumber);
						s++;
					}
				}
		return steps;
	}
	public int getGameOver(int playerNumber)
	{
  		for(int j = 0; j < X; j++)
  			if(battleField[0][j] == 1)
 				return 1;
 		for(int j = 0; j < X; j++)
 			if(battleField[Y-1][j] == 2)
  				return 2;
 		if(playerNumber == 1 && getNumberOfSteps(1) == 0)
 			return 2;
 		if(playerNumber == 2 && getNumberOfSteps(2) == 0)
 			return 1;
 		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(battleField[i][j] == playerNumber)
					return 0;
		if(playerNumber == 1)
			return 2;
		return 1;
	}
	public void step(Step step)
	{
		battleField[step.getOldY()][step.getOldX()] = 0;
		battleField[step.getNewY()][step.getNewX()] = step.getPlayerNumber();
		getBattleFieldInSystem();
	}
	public int getBattleField(int y, int x)
	{
		try
		{
			return battleField[y][x];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return -1;
		}
	}
	public void getBattleFieldInSystem()
	{
			System.out.print(" X");
			for(int i = 0; i < X; i++)
				System.out.print(" "+i);
			System.out.println("\nY");
			for(int i = 0; i < Y; i++) {
				System.out.print(i+"  ");
				for(int j = 0; j < X; j++)
					System.out.print(battleField[i][j]+" ");
				System.out.println();
			}
	}
	public void setNewBattleField()
	{
		battleField = new int [Y][X];
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
			{
				if(i == 0)
					battleField[i][j] = 2;
				else if(i == Y-1)
					battleField[i][j] = 1;
				else
					battleField[i][j] = 0;
			}
	}
}

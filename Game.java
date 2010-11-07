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
	public int getNumberOfSteps(int playerNumber)
	{


	}
	public int getSteps(int playerNumber)
	{


	}	
	public boolean getGameOver(int playerNumber)
	{
		int p1 = 0, p2 = 0;
		if(playerNumber == 1)
		{
			p1 = 0;
			for(int j = 0; j < X; j++)
				if(battleField[0][j] == 1)
					p1 ++;
			if(p1 != 0)
				return true;
		}
		else
		{
			p2 = 0;
			for(int j = 0; j < X; j++)
				if(battleField[Y-1][j] == 2)
					p2 ++;
			if(p2 != 0)
				return true;
		}
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
			{
				if(battleField[i][j] == 1)
				{
					if(battleField[i-1][j] == 2 || battleField[i-1][j] == 1)
						p1 = 0;
					else {
						p1 ++;
						i = Y;
						j = X;
					}
					if (p1 == 0){
						try
						{
							if(battleField[i-1][j-1] == 2) {
								p1 ++;
								i = Y;
								j = X;
							}
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
						}
						try
						{
							if(battleField[i-1][j+1] == 2) {
								p1 ++;
								i = Y;
								j = X;
							}
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
						}
					}
				}
			}
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
			{
				if(battleField[i][j] == 2)
				{
					if(battleField[i+1][j] == 2 || battleField[i+1][j] == 1)
						p2 = 0;
					else {
						p2 ++;
						i = Y;
						j = X;
					}
					if (p2 == 0)
					{
						try
						{
							if(battleField[i+1][j-1] == 2) {
								p1 ++;
								i = Y;
								j = X;
							}
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
						}
						try
						{
							if(battleField[i+1][j+1] == 2) {
								p1 ++;
								i = Y;
								j = X;
							}
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
						}
					}
				}
			}
		if(p1 == 0 || p2 == 0)
			return true;
		return false;
	}
	public void step(Step step)
	{
		battleField[step.getOldY()][step.getOldX()] = 0;
		battleField[step.getNewY()][step.getNewX()] = step.getPlayerNumber();
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

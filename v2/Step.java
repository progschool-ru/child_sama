import java.io.*;
import java.util.*;

class Step
{
	private int point;
	private int playerNumber;
	private int oldNumber;
	private int newNumber;
	private String BF;
	Step(int playerNumber, String BF)
	{
		this.playerNumber = playerNumber;
		this.BF = BF;
		oldNumber = -1;
	}
	public void setBF(String BF)
	{
		this.BF = BF;
	}
	public String getBF()
	{
		return BF;
	}
	public void setPoint(int point)
	{
		this.point = point;
	}
	public void setPlayerNumber(int playerNumber)
	{
		this.playerNumber = playerNumber;
	}
	public void setOldNumber(int n)
	{
		oldNumber = n;
	}
	public void setNewNumber(int n)
	{
		newNumber = n;
	}
	public int getPoint()
	{
		return point;
	}
	public int getPlayerNumber()
	{
		return playerNumber;
	}
	public int getOldNumber()
	{
		return oldNumber;
	}
	public int getNewNumber()
	{
		return newNumber;
	}
	public int getNC(int n)
	{
		char c[]= {BF.charAt(n)};
		String str = new String(c);
		return Integer.valueOf(str);
	}
	public void setNC(int n, int N)
	{
		char z = Integer.toString(N).charAt(0);
		char c[]= BF.toCharArray();
		c[n] = z;
		BF = new String(c);
	}
	public int getX(int n)
	{
		return n%3;
	}
	public int getY(int n)
	{
		return n/3;
	}
	public boolean getGameOver()
	{
  		for(int j = 0; j < 3; j++)
  			if(getNC(j) == 1)
 				return true;
 		for(int j = 6; j < 9; j++)
 			if(getNC(j) == 2)
  				return true;
 		if(getNumberOfSteps(getW(playerNumber)) == 0)
 			return true;
 		for(int j = 0; j < 9; j++)
			if(getNC(j) == playerNumber)
				return false;
		return true;
	}
	private boolean getPossHead(int n, int p)
	{
		if(n+3*getCourse(p) > 8 || n+3*getCourse(p) < 0)
			return false;
		if(getNC(n+3*getCourse(p)) != 0)
			return false;
		return true;
	}
	private boolean getPossLeft(int n, int p)
	{
		if(n%3 == 0)
			return false;
		if(getNC(n+2*getW(p)*getCourse(p)) == getW(p))
			return true;
		return false;
	}
	private boolean getPossRight(int n, int p)
	{
		if(n%3 == 2)
			return false;
		if(getNC(n+2*p*getCourse(p)) == getW(p))
			return true;
		return false;
	}
	public int getW(int p)
	{
		if(p == 1)
			return 2;
		return 1;
	}
	public int getCourse(int p)
	{
		if(p == 1)
			return -1;
		return 1;
	}
	public int getNumberOfSteps(int p)
	{
		int NumberOfSteps = 0;;
		for(int i = 0; i < 9; i++)
				if(getNC(i) == p)
				{
					if(getPossLeft(i ,p))
						NumberOfSteps++;
					if(getPossHead(i, p))
						NumberOfSteps++;
					if(getPossRight(i, p))
						NumberOfSteps++;
				}
		return  NumberOfSteps;
	}
	public Step[] getSteps(int p) throws IOException
	{
		Step[] steps = new Step [getNumberOfSteps(p)];
		int s = 0;
		for(int i = 0; i < 9; i++)
			if(getNC(i) == p)
			{
				if(getPossHead(i, p))
				{
					steps[s] = new Step(p, BF);
					steps[s].setOldNumber(i);
					steps[s].setNewNumber(i+3*getCourse(p));
					steps[s].setPoint(5);
					s++;
				}
				if(getPossLeft(i, p))
				{
					steps[s] = new Step(p, BF);
					steps[s].setOldNumber(i);
					steps[s].setNewNumber(i+2*getW(p)*getCourse(p));
					steps[s].setPoint(5);
					s++;
				}
				if(getPossRight(i, p))
				{
					steps[s] = new Step(playerNumber, BF);
					steps[s].setOldNumber(i);
					steps[s].setNewNumber(i+2*p*getCourse(p));
					steps[s].setPoint(5);
					s++;
				}
			}
		return steps;
	}
	public Step stepProc()
	{
		setNC(newNumber , playerNumber);
		setNC(oldNumber, 0);
		oldNumber = -1;
		newNumber = -1;
		playerNumber = getW(playerNumber);
		return this;
	}
}

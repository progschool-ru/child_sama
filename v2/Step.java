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
}

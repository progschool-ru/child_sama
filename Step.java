import java.io.*;
import java.util.*;

class Step
{
	private int point;
	private int playerNumber;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
	public void setPoint(int point)
	{
		this.point = point;
	}
	public void setPlayerNumber(int playerNumber)
	{
		this.playerNumber = playerNumber;
	}
	public void setOldX(int x)
	{
		oldX = x;
	}
	public void setOldY(int y)
	{
		oldY = y;
	}
	public void setNewX(int x)
	{
		newX = x;
	}
	public void setNewY(int y)
	{
		newY = y;
	}
	public int getPoint()
	{
		return point;
	}
	public int getPlayerNumber()
	{
		return playerNumber;
	}
	public int getOldX()
	{
		return oldX;
	}
	public int getOldY()
	{
		return oldY;
	}
	public int getNewX()
	{
		return newX;
	}
	public int getNewY()
	{
		return newY;
	}
}

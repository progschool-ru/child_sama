import java.io.*;
import java.util.*;

class DataSteps
{

	Step[] steps;
	private String BF;
	private int numberOfSteps;
	public void setNumberOfSteps(int numberOfSteps)
	{
		this.numberOfSteps = numberOfSteps;
	}
	public void setSteps(int numberOfSteps)
	{
		this.numberOfSteps = numberOfSteps;
		steps = new Step[numberOfSteps];
	}	
	public void setSteps(Step[] steps)
	{
		this.steps = steps;
	}	
	public int getNumberOfSteps()
	{
		return numberOfSteps;
	}
	public void setBF(String BF)
	{
		this.BF = BF;
	}
	public String getBF()
	{
		return BF;
	}
	public int getAllPoint()
	{
		int allPoint = 0;
		for(int i = 0; i < numberOfSteps; i++)
			allPoint = allPoint + steps[i].getPoint();
		return allPoint;
	}
}
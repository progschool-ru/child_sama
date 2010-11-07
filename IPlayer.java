import java.io.*;
import java.util.*;

interface IPlayer
{
	public Step doStep(Game g) throws IOException;
	public String getName();
}

abstract class Player implements IPlayer
{
	protected String name;
	protected int playerNumber;
	protected void nameInit(int playerNumber) throws IOException
	{
		this.playerNumber = playerNumber;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Введите имя "+playerNumber+" игрока");
		name = br.readLine();
		for(;;)
		{
			System.out.println("Имя "+playerNumber+" игрока - "+name+", вы уверены?(y / n)");
			String choice = br.readLine();
			if(choice.equals("y"))
				return;
			System.out.println("Повторите ввод");
			name = br.readLine();
		}
	}
	public String getName()
	{
		return name;
	}
}

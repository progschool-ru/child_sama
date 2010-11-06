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

class HumanPlayer extends Player
{
	public HumanPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Game g) throws IOException
	{
		Step step = new Step();
		step.setPlayerNumber(playerNumber);
		System.out.println("Сейчас ходит игрок №"+playerNumber+" - "+name);
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
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for (;;)
		{
			System.out.println("Введите координаты пешки которой вы хотите походить");
			String choice = br.readLine();
			try
			{
				String []XY = choice.split(" ");
				step.setOldX(Integer.valueOf(XY[0]));
				step.setOldY(Integer.valueOf(XY[1]));
			}
			catch (NumberFormatException e)
			{
				System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координтаы пешки");
				continue;
			}
			catch (ArrayIndexOutOfBoundsException e2)
			{
				System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координтаы пешки");
				continue;
			}
			if(g.getBattleField(step.getOldY(), step.getOldX()) != playerNumber)
			{
				System.out.println("На выбранной клетке нет вашей пешки");
				g.getBattleFieldInSystem();
				continue;
			}
			return step;
		}
	}
	protected Step choiceSquare(Game g, Step step)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			System.out.println("Введите координты клетки в которую вы хотите походить");
			System.out.println("Если вы желаете сменить пешку введите cp");
			String choice = br.readLine();
			if(choice.equals("cp"))
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
				System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координаты клетки");
				continue;
			}
			catch (ArrayIndexOutOfBoundsException e2)
			{
				System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координаты клетки");
				continue;
			}
			if(g.getBattleField(step.getNewY(), step.getNewX()) == playerNumber)
			{
				System.out.println("На выбранной клетке стоит ваша пешка");
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
					System.out.println("Ход невозможен");
					continue;
				}
				if(step.getNewX()-step.getOldX() != 0 && g.getBattleField(step.getNewY(), step.getNewX()) == 0)
				{
					System.out.println("Ход невозможен");
					continue;
				}
				if(step.getNewX()-step.getOldX() == 0 && g.getBattleField(step.getNewY(), step.getNewX()) != 0)
				{
					System.out.println("Ход невозможен");
					continue;
				}
			}
			else
			{
				System.out.println("Ход невозможен");
				continue;
			}
			return step;
		}
	}
}
/*class PCPlayer extends Player
{
	public PCPlayer(int playerNumber) throws IOException
	{
		nameInit(playerNumber);
	}
	public Step doStep(Game g) throws IOException
	{
		Step step = new Step();
		step.setPlayerNumber(playerNumber);
		System.out.println("Сейчас ходит игрок №"+playerNumber+" - "+name);
		g.getBattleFieldInSystem();
		step = choiceStep(g, step);
		return step;
	}
	protected Step choiceStep(Game g, Step step) throws IOException
	{

	}
}
*/
class Game
{
	private int X=3;
	private int Y=3;
	private boolean gameOver = false;
	private int[][] battleField;
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
			for(int j = 0; j < X; j++) {
				if(i == 0)
					battleField[i][j] = 2;
				else if(i == Y-1)
					battleField[i][j] = 1;
				else
					battleField[i][j] = 0;
			}
	}
}

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
class DataSteps
{

	Step[] steps;
	private String BF;
	private int numberOfSteps;
	public void setNumberOfSteps(int numberOfSteps)
	{
		this.numberOfSteps = numberOfSteps;
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
		int allPoint;
		for(int i = 0; i < numberOfSteps; i++)
			allPoint = allPoint + steps[i].getPoint;
		return allPoint;
	}
}
class Data
{
	int numberOfBF;
	DataSteps[] dataSteps;
	Data() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String str = br.readLine();
		numberOfBF = Integer.valueOf(str);
		dataSteps = new  DataSteps[numberOfBF];
		for (int i = 0; i < numberOfBF; i++)
		{
			dataSteps[i] = new DataSteps();
			String str1 = br.readLine();
			dataSteps[i].setBF(str1);
			String str2 = br.readLine();
			int numberOfSteps = Integer.valueOf(str2);
			dataSteps[i].steps = new Step[numberOfSteps];
			dataSteps[i].setNumberOfSteps(numberOfSteps);
			for(int j = 0; j < numberOfSteps; j++)
			{
				dataSteps[i].steps[j] = new Step();
				String str3 = br.readLine();
				String b[] = str3.split("\t");
				dataSteps[i].steps[j].setOldX(Integer.valueOf(b[0]));
				dataSteps[i].steps[j].setOldY(Integer.valueOf(b[1]));
				dataSteps[i].steps[j].setNewX(Integer.valueOf(b[2]));
				dataSteps[i].steps[j].setNewY(Integer.valueOf(b[3]));
				dataSteps[i].steps[j].setPoint(Integer.valueOf(b[4]));
			}
		}
	}
	public void save() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(numberOfBF + "\n");
		for (int i = 0; i < numberOfBF; i++)
		{
			pw.print(dataSteps[i].getBF() + "\n");
			pw.print(dataSteps[i].getNumberOfSteps() + "\n");
			for (int j = 0; j < dataSteps[i].getNumberOfSteps(); j++)
			{
				pw.print(dataSteps[i].steps[j].getOldX() + "\t");
				pw.print(dataSteps[i].steps[j].getOldY() + "\t");
				pw.print(dataSteps[i].steps[j].getNewX() + "\t");
				pw.print(dataSteps[i].steps[j].getNewY() + "\t");
				pw.print(dataSteps[i].steps[j].getPoint() + "\t");
			}
		}
		pw.close();
	}
	public void save(DataSteps newStep) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(numberOfBF + "\n");
		for (int i = 0; i < numberOfBF; i++)
		{
			pw.print(dataSteps[i].getBF() + "\n");
			pw.print(dataSteps[i].getNumberOfSteps() + "\n");
			for (int j = 0; j < dataSteps[i].getNumberOfSteps(); j++)
			{
				pw.print(dataSteps[i].steps[j].getOldX() + "\t");
				pw.print(dataSteps[i].steps[j].getOldY() + "\t");
				pw.print(dataSteps[i].steps[j].getNewX() + "\t");
				pw.print(dataSteps[i].steps[j].getNewY() + "\t");
				pw.print(dataSteps[i].steps[j].getPoint() + "\n");
			}
		}
		pw.print(newStep.getBF() + "\n");
		pw.print(newStep.getNumberOfSteps() + "\n");
		for (int j = 0; j < newStep.getNumberOfSteps(); j++)
		{
			pw.print(newStep.steps[j].getOldX() + "\t");
			pw.print(newStep.steps[j].getOldY() + "\t");
			pw.print(newStep.steps[j].getNewX() + "\t");
			pw.print(newStep.steps[j].getNewY() + "\t");
			pw.print(newStep.steps[j].getPoint() + "\n");
		}
		pw.close();
	}
}
class SAMA
{
	private static final String GAME = "game";
	private static final String NEW = "new";
	private static final String EXIT = "exit";
	public static void main(String []args) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean Exit=false;
		while(Exit == false) {
			String choice=br.readLine();
			if(choice.equals(GAME))
				game();
			else if(choice.equals(NEW))
			{
			}
			else if(choice.equals(EXIT))
				Exit=true;
		}
	}
	public static void game() throws IOException
	{
		Game g = new Game();
		Step step = new Step();
		g.setNewBattleField();
		IPlayer p1 = new HumanPlayer(1);
		IPlayer p2 = new HumanPlayer(2);
		int numberOfTheWinner = 0;
		String theWinner = "Победителя нет";
		while(!g.getGameOver(step.getPlayerNumber()))
		{
			step = p1.doStep(g);
			g.step(step);
			if(g.getGameOver(step.getPlayerNumber()))
			{
				numberOfTheWinner = 1;
				theWinner = p1.getName();
				break;
			}
			step = p2.doStep(g);
			g.step(step);
		}
		if(numberOfTheWinner == 0)
		{
			numberOfTheWinner = 2;
			theWinner = p2.getName();
		}
		System.out.print("Победил игрок №"+numberOfTheWinner+" - "+theWinner);
	}
}
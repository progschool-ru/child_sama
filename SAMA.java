import java.io.*;
import java.util.*;

interface IPlayer 
{
	public Step doStep(Game g) throws IOException;
}

abstract class Player implements IPlayer
{
	protected void someMethod()
	{
	}
}

class HumanPlayer extends Player {
	private String name;
	private int playerNumber;
	public HumanPlayer(int playerNumber) throws IOException 
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
	public Step doStep(Game g) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Step step = new Step();
		step.setPlayerNumber(playerNumber);
		System.out.println("Сейчас ходит игрок №"+playerNumber+" - "+name);
		g.getBattleFieldInSystem();
		for (;;) {
			System.out.println("Введите координты пешки которй вы хотите походить");
			String choice = br.readLine();
                        try {
                        	String []XY = choice.split(" ");
				step.setOldX(Integer.valueOf(XY[0]));
				step.setOldY(Integer.valueOf(XY[1]));
			}
                        catch (NumberFormatException e) {
                        	System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координты пешки");
				continue;		
			} 
			catch (ArrayIndexOutOfBoundsException e2) {		
                        	System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координты пешки");
				continue;		

			}
			if(g.getBattleField(step.getOldY(), step.getOldX()) != playerNumber) {
				System.out.println("На выбранной клетке нет вашей пешки");
				g.getBattleFieldInSystem();
				continue;
					
			} 
			break;
		}
                for (;;) {
			System.out.println("Введите координты клетки в которую вы хотите походить");
			String choice = br.readLine();
                        try {
                        	String []XY = choice.split(" ");
				step.setNewX(Integer.valueOf(XY[0]));
				step.setNewY(Integer.valueOf(XY[1]));
			}
                        catch (NumberFormatException e) {
                        	System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координты клетки");
				continue;		
			} 
			catch (ArrayIndexOutOfBoundsException e2) {		
                        	System.out.println("Вводится так:");
				System.out.println("x y");
				System.out.println("где x y - координаты клетки");
				continue;		

			}
			int m;
			if(playerNumber == 1)
				m=-1;
			else
				m=1;
			if(step.getNewY()-step.getOldY()==m)	
			{
				if(step.getNewX()-step.getOldX() > 1 || step.getNewX()-step.getOldX() < -1) {
					System.out.println("Ход невозможен");
					continue;
				}
				if(g.getBattleField(step.getNewY(), step.getNewX()) == playerNumber) {
					System.out.println("На выбранной клетке стоит ваша пешка");
					g.getBattleFieldInSystem();
					continue;
				}
				if(step.getNewX()-step.getOldX() != 0 && g.getBattleField(step.getNewY(), step.getNewX()) == 0) {
					System.out.println("Ход невозможен");
					continue;
				}
			} 
			else
			{
				System.out.println("Ход невозможен");
				continue;		
			}
			break;
		}
		return step;
	}
}
class Step {
	private int playerNumber;
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	public void setOldX(int x) {
		oldX = x;
	}
	public void setOldY(int y) {
		oldY = y;
	}
	public void setNewX(int x) {
		newX = x;
	}
	public void setNewY(int y) {
		newY = y;
	}
	public int getPlayerNumber() {
		return playerNumber;
	}
	public int getOldX() {
		return oldX;
	}
	public int getOldY() {
		return oldY;
	}
	public int getNewX() {
		return newX;
	}
	public int getNewY() {
		return newY;
	}
}
class Game {
	private int X=3;
	private int Y=3;
	private boolean gameOver = false;
	private int[][] battleField;
	public boolean getGameOver() {
			return gameOver;
	}
	public void step(Step step) {
		battleField[step.getOldY()][step.getOldY()] = 0;
		battleField[step.getNewY()][step.getNewY()] = step.getPlayerNumber();	
	}
	public int getBattleField(int y, int x) {
		try {
			return battleField[y][x];
		}
		catch (ArrayIndexOutOfBoundsException e) {		
			return -1;
		}
	}
	public void getBattleFieldInSystem() {
			for(int i = 0; i < Y; i++) {
				for(int j = 0; j < X; j++)
					System.out.print(battleField[i][j]+" ");
				System.out.println();
			}
	}
	public void setNewBattleField() {
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
class SAMA {
	private static final String GAME = "game";
	private static final String NEW = "new";
	private static final String EXIT = "exit";
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean Exit=false;
		while(Exit == false) {
			String choice=br.readLine();
			if(choice.equals(GAME))
				game();
			else if(choice.equals(NEW)) {
			}
			else if(choice.equals(EXIT))
				Exit=true;
		}
	}
	public static void game() throws IOException {
		Game g = new Game();
		Step step = new Step();
		g.setNewBattleField();
		IPlayer p1 = new HumanPlayer(1);
		IPlayer p2 = new HumanPlayer(2);
		while(!g.getGameOver()) {
			g.step(p1.doStep(g));
			if(g.getGameOver())
				return;
			g.step(p2.doStep(g));
		}
		return;
	}
}
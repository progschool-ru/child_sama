import java.io.*;
import java.util.*;

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
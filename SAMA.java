import java.io.*;
import java.util.*;

interface Player {
	public Step doStep(Game g) throws IOException;
}
class HumanPlayer implements Player {
	private String name;
	private int playerNumber;
	public HumanPlayer(int playerNumber) throws IOException {
		this.playerNumber = playerNumber;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Введите имя "+playerNumber+" игрока");
		name = br.readLine();
		for(;;) {
			System.out.println("Имя "+playerNumber+" игрока - "+name+", вы уверены?(y / n)");
			String choice = br.readLine();
			if(choice.equals("y"))
				return;
			System.out.println("Повторите ввод");
			name = br.readLine();
		}
	}
	public Step doStep(Game g) {
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Step step = new Step();
		System.out.println("Сейчас ходит игрок №"+playerNumber+" - "+name);
		g.getBattleFieldInSystem();
//		String choice = br.readLine();



		return step;
	}
}
class Step {
	private int oldX;
	private int oldY;
	private int newX;
	private int newY;
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
	public boolean step(Step step) {
		return true;
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
		Player p1 = new HumanPlayer(1);
		Player p2 = new HumanPlayer(2);
		while(!g.getGameOver()) {
			boolean possible = false;
			while(!possible) {
				step = p1.doStep(g);
				possible = g.step(step);
			}
			if(g.getGameOver())
				return;
			possible = false;
			while(!possible) {
				step = p2.doStep(g);
				possible = g.step(step);
			}
		}
		return;
	}
}
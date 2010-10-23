import java.io.*;
import java.util.*;

class Pawn {
	private int left;
	private int up;
	private int right;
	public void setLeft(int left) {
		this.left = left;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getLeft() {
		return left;
	}
	public int getUp() {
		return up;
	}
	public int getRight() {
		return right;
	}
	public int getPoints() {
		return left+up+right;
	}
}
class Move
{
	private int X=3;
	private int Y=3;
	private boolean gameOver = false;
	private int[][] busy;
	Pawn[] pawns;
	public void setGameOver( boolean gameOver) {
		this.gameOver = gameOver;
	}
	public boolean getGameOver( ) {
		return gameOver;
	}
	public void setBusy() {
		busy = new int [Y][X];
	}
	public void setBusy(String []a) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				busy[i][j] = Integer.valueOf(a[i*X + j]);
	}
	public void setBusy(int []a) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				busy[i][j] = a[i*X + j];
	}
	public void setBusy(int y, int x, int p) {
				busy[y][x] = p;
	}
	public void setNewBusy() {
		busy = new int [Y][X];
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++) {
				if(i == 0)
					busy[i][j] = 2;
				else if(i == Y-1)
					busy[i][j] = 1;
				else
					busy[i][j] = 0;
			}
	}
	public int getBusy(int y, int x) {
		return busy[y][x];
	}
	public int[][] getBusy() {
		return busy;
	}
	public int getPawn() {
		int pawn=0;
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(busy[i][j] == 2)
					pawn++;
		return pawn;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
	}
	public int getPawnX(int numberOfPawns) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(busy[i][j] == 2) {
					numberOfPawns--;
					if(numberOfPawns == 0)
						return j;
				}
		return -1;
	}
	public int getPawnY(int numberOfPawns) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(busy[i][j] == 2) {
					numberOfPawns--;
					if(numberOfPawns == 0)
						return i;
				}
		return -1;
	}
	public int getPPawnX(int numberOfPawns) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(busy[i][j] == 1) {
					numberOfPawns--;
					if(numberOfPawns == 0)
						return j;
				}
		return -1;
	}
	public int getPPawnY(int numberOfPawns) {
		for(int i = 0; i < Y; i++)
			for(int j = 0; j < X; j++)
				if(busy[i][j] == 1) {
					numberOfPawns--;
					if(numberOfPawns == 0)
						return i;
				}
		return -1;
	}
}

class Data
{
	int pawn;
	int move;
	Move[] moves;
	Data() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String str1 = br.readLine();
		move = Integer.valueOf(str1);
		moves = new Move[move];
		for (int i = 0; i < move; i++) {
			moves[i] = new Move();
			String str2 = br.readLine();
			String a[] = str2.split("\t");
			moves[i].setBusy();
			moves[i].setBusy(a);
			pawn=moves[i].getPawn();
			moves[i].pawns = new Pawn[pawn];
			for(int l = 0; l < pawn; l++) {
				moves[i].pawns[l] = new Pawn();
				String str3 = br.readLine();
				String b[] = str3.split("\t");
				moves[i].pawns[l].setLeft(Integer.valueOf(b[0]));
				moves[i].pawns[l].setUp(Integer.valueOf(b[1]));
				moves[i].pawns[l].setRight(Integer.valueOf(b[2]));
			}
		}
	}
	public void save() throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(move + "\n");
		for (int i = 0; i < move; i++) {
			for (int j = 0; j < moves[i].getY(); j++)
				for (int l = 0; l < moves[i].getX(); l++)
					pw.print(moves[i].getBusy(j, l) + "\t");
			pw.print("\n");
			for (int j = 0; j < pawn; j++) {
				pw.print(moves[i].pawns[j].getLeft() + "\t");
				pw.print(moves[i].pawns[j].getUp() + "\t");
				pw.print(moves[i].pawns[j].getRight() + "\n");
			}
		}
		pw.close();
	}
	public void save(Move newMove) throws IOException
	{
		PrintWriter pw = new PrintWriter(new File("data.txt"));
		pw.print(move+1 + "\n");
		for (int i = 0; i < move; i++) {
			for (int j = 0; j < moves[i].getY(); j++)
				for (int l = 0; l < moves[i].getX(); l++)
					pw.print(moves[i].getBusy(j, l) + "\t");
			pw.print("\n");
			for (int j = 0; j < pawn; j++) {
				pw.print(moves[i].pawns[j].getLeft() + "\t");
				pw.print(moves[i].pawns[j].getUp() + "\t");
				pw.print(moves[i].pawns[j].getRight() + "\n");
			}
		}
		for (int j = 0; j < newMove.getY(); j++)
			for (int l = 0; l < newMove.getX(); l++)
				pw.print(newMove.getBusy(j, l) + "\t");
		pw.print("\n");
		for (int j = 0; j < pawn; j++) {
			pw.print(newMove.pawns[j].getLeft() + "\t");
			pw.print(newMove.pawns[j].getUp() + "\t");
			pw.print(newMove.pawns[j].getRight() + "\n");
		}
		pw.close();
	}
}

class SAMA {
	private static final String GAME = "game";
	private static final String NEW = "new";
	private static final String EXIT = "exit";
	private static final String UP = "u";
	private static final String LEFT = "l";
	private static final String RIGHT = "r";
	public static void main(String []args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean Exit=false;
		while(Exit==false) {
			String choice=br.readLine();
			if(choice.equals(GAME)) {
				game();
			}
			else if(choice.equals(NEW)) {
			}
			else if(choice.equals(EXIT))
				Exit=true;
		}
	}
	public static void game() throws IOException {
		Move move = new Move();
		move.setNewBusy();
		boardInSystem(move);
		while(!move.getGameOver()) {
			move = PMove(move);
			boardInSystem(move);
			if(move.getGameOver())
				return;
			move = SMove(move);
			boardInSystem(move);
		}
		saveSMove();
	}
	public static Move PMove(Move move) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String choice=br.readLine();
		String a[] = choice.split(" ");
		int numberOfPawns = Integer.valueOf(a[0]);
		int x = move.getPPawnX(numberOfPawns);
		int y = move.getPPawnY(numberOfPawns);
		move.setBusy(y, x, 0);
		if(a[1].equals(UP))
			move.setBusy(y-1, x, 1);
		else if(a[1].equals(LEFT))
			move.setBusy(y-1, x-1, 1);
		else
			move.setBusy(y-1, x+1, 1);
		return move;
	}
	public static Move SMove(Move move) throws IOException {
		return move;
	}
	public static void saveSMove() throws IOException {

	}
	public static void boardInSystem(Move move) {
		for(int i = 0; i < move.getY(); i++) {
			for(int j = 0; j < move.getX(); j++)
				System.out.print(move.getBusy(i, j)+" ");
			System.out.println();
		}
	}
	public static boolean sM(int a[], int b[])  {
		if (a.length!=b.length)
			return false;
		for(int i = 0; i < a.length; i++)
			if (a[i] != b[i])
				return false;
		return true;
	}
}
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
	public int getLeft()
	{
		return left;
	}
	public int getUp()
	{
		return up;
	}
	public int getRight()
	{
		return right;
	}
	public int getPoints()
	{
		return left+up+right;
	}
}
class Move
{
	private int X=3;
	private int Y=3;
	private int[] busy;
	Pawn[] pawns;
	public void setBusy(String []a) {
		for(int i = 0; i < a.length; i++)
			busy[i]=Integer.valueOf(a[i]);
	}
	public void setBusy(int []a) {
		for(int i = 0; i < a.length; i++)
			busy[i]=a[i];
	}
	public void setBusy() {
		busy = new int [X*Y];
	}
	public int getBusy(int i) {
		return busy[i];
	}
	public int getBusyLength() {
		return busy.length;
	}
	public int getPawn() {
		int pawn=0;
		for(int i = 0; i < X*Y; i++)
			if(busy[i] == 1)
				pawn++;
		return pawn;
	}
	public int getX() {
		return X;
	}
	public int getY() {
		return Y;
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
			for (int j = 0; j < moves[i].getBusyLength(); j++)
				pw.print(moves[i].getBusy(j) + "\t");
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
			for (int j = 0; j < moves[i].getBusyLength(); j++)
				pw.print(moves[i].getBusy(j) + "\t");
			pw.print("\n");
			for (int j = 0; j < pawn; j++) {
				pw.print(moves[i].pawns[j].getLeft() + "\t");
				pw.print(moves[i].pawns[j].getUp() + "\t");
				pw.print(moves[i].pawns[j].getRight() + "\n");
			}
		}
		for (int j = 0; j < newMove.getBusyLength(); j++)
			pw.print(newMove.getBusy(j) + "\t");
		pw.print("\n");
		for (int j = 0; j < pawn; j++) {
			pw.print(newMove.pawns[j].getLeft() + "\t");
			pw.print(newMove.pawns[j].getUp() + "\t");
			pw.print(newMove.pawns[j].getRight() + "\n");
		}
		pw.close();
	}
}

class SAMA
{
	public static void main(String []args) throws IOException {
/*ÏÐÎÂÅÐÊÀ		Data var=new Data(); ÏÐÎÂÅÐÊÀ
		System.out.println(var.move);
		for(int i = 0; i < var.move; i++) {
			System.out.println(var.moves[i].getBusy(0)+" "+var.moves[i].getBusy(3)+" "+var.moves[i].getBusy(6));
			System.out.println(var.moves[i].pawns[0].getRight());
		}
		Move newMove = new Move();
		newMove.setBusy();
		int a[]={2,2,2,0,0,0,1,1,1}, pawn;
		newMove.setBusy(a);
		pawn=newMove.getPawn();
		newMove.pawns = new Pawn[pawn];
		for(int l = 0; l < pawn; l++) {
			newMove.pawns[l] = new Pawn();
			newMove.pawns[l].setLeft(15);
			newMove.pawns[l].setUp(7);
			newMove.pawns[l].setRight(9);
		}
		var.save();
		var.save(newMove);
*/
	}
}
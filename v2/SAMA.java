import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SAMA extends Frame
{
	int mouseX = 0, mouseY = 0;
	int set[][] = {{0, 30},{100,30},{200,30},{0, 130},{100, 130},{200, 130},{0, 230},{100, 230},{200, 230}};
	int tab[][] = {{10, 40},{110,40},{210,40},{10, 140},{110, 140},{210, 140},{10, 240},{110, 240},{210, 240}};
	List l;
	Step step;
	IPlayer p1;
	IPlayer p2;
	boolean gameOver = false;
    public SAMA() throws IOException
	{
		addWindowListener(new MyWindowAdapter(this));
		addMouseListener(new MyMouseAdapter(this));
	}
	public static void main(String args[]) throws IOException
	{
		SAMA appwin = new SAMA();
		appwin.l = new List();
		appwin.l.newList();
		appwin.step = new Step(1, "222000111");
		appwin.p1 = new HumanPlayer(1);
		appwin.p2 = new PCPlayer(2);
		appwin.setSize(450, 370);
		appwin.setTitle("SAMA");
		appwin.setVisible(true);
	}
	public void paint(Graphics g)
	{
		if(gameOver)
		{
			if(step.getPlayerNumber() == 1)
				g.drawString("Игра окончена, победили белые",10,352);
			else
				g.drawString("Игра окончена, победили черные",10,352);
		}
		else
		{
			if(step.getPlayerNumber() == 1)
				g.drawString("Ходят черные",10,342);
			else
				g.drawString("Ходят белые",10,342);
			if(step.getOldNumber() == -1)
				g.drawString("Пешка не выбранна",10,362);
			else
			{
				g.drawString("Пешка выбранна",10,362);
				g.setColor(Color.green);
				g.fillRect(set[step.getOldNumber()][0], set[step.getOldNumber()][1], 100, 100);
			}
		}
		g.setColor(Color.black);
		g.drawLine(100, 0, 100, 330);
		g.drawLine(200, 0, 200, 330);
		g.drawLine(300, 0, 300, 370);
		g.drawLine(0, 130, 300, 130);
		g.drawLine(0, 230, 300, 230);
		g.drawLine(0, 330, 300, 330);
		for(int i = 0 ; i < 9; i++) {
			char c = step.getBF().charAt(i);
			if (c == '2')
			{
				g.setColor(Color.white);
				g.fillOval(tab[i][0], tab[i][1], 80, 80);
				g.setColor(Color.black);
				g.drawOval(tab[i][0], tab[i][1], 80, 80);
			}
			else if (c == '1')
				g.fillOval(tab[i][0], tab[i][1], 80, 80);
		}
	}
}
class MyMouseAdapter extends MouseAdapter {
	SAMA s;
	int set[][] = {{0, 30},{100,30},{200,30},{0, 130},{100, 130},{200, 130},{0, 230},{100, 230},{200, 230}};
	public MyMouseAdapter(SAMA s) throws IOException
	{
		this.s = s;
	}
	public void mouseClicked(MouseEvent me)
	{
		if(!s.gameOver)
		{
			for(int i = 0; i < 9; i++)
				if(me.getX() > set[i][0] && me.getX() < set[i][0]+100 && me.getY() > set[i][1] && me.getY() < set[i][1]+100)
				{
					try
					{
						s.step = s.p1.doStep(s.step, i);
						if(!s.step.getGameOver())
							s.step = s.p2.doStep(s.step, i);
					}
					catch(IOException ex) {}
				}
		}
		s.gameOver = s.step.getGameOver();
		s.repaint();
	}
}

class MyWindowAdapter extends WindowAdapter
{
	SAMA s;
	public MyWindowAdapter(SAMA s) throws IOException
	{
		this.s = s;
	}
	public void windowClosing(WindowEvent we)
	{
		try
		{
			s.p2.carrotAndStick(s.step.getW(s.step.getPlayerNumber()));
		}
		catch(IOException ex) {}
		System.exit(0);
	}
}
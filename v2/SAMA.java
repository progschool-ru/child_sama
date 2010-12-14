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
//	list l;
//	Game g;
	Step step;
	IPlayer p1;
	IPlayer p2;
    public SAMA() throws IOException
	{
		addWindowListener(new MyWindowAdapter());
		addMouseListener(new MyMouseAdapter(this));
	}
	public static void main(String args[]) throws IOException
	{
		SAMA appwin = new SAMA();
/*		appwin.l = new list();
		appwin.l.newList(); */
//		appwin.g = new Game();
		appwin.step = new Step(2, "111000222");
		appwin.p1 = new HumanPlayer(1);
		appwin.p2 = new HumanPlayer(2);
		appwin.setSize(450, 350);
		appwin.setTitle("SAMA");
		appwin.setVisible(true);
	}
	public void paint(Graphics g)
	{
		if(step.getOldNumber() == -1)
			g.drawString("Пешка не выбранна",10,342);
		else
		{
			g.drawString("Пешка выбранна",10,342);
			g.setColor(Color.green);
			g.fillRect(set[step.getOldNumber()][0], set[step.getOldNumber()][1], 100, 100);
		}
		g.setColor(Color.black);
		g.drawLine(100, 0, 100, 330);
		g.drawLine(200, 0, 200, 330);
		g.drawLine(300, 0, 300, 350);
		g.drawLine(0, 130, 300, 130);
		g.drawLine(0, 230, 300, 230);
		g.drawLine(0, 330, 300, 330);
		for(int i = 0 ; i < 9; i++) {
			char c = step.getBF().charAt(i);
			if (c == '1')
			{
				g.setColor(Color.white);
				g.fillOval(tab[i][0], tab[i][1], 80, 80);
				g.setColor(Color.black);
				g.drawOval(tab[i][0], tab[i][1], 80, 80);
			}
			else if (c == '2')
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
		for(int i = 0; i < 9; i++)
			if(me.getX() > set[i][0] && me.getX() < set[i][0]+100 && me.getY() > set[i][1] && me.getY() < set[i][1]+100)
			{
				if(s.step.getPlayerNumber() == 1)
				{
					try
					{
						s.step = s.p1.doStep(s.step, i);
					}
					catch(IOException ex) {}
				}
				else
				{
					try
					{
						s.step = s.p2.doStep(s.step, i);
					}
					catch(IOException ex) {}
				}
			}
		s.repaint();
	}
}

class MyWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
}
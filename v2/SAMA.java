import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SAMA extends Frame implements ActionListener
{
	int mouseX = 0, mouseY = 0;
	int set[][] = {{0, 30},{100,30},{200,30},{0, 130},{100, 130},{200, 130},{0, 230},{100, 230},{200, 230}};
	int tab[][] = {{10, 40},{110,40},{210,40},{10, 140},{110, 140},{210, 140},{10, 240},{110, 240},{210, 240}};
	List l;
	Step step;
	IPlayer p1;
	IPlayer p2;
	Button yes, no;
	boolean butOff = true;
	boolean gameOver = false;
	public void actionPerformed(ActionEvent ae)
	{
		String str = ae.getActionCommand();
		if(gameOver)
		{
			try
			{
				p2.carrotAndStick(step.getW(step.getPlayerNumber()));
			}
			catch(IOException ex) {}
		}
		if(str.equals("ДА"))
		{
			try
			{
				l.newList();
			}
			catch(IOException ex) {}
			step.setPlayerNumber(1);
			step.setBF("222000111");
			remove(yes);
			remove(no);
			gameOver = false;
			butOff = true;
			repaint();
		}
		else if(str.equals("НЕТ"))
		{
			System.exit(0);
		}
	}
    public SAMA() throws IOException
	{
		addWindowListener(new SAMAWindowAdapter(this));
		addMouseListener(new SAMAMouseAdapter(this));
	}
	public static void main(String args[]) throws IOException
	{
		SAMA s = new SAMA();
		s.l = new List();
		s.l.newList();
		s.step = new Step(1, "222000111");
		s.p1 = new HumanPlayer(1);
		s.p2 = new PCPlayer(2);

		s.setLayout(null);
		s.yes = new Button ("ДА");
		s.no = new Button ("НЕТ");
		s.yes.setBounds(310, 80, 60, 30);
		s.no.setBounds(380, 80, 60, 30);
		s.yes.addActionListener(s);
		s.no.addActionListener(s);

		s.setSize(450, 370);
		s.setTitle("SAMA");
		s.setVisible(true);
	}
	public void paint(Graphics g)
	{
		if(gameOver)
		{
			if(butOff)
			{
				add(yes);
				add(no);
				butOff = false;
			}
			g.drawString("Хотите",355,45);
			g.drawString("сыграть еще раз?",325,65);
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
		for(int i = 0 ; i < 9; i++)
		{
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
class SAMAMouseAdapter extends MouseAdapter {
	SAMA s;
	int set[][] = {{0, 30},{100,30},{200,30},{0, 130},{100, 130},{200, 130},{0, 230},{100, 230},{200, 230}};
	public SAMAMouseAdapter(SAMA s) throws IOException
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

class SAMAWindowAdapter extends WindowAdapter
{
	SAMA s;
	public SAMAWindowAdapter(SAMA s) throws IOException
	{
		this.s = s;
	}
	public void windowClosing(WindowEvent we)
	{
		if(s.gameOver)
		{
			try
			{
				s.p2.carrotAndStick(s.step.getW(s.step.getPlayerNumber()));
			}
			catch(IOException ex) {}
		}
		System.exit(0);
	}
}
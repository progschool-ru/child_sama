import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SAMA extends Frame implements ActionListener, ItemListener
{
	int mouseX = 0, mouseY = 0;
	final int OVAL_PADDING = 10;
	int set[][] = {{0, 30},{100,30},{200,30},{0, 130},{100, 130},{200, 130},{0, 230},{100, 230},{200, 230}};
	List l;
	Text text;
	Step step;
	IPlayer p1;
	IPlayer p2;
	Button yes, no;
	CheckboxGroup GBG;
	Checkbox graphOn, graphOff;
	CheckboxGroup OppBG;
	Checkbox PC, Human;
	boolean butOff = true;
	boolean gameOver = false;
	Graph graph;

    public SAMA() throws IOException
	{
		addWindowListener(new SAMAWindowAdapter(this));
		addMouseListener(new SAMAMouseAdapter(this));
	}
	public static void main(String args[]) throws IOException
	{
		FilesToucher ft = new FilesToucher();
		ft.touch();
		
		SAMA s = new SAMA();
		s.graph = new Graph();
		s.l = new List();
		s.l.newList();
		s.text = new Text();
		s.step = new Step(1, "222000111");

		s.p1 = new HumanPlayer(1);
		s.p2 = new PCPlayer(2);

		s.setLayout(null);
		s.yes = new Button (s.text.YES);
		s.no = new Button (s.text.NO);
		s.OppBG = new CheckboxGroup();
		s.Human = new Checkbox(s.text.HUMAN_ON, false , s.OppBG);
		s.PC = new Checkbox(s.text.PC_ON, true , s.OppBG);
		s.Human.setBounds(310, 220, 130, 30);
		s.PC.setBounds(310, 250, 130, 30);
		s.add(s.Human);
		s.add(s.PC);
		s.GBG = new CheckboxGroup();
		s.graphOn = new Checkbox(s.text.GRAPH_ON, false , s.GBG);
		s.graphOff = new Checkbox(s.text.GRAPH_OFF, true , s.GBG);
		s.graphOn.setBounds(310, 310, 130, 30);
		s.graphOff.setBounds(310, 340, 130, 30);
		s.add(s.graphOn);
		s.add(s.graphOff);
		s.yes.setBounds(310, 80, 60, 30);
		s.no.setBounds(380, 80, 60, 30);
		s.yes.addActionListener(s);
		s.no.addActionListener(s);
		s.Human.addItemListener(s);
		s.PC.addItemListener(s);
		s.graphOn.addItemListener(s);
		s.graphOff.addItemListener(s);
		s.yes.setActionCommand("yes");
		s.no.setActionCommand("no");

		s.graph.setSize(s.graph.DG.WIDTH*s.graph.DG.NET+3*s.graph.DG.INDENT, s.graph.DG.HEIGHT*s.graph.DG.NET+3*s.graph.DG.INDENT);
		s.graph.setTitle("Graph");
		s.graph.setVisible(false);
		s.setSize(450, 370);
		s.setTitle("SAMA");
		s.setVisible(true);
	}
	public void paint(Graphics g)
	{
		g.drawString(text.OPPONENT, 315, 210);
		if(gameOver)
		{
			if(butOff)
			{
				add(yes);
				add(no);
				butOff = false;
			}
			g.drawString(text.YOU_WANT, 355, 45);
			g.drawString(text. PLAY_MORE, 325, 65);
			if(step.getPlayerNumber() == 1)
				g.drawString(text.WIN_WHITE, 10, 352);
			else
				g.drawString(text.WIN_BLACK, 10, 352);
		}
		else
		{
			if(step.getPlayerNumber() == 1)
				g.drawString(text.NEXT_BLACK, 10, 342);
			else
				g.drawString(text.NEXT_WHITE, 10, 342);
			if(step.getOldNumber() == -1)
				g.drawString(text.PAWN_IS_NOT_SELECTED, 10, 362);
			else
			{
				g.drawString(text.PAWN_SELECTED, 10, 362);
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
				g.fillOval(getTab(i, 0), getTab(i, 1), 80, 80);
				g.setColor(Color.black);
				g.drawOval(getTab(i, 0), getTab(i, 1), 80, 80);
			}
			else if (c == '1')
				g.fillOval(getTab(i, 0), getTab(i, 1), 80, 80);
		}
	}
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
		if(str.equals("yes"))
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
		else if(str.equals("no"))
		{
			System.exit(0);
		}
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(graphOn.getState())
			graph.setVisible(true);
		if(graphOff.getState())
			graph.setVisible(false);
		if(PC.getState())
			try
			{
				p2 = new PCPlayer(2);
			}
			catch(IOException ex) {}
		if(Human.getState())
			try
			{
				p2 = new HumanPlayer(2);
			}
			catch(IOException ex) {}
	}

	public int getTab(int i, int j)
	{
		return set[i][j] + OVAL_PADDING;
	}
}
class SAMAMouseAdapter extends MouseAdapter {
	SAMA s;
	public SAMAMouseAdapter(SAMA s) throws IOException
	{
		this.s = s;
	}
	public void mouseClicked(MouseEvent me)
	{
		if(!s.gameOver)
		{
			for(int i = 0; i < 9; i++)
				if(me.getX() > s.set[i][0] && me.getX() < s.set[i][0]+100 && me.getY() > s.set[i][1] && me.getY() < s.set[i][1]+100)
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
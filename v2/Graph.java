import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Graph extends Frame implements ActionListener, AdjustmentListener
{
	DataForGraph DG;
	int[][] y;
	Scrollbar SB;
    public Graph() throws IOException
	{
		setLayout(null);
		DG = new DataForGraph();
		addWindowListener(new GraphWindowAdapter());
		SB = new Scrollbar(Scrollbar.HORIZONTAL, 0, 20, 0, DG.getNumberOfGames() - DG.WIDTH + 20);
		SB.setBounds(DG.INDENT, DG.HEIGHT*DG.NET + DG.INDENT + 15, DG.WIDTH*DG.NET, 15);
		add(SB);
		SB.addAdjustmentListener(this);
	}
	public void paint(Graphics g)
	{
		try
		{
			DG.update();
		}
		catch(IOException ex) {}
		if(SB.getMaximum() != DG.getNumberOfGames() - DG.WIDTH + 20)
			SB.setValues(0, 20, 0, DG.getNumberOfGames() - DG.WIDTH + 20);
		y = DG.getY(SB.getValue());
		g.setColor(Color.black);

/*		g.drawString("Номер игры", 270,375);
		g.drawLine(110, 383, 115, 388);
		g.drawLine(110, 384, 115, 389);
		g.drawLine(110, 385, 115, 390);
		g.drawString("- Победа игрока №1", 120,390);
		g.drawLine(390, 388, 395, 383);
		g.drawLine(390, 389, 395, 384);
		g.drawLine(390, 390, 395, 385);
		g.drawString("- Победа игрока №2", 400,390);
*/

		for(int i = DG.INDENT; i <= DG.WIDTH*DG.NET + DG.INDENT; i += DG.NET)
			if(i%DG.INDENT == 0)
			{
				g.drawLine(i, DG.INDENT, i, DG.HEIGHT*DG.NET + DG.INDENT+3);
				String str = Integer.toString((i-DG.INDENT)/5+SB.getValue());
				g.drawString(str, i, DG.HEIGHT*DG.NET + DG.INDENT+13);
			}
			else
				g.drawLine(i, DG.INDENT, i, DG.HEIGHT*DG.NET + DG.INDENT);
		for(int i = DG.INDENT; i <= DG.HEIGHT*DG.NET + DG.INDENT; i += 5)
			g.drawLine(DG.INDENT, i, DG.WIDTH*DG.NET + DG.INDENT, i);

		for( int i = 0; i < DG.WIDTH; i++)
		{
			g.drawLine(DG.INDENT+i*DG.NET, y[i][0]+1, DG.INDENT+(i+1)*DG.NET, y[i][1]+1);
			g.drawLine(DG.INDENT+i*DG.NET, y[i][0]  , DG.INDENT+(i+1)*DG.NET, y[i][1]);
			g.drawLine(DG.INDENT+i*DG.NET, y[i][0]-1, DG.INDENT+(i+1)*DG.NET, y[i][1]-1);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		repaint();
	}
	public void adjustmentValueChanged(AdjustmentEvent ae)
	{
		repaint();
	}
}
class GraphWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
}
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Graph extends Frame implements ActionListener, AdjustmentListener
{
	DataForGraph DG;
	int[][] y;
//	Button up;
	Scrollbar SB;
    public Graph() throws IOException
	{
		setLayout(null);
	//	up = new Button ("Обновить график");
	//	up.setBounds(245, 380, 130, 20);
	//	add(up);
	//	up.addActionListener(this);
		DG = new DataForGraph();
		addWindowListener(new GraphWindowAdapter());

		SB = new Scrollbar(Scrollbar.HORIZONTAL, 0, 20, 0, DG.getNumberOfGames()-80);
		SB.setBounds(50, 565, 500, 15);
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
		if(SB.getMaximum() != DG.getNumberOfGames()-80)
			SB.setValues(0, 20, 0, DG.getNumberOfGames()-80);
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

		for(int i = 50; i <= 550; i += 5)
			if(i%50 == 0)
			{
				g.drawLine(i, 50, i, 553);
				String str = Integer.toString((i-50)/5+SB.getValue());
				g.drawString(str, i,563);
			}
			else
				g.drawLine(i, 50, i, 550);
		for(int i = 50; i <= 550; i += 5)
			g.drawLine(50, i, 550, i);

		for( int i = 0; i < 100; i++)
		{
			g.drawLine(50+i*5, y[i][0]+1, 50+(i+1)*5, y[i][1]+1);
			g.drawLine(50+i*5, y[i][0], 50+(i+1)*5, y[i][1]);
			g.drawLine(50+i*5, y[i][0]-1, 50+(i+1)*5, y[i][1]-1);
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
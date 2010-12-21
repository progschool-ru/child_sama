import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Graph extends Frame
{
DataForGraph DG;
int[][] y;
    public Graph() throws IOException
	{
		addWindowListener(new GraphWindowAdapter());
	}
	public static void main(String args[]) throws IOException
	{
		Graph graph = new Graph();
		graph.DG = new DataForGraph();
		graph.y = graph.DG.getY();
		graph.setSize(600, 400);
		graph.setTitle("Graph");
		graph.setVisible(true);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawString("Номер игры", 270,375);
		g.drawLine(110, 383, 115, 388);
		g.drawLine(110, 384, 115, 389);
		g.drawLine(110, 385, 115, 390);
		g.drawString("- Победа игрока №1", 120,390);
		g.drawLine(390, 388, 395, 383);
		g.drawLine(390, 389, 395, 384);
		g.drawLine(390, 390, 395, 385);
		g.drawString("- Победа игрока №2", 400,390);
		for(int i = 50; i <= 550; i += 5)
			if(i%50 == 0)
			{
				g.drawLine(i, 50, i, 353);
				String str = Integer.toString((i-50)/5);
				g.drawString(str, i,363);
			}
			else
				g.drawLine(i, 50, i, 350);
		for(int i = 50; i <= 350; i += 5)
			g.drawLine(50, i, 550, i);

		for( int i = 0; i < DG.getNumberOfGames(); i++)
		{
			g.drawLine(50+i*5, y[i][0]+1, 50+(i+1)*5, y[i][1]+1);
			g.drawLine(50+i*5, y[i][0], 50+(i+1)*5, y[i][1]);
			g.drawLine(50+i*5, y[i][0]-1, 50+(i+1)*5, y[i][1]-1);
		}
	}
}
class GraphWindowAdapter extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
		System.exit(0);
	}
}
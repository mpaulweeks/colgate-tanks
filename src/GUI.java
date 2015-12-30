import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.List;
import java.util.*;

public class GUI extends JFrame
{
	private Panel pane;
	private Graphics2D graphics;
	private Image image;
	private Point center;
	public Keyboard myKeyboard;
	public Mouse myMouse;
	private Toolkit toolkit;
	
	public GUI ()
	{
		makeGUI(800, 600);
	}
	
	public GUI (int x, int y)
	{
		makeGUI(x,y);
	}
	
	public void makeGUI(int x, int y)
	{
		setTitle("Tank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = new Panel();
		pane.setPreferredSize(new Dimension(x, y));
		setContentPane(pane);
		pack();
		setVisible(true);
		makeListener();
	}
	
	public void makeListener()
	{
		myKeyboard = new Keyboard();
		myMouse = new Mouse();
		this.addKeyListener(myKeyboard);
		getContentPane().addMouseListener(myMouse);
		getContentPane().addMouseMotionListener(myMouse);
	}
	
	public void setVisible(boolean visible)
	{
		if(graphics == null)
		{
			image = pane.createImage(pane.getSize().width, pane.getSize().height);
			graphics = (Graphics2D)image.getGraphics();
			purge();
		}
		super.setVisible(visible);
	}
	
	public void purge()
	{
			graphics.setColor(Color.black);
			graphics.fill(new Rectangle(pane.getSize()));
	}
	
	public void drawAll(Tank Hero,ArrayList<Shell> Fire,ArrayList<Tank> Enemy,ArrayList<Explosion> Explodes,ArrayList<Bound> Bounds,ArrayList<Mine> Mines)
	{
		for(int i = 0; i < Bounds.size(); i++)
		{
			graphics.setColor(Bounds.get(i).getColor());
			graphics.fillPolygon(Bounds.get(i).getBody());
		}
		
		for(int i = 0; i < Explodes.size(); i++)
		{
			graphics.setColor(Explodes.get(i).getColor());
			graphics.fillOval(Explodes.get(i).getCorner().x, Explodes.get(i).getCorner().y, 2*Explodes.get(i).getRadius(), 2*Explodes.get(i).getRadius());
		}
		
		graphics.setColor(Color.white);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y-5,Mouse.point.x-25,Mouse.point.y-25);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y-5,Mouse.point.x-5,Mouse.point.y-15);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y-5,Mouse.point.x-15,Mouse.point.y-5);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y+5,Mouse.point.x-25,Mouse.point.y+25);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y+5,Mouse.point.x-5,Mouse.point.y+15);
		graphics.drawLine(Mouse.point.x-5,Mouse.point.y+5,Mouse.point.x-15,Mouse.point.y+5);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y-5,Mouse.point.x+25,Mouse.point.y-25);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y-5,Mouse.point.x+5,Mouse.point.y-15);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y-5,Mouse.point.x+15,Mouse.point.y-5);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y+5,Mouse.point.x+25,Mouse.point.y+25);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y+5,Mouse.point.x+5,Mouse.point.y+15);
		graphics.drawLine(Mouse.point.x+5,Mouse.point.y+5,Mouse.point.x+15,Mouse.point.y+5);
		
		graphics.setColor(Hero.getColor());
		graphics.drawPolygon(Hero.getBody());
		graphics.drawLine(Hero.getCenter().x,Hero.getCenter().y,Hero.getTurret().x,Hero.getTurret().y);
		
		for(int i = 0; i < Enemy.size(); i++)
		{
			graphics.setColor(Enemy.get(i).getColor());
			graphics.drawPolygon(Enemy.get(i).getBody());
			graphics.drawLine(Enemy.get(i).getCenter().x,Enemy.get(i).getCenter().y,Enemy.get(i).getTurret().x,Enemy.get(i).getTurret().y);
		}
		
		for(int i = 0; i < Mines.size(); i++)
		{
			graphics.setColor(Mines.get(i).getColor());
			graphics.fillPolygon(Mines.get(i).getBody());
		}
		
		for(int i = 0; i < Fire.size(); i++)
		{
			graphics.setColor(Fire.get(i).getColor());
			graphics.fillRect(Fire.get(i).getCenter().x-2,Fire.get(i).getCenter().y-2,4,4);
		}	
	}
	
	public void ready(int level, ArrayList<Bound> Bounds)
	{
		for(int i = 0; i < 4; i++)
		{
			graphics.setColor(Bounds.get(i).getColor());
			graphics.fillPolygon(Bounds.get(i).getBody());
		}
		graphics.setColor(Color.green);
		graphics.drawString("LEVEL " + level, 390, 310);
	}
	
	public void pause(ArrayList<Bound> Bounds)
	{
		for(int i = 0; i < 4; i++)
		{
			graphics.setColor(Bounds.get(i).getColor());
			graphics.fillPolygon(Bounds.get(i).getBody());
		}
		graphics.setColor(Color.green);
		graphics.drawString("PAUSE", 390, 310);
	}
	
	private class Panel extends JPanel
	{
		public void paint(Graphics g)
	    {
	        g.drawImage(image, 0, 0, null);
	    }
	}
}
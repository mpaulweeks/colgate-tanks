import java.awt.*;

public class Mine extends Body
{
	int life;
	Color oldColor;
	boolean active;
	
	public Mine(Point p, Color c)
	{
		center = new Point(p);
		radius = 10;
		active = false;
		body = new Polygon();
		body.addPoint(p.x,p.y-radius);
		body.addPoint(p.x-radius,p.y);
		body.addPoint(p.x,p.y+radius);
		body.addPoint(p.x+radius,p.y);
		color = Color.white;
		oldColor = c;
		life = 600;		
	}
	
	public void age()
	{
		life--;
		if(life == 500)
		{
			active = true;
			color = oldColor;
		}
		if(life <= 100 && life % 10 == 0)
		{
			if(color.equals(oldColor))
				color = Color.red;
			else
				color = oldColor;
		}
	}
	
	public Explosion getExplosion()
	{
		return new Explosion(center,radius*10,oldColor);
	}
	
	public int getLife()
	{
		return life;
	}
	
	public boolean getActive()
	{
		return active;
	}
}
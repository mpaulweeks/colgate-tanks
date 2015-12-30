import java.awt.*;

public class Explosion extends Body
{
	int life;
	double step, r, g, b;
	Point corner;
	
	public Explosion(Body b)
	{
		init(b.getCenter(),b.getRadius()*2,b.getColor());
	}
	
	public Explosion(Point p, int r, Color c)
	{
		init(p,r,c);
	}
	
	public void init(Point cen, int li, Color col)
	{
		center = new Point(cen.x, cen.y);
		corner = new Point(cen.x, cen.y);
		color = col;
		life = li;
		step = (double)200/(double)life;
		radius = 0;
		r = color.getRed();
		g = color.getGreen();
		b = color.getBlue();
	}
	
	public void age()
	{
		life--;
		radius++;
		corner.x--;
		corner.y--;
		if(r>50)	r-=step;
		if(g>50)	g-=step;
		if(b>50)	b-=step;
		color = new Color((int)r,(int)g,(int)b);
	}
	
	public Point getCorner() //override
	{
		return corner;
	}
	
	public Rectangle getRect() //override
	{
		return new Rectangle(corner.x,corner.y,2*radius,2*radius);
	}
	
	public boolean getAlive()
	{
		return life > 0;
	}
}
import java.awt.*;

public class Tank extends Body
{
	Point turret;
	double angle;
	boolean killed;
	int move;
	
	public Tank() //hero
	{
		killed = false;
		color = Color.pink;
		center = new Point(100,100);
		radius = 15;
		init();
	}
	
	public Tank(int id, int x, int y)
	{
		if(id == 1)
		{
			color = Color.green;
			center = new Point(x,y);
			radius = 15;
			init();
		}
		if(id == 2)
		{
			color = Color.magenta;
			center = new Point(x,y);
			radius = 30;
			init();
		}
	}
	
	public void init()
	{
		move = 0;
		turret = new Point(center.x+radius*2,center.y);
		body = new Polygon();
		body.addPoint(center.x-radius,center.y-radius);
		body.addPoint(center.x+radius,center.y-radius);
		body.addPoint(center.x+radius,center.y+radius);
		body.addPoint(center.x-radius,center.y+radius);		
	}
	
	public void moveVert(int s)
	{
		center.y += s*3;
		turret.y += s*3;
		for(int i = 0; i < body.npoints; i++)
		{
			body.ypoints[i] += s*3;
		}
		body = new Polygon(body.xpoints,body.ypoints,body.npoints);
	}
	
	public void moveHori(int s)
	{
		center.x += s*3;
		turret.x += s*3;
		for(int i = 0; i < body.npoints; i++)
		{
			body.xpoints[i] += s*3;
		}
		body = new Polygon(body.xpoints,body.ypoints,body.npoints);
	}
	
	public void setTurret(Point p)
	{
		double ratio = Math.sqrt(Math.pow(p.y-center.y,2)+Math.pow(p.x-center.x,2))/(radius*2);
		dx = (p.x-center.x)/ratio;
		dy = (p.y-center.y)/ratio;
		angle = Math.atan(dy/dx);
		turret = new Point(center.x+(int)dx,center.y+(int)dy);
	}
	
	public Point getTurret()
	{
		return turret;
	}
	
	public void kill()
	{
		body = new Polygon();
		center = new Point(-500,-500);
		turret = center;
		killed = true;
	}
	
	public boolean getAlive()
	{
		return !killed;
	}
	
	public void setMove(int m)
	{
		move = m;
	}
	
	public int getMove()
	{
		return move;
	}
}
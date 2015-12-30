import java.awt.*;

public abstract class Body
{
	Point center;
	Polygon body;
	Color color;
	int radius;
	double dx,dy;
	
	public Color getColor()
	{
		return color;
	}
	
	public Point getCenter()
	{
		return center;
	}
	
	public Point getCorner()
	{
		return new Point(center.x-radius,center.y-radius);
	}
	
	public Polygon getBody()
	{
		return body;
	}
	
	public int getRadius()
	{
		return radius;
	}
		
	public double getDX()
	{
		return dx;
	}
	
	public double getDY()
	{
		return dy;
	}
	
	public Rectangle getRect()
	{
		return body.getBounds();
	}
}
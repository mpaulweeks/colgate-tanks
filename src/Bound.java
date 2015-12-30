import java.awt.*;

public class Bound extends Body
{
	int height, width;
	
	public Bound(Point c, int w, int h)
	{
		init(c,w,h);
	}
	
	public Bound(int x, int y, int w, int h)
	{
		init(new Point(x,y),w,h);
	}
	
	public void init(Point c, int w, int h)
	{
		color = Color.lightGray;
		center = c;
		height = h;
		width = w;
		body = new Polygon();
		body.addPoint(c.x-w,c.y-h);
		body.addPoint(c.x+w,c.y-h);
		body.addPoint(c.x+w,c.y+h);
		body.addPoint(c.x-w,c.y+h);
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
}
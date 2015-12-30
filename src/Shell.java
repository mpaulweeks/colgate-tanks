import java.awt.*;

public class Shell extends Body
{
	double angle,x,y;
	boolean bounced;
	
	public Shell()
	{
		
	}
	
	public Shell(double d)
	{
		
	}
	
	public Shell(Point c, double DX, double DY)
	{
		dx = DX;
		dy = DY;
		center = c;
		x = c.x;
		y = c.y;
		color = Color.white;
		bounced = false;
		radius = 10;
	}
	
	public void travel()
	{
		x += dx;
		y += dy;
		center = new Point((int)x,(int)y);
	}
	
	public void reverseX()
	{
		dx *= -1;
		bounced = true;
	}
	
	public void reverseY()
	{
		dy *= -1;
		bounced = true;
	}
	
	public boolean getBounced()
	{
		if(Keyboard.bouncy)
			return false;
		return bounced;
	}
}
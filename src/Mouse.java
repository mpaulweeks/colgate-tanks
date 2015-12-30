import java.awt.*;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener
{
	static Point point;
	static boolean LC;
	static boolean RC;
	static boolean WHEEL;
	
	public Mouse()
	{
		point = new Point(0,0);
	}
	
	public void mousePressed(MouseEvent e)
	{
		int button = e.getButton();
		if(button == 1)	LC = true;
		if(button == 3)	RC = true;
		if(button == 2)	WHEEL = true;
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void mouseMoved(MouseEvent e)
	{
		point = e.getPoint();
	}
	
	public void mouseDragged(MouseEvent e)
	{
		mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e)	{}
	public void mouseEntered(MouseEvent e)	{}
	public void mouseExited(MouseEvent e)	{}
}
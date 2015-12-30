//The KeyListener for the game. This allows Keyboard input.

import java.awt.*;
import java.awt.event.*;

public class Keyboard implements KeyListener
{	
	public static boolean ESC;
	public static boolean INGAME;
	public static boolean P;
	public static boolean UP;
	public static boolean DOWN;
	public static boolean LEFT;
	public static boolean RIGHT;
	public static boolean bouncy;
	public static boolean F1;
	public static boolean F2;
	public static boolean F3;
	public static boolean F4;
	public static boolean F5;
	public static boolean F6;
	public static boolean F7;
	public static boolean F8;
	public static boolean F11;
	public static boolean F12;
	public static String s = "";
	
	public Keyboard()
	{
		
	}
    
    public void keyPressed(KeyEvent event)
    {  
//    	System.out.println("KeyPressed");
    	int keyCode = event.getKeyCode();
    	int keyLocation = event.getKeyLocation();
        
        if(keyCode == KeyEvent.VK_F1)
        {
        	F1 = true;
        }
        
        if(keyCode == KeyEvent.VK_F2)
        {
        	F2 = true;
        }
        
        if(keyCode == KeyEvent.VK_F3)
        {
        	F3 = true;
        }
        
        if(keyCode == KeyEvent.VK_F4)
        {
        	F4 = true;
        }
        
        if(keyCode == KeyEvent.VK_F5)
        {
        	bouncy = true;
        }
        
        if(keyCode == KeyEvent.VK_F6)
        {
        	bouncy = false;
        }
        
        if(keyCode == KeyEvent.VK_F7)
        {
        	F7 = true;
        }
        
        if(keyCode == KeyEvent.VK_F8)
        {
        	F8 = true;
        }  
        
        if(keyCode == KeyEvent.VK_F11)
        {
        }        
        
        if(keyCode == KeyEvent.VK_F12)
        {
        }
        
        if(keyCode == KeyEvent.VK_P)
        {
        	P = true;
 //       	System.out.println("GAMEPAUSED = " + GAMEPAUSED);
        }
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
        {
        	UP = true;
//        	System.out.println("UP = " + UP);
        }
        if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
        {
        	DOWN = true;
//        	System.out.println("DOWN = " + DOWN);
        }
        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
        {
        	LEFT = true;
//        	System.out.println("LEFT = " + LEFT);
        }        
        if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
        {
        	RIGHT = true;
//        	System.out.println("RIGHT = " + RIGHT);
        }  
        if(keyCode == KeyEvent.VK_ESCAPE)
        {
        	ESC = true;
        }
    }
    
    public void keyReleased(KeyEvent event)
    {
//    	System.out.println("KeyReleased");
    	int keyCode = event.getKeyCode();
    	int keyLocation = event.getKeyLocation();
    	           
        if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W)
        {
        	UP = false;
//        	System.out.println("UP = " + UP);
        }        
        if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S)
        {
        	DOWN = false;
//        	System.out.println("DOWN = " + DOWN);
        }
        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A)
        {
        	LEFT = false;
 //       	System.out.println("LEFT = " + LEFT);
        }        
        if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D)
        {
        	RIGHT = false;
//        	System.out.println("RIGHT = " + RIGHT);
        }
    }
    
    public void keyTyped(KeyEvent event)
    {
	   	int keyCode = event.getKeyCode();
    	int keyLocation = event.getKeyLocation();
    }
}
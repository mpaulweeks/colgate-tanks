import java.util.Random;
import java.util.ArrayList;
import java.awt.*;

public class Game
{
	GUI gui;
	Tank Hero;
	int level;
	ArrayList<Tank> Enemy;
	ArrayList<Shell> Fire;
	ArrayList<Explosion> Explodes;
	ArrayList<Bound> Bounds;
	ArrayList<Mine> Mines;
	Random random = new Random();
	
	public Game()
	{
		gui = new GUI();		
		Bounds = new ArrayList<Bound>();
		Bounds.add(new Bound(400,0,400,20));
		Bounds.add(new Bound(400,600,400,20));
		Bounds.add(new Bound(0,300,20,280));
		Bounds.add(new Bound(800,300,20,280));
		level = 1;
		
		while(!Keyboard.ESC)
		{
			Hero = new Tank();
			Fire = new ArrayList<Shell>();
			Enemy = new ArrayList<Tank>();
			Explodes = new ArrayList<Explosion>();
			Mines = new ArrayList<Mine>();
			
			level = ((level-1)%7)+1;
			if(level == 1)
			{
				Bounds.add(new Bound(400,300,10,100));
				Enemy.add(new Tank(1,600,300));				
			}
			if(level == 2)
			{
				Bounds.add(new Bound(400,200,100,10));
				Bounds.add(new Bound(400,400,100,10));
				Enemy.add(new Tank(1,600,100));
				Enemy.add(new Tank(1,600,500));
			}
			if(level == 3)
			{
				Bounds.add(new Bound(400,200,200,10));
				Bounds.add(new Bound(400,400,200,10));
				Enemy.add(new Tank(1,600,100));
				Enemy.add(new Tank(1,600,500));
				Enemy.add(new Tank(1,200,500));
				Enemy.add(new Tank(1,400,500));
			}
			if(level == 4)
			{
				Bounds.add(new Bound(400,210,100,10));
				Bounds.add(new Bound(400,390,100,10));
				Bounds.add(new Bound(310,300,10,80));
				Bounds.add(new Bound(490,300,10,80));
				Enemy.add(new Tank(1,600,400));
				Enemy.add(new Tank(1,600,200));
				Enemy.add(new Tank(1,200,400));
			}
			if(level == 5)
			{
				Bounds.add(new Bound(400,300,300,10));
				for(int i = 0; i < 7; i++)
					Enemy.add(new Tank(1,100+100*i,400));
			}
			if(level == 6)
			{
				Bounds.add(new Bound(300,200,10,110));
				Bounds.add(new Bound(200,300,100,10));
				for(int i = 0; i < 6; i++)
					Enemy.add(new Tank(1,100+100*i,500));
				for(int i = 0; i < 5; i++)
					Enemy.add(new Tank(1,700,100+100*i));
			}
			if(level == 7)
			{
				Bounds.add(new Bound(300,200,10,110));
				Bounds.add(new Bound(200,300,100,10));
				Enemy.add(new Tank(2,200,500));
			}
			
			gui.purge();
			gui.ready(level,Bounds);
			gui.repaint();
			try {Thread.sleep(2000);} catch (Exception e) {}
			
			Mouse.LC = false;
			Mouse.RC = false;
			Mouse.WHEEL = false;
			
			while(Hero.getAlive() && Enemy.size()>0 && !Keyboard.ESC)
			{			
				if(Keyboard.F4)
				{
					System.out.println("you just pressed F4");
					Keyboard.F4 = false;
				}
				if(Keyboard.P)
				{
					Keyboard.P = false;
					gui.purge();
					gui.pause(Bounds);
					gui.repaint();
					while(!Keyboard.P){}
					Keyboard.P = false;
				}
				if(Keyboard.UP)
				{
					Hero.moveVert(-1);
					if(boundsConflict(Hero))
						Hero.moveVert(1);
				}
				if(Keyboard.DOWN)
				{
					Hero.moveVert(1);
					if(boundsConflict(Hero))
						Hero.moveVert(-1);
				}
				if(Keyboard.LEFT)
				{
					Hero.moveHori(-1);
					if(boundsConflict(Hero))
						Hero.moveHori(1);
				}
				if(Keyboard.RIGHT)
				{
					Hero.moveHori(1);
					if(boundsConflict(Hero))
						Hero.moveHori(-1);
				}
				if(Mouse.LC)
				{
					Fire.add(new Shell(Hero.getTurret(),Hero.getDX()/5,Hero.getDY()/5));
					Mouse.LC = false;
				}
				if(Mouse.RC)
				{
					Mines.add(new Mine(Hero.getCenter(),Hero.getColor()));
					Mouse.RC = false;
				}
				
				Hero.setTurret(Mouse.point);
				ai();
								
				for(int i = 0; i < Fire.size(); i++)
				{
					Fire.get(i).travel();
					if(Hero.getBody().contains(Fire.get(i).getCenter()))
					{
						Explodes.add(new Explosion(Fire.get(i)));
						Fire.remove(i);
						i--;
					}
					else
						for(int e = 0; e < Enemy.size(); e++)
							if(Enemy.get(e).getBody().contains(Fire.get(i).getCenter()))
							{
								Explodes.add(new Explosion(Fire.get(i)));
								Fire.remove(i);
								i--;
								e=Enemy.size();
							}
				}
				
				for(int x = 0; x < Fire.size(); x++)
				{
					for(int y = x+1; y < Fire.size(); y++)
					{
						double distance = Math.sqrt(Math.pow(Fire.get(x).getCenter().x-Fire.get(y).getCenter().x,2)+Math.pow(Fire.get(x).getCenter().y-Fire.get(y).getCenter().y,2));
						if(distance < Fire.get(x).getRadius())
						{
							Explodes.add(new Explosion(Fire.get(x)));
							Explodes.add(new Explosion(Fire.get(y)));
							Fire.remove(y);
							Fire.remove(x);
							x = 0;
							y = Fire.size();
						}
					}
				}
				
				for(int i = 0; i < Fire.size(); i++)
				{
					for(int b = 0; b < Bounds.size(); b++)
					{
						if(Bounds.get(b).getBody().contains(Fire.get(i).getCenter()))
						{
							if(Fire.get(i).getBounced())
							{
								Explodes.add(new Explosion(Fire.get(i)));
								Fire.remove(i);
								i--;
								b = Bounds.size();
							}
							else
							{
								if(Bounds.get(b).getBody().contains(new Point(Fire.get(i).getCenter().x,Fire.get(i).getCenter().y-(int)Fire.get(i).getDY())))
									Fire.get(i).reverseX();
								else
									Fire.get(i).reverseY();
								Fire.get(i).travel();
								Fire.get(i).travel();
							}
						}
					}
				}
				
				for(int m = 0; m < Mines.size(); m++)
				{
					Mines.get(m).age();
					if(Mines.get(m).getActive())
					{
						if(Mines.get(m).getLife() <= 0)
						{
							Explodes.add(Mines.get(m).getExplosion());
							Mines.remove(m);
							m--;
						}
						else if(Hero.getRect().intersects(Mines.get(m).getRect()))
						{
							Explodes.add(Mines.get(m).getExplosion());
							Mines.remove(m);
							m--;
						}
						else
						{
							for(int e = 0; e < Enemy.size(); e++)
								if(Enemy.get(e).getRect().intersects(Mines.get(m).getRect()))
								{
									Explodes.add(Mines.get(m).getExplosion());
									Mines.remove(m);
									m--;
									e = Enemy.size();
								}
							for(int f = 0; f < Fire.size(); f++)
								if(Mines.get(m).getRect().contains(Fire.get(f).getCenter()))
								{
									Explodes.add(Mines.get(m).getExplosion());
									Mines.remove(m);
									m--;
									Fire.remove(f);
									f = Fire.size();
								}
							for(int n = m+1; n < Mines.size(); n++)
								if(Mines.get(m).getRect().intersects(Mines.get(n).getRect()))
								{
									Explodes.add(Mines.get(m).getExplosion());
									Mines.remove(m);
									m--;
									Explodes.add(Mines.get(n-1).getExplosion());
									Mines.remove(n-1);
									break;
								}
						}
					}
				}
				
				for(int i = 0; i < Explodes.size(); i++)
				{
					Explodes.get(i).age();
					Explodes.get(i).age();
					if(!Explodes.get(i).getAlive())
					{
						Explodes.remove(i);
						i--;
					}
					else
					{
						if(Explodes.get(i).getRect().intersects(Hero.getRect()))
						{
							Explodes.add(new Explosion(Hero));
							Hero.kill();
						}
						for(int e = 0; e < Enemy.size(); e++)						
							if(Explodes.get(i).getRect().intersects(Enemy.get(e).getRect()))
							{
								Explodes.add(new Explosion(Enemy.get(e)));
								Enemy.remove(e);
								e--;
							}
						for(int m = 0; m < Mines.size(); m++)
							if(Explodes.get(i).getRect().intersects(Mines.get(m).getRect()))
							{
								Explodes.add(Mines.get(m).getExplosion());
								Mines.remove(m);
								m--;
							}
					}
				}
					
				gui.purge();
				gui.drawAll(Hero,Fire,Enemy,Explodes,Bounds,Mines);
				gui.repaint();								
				try {Thread.sleep(30);} catch (Exception e) {}
			}
			
			while(Bounds.size() > 4)
				Bounds.remove(4);			
			if(Hero.getAlive())
				level++;
		}
		gui.dispose();
	}
	
	public void ai()
	{
		for(int i = 0; i < Enemy.size(); i++)
		{	
			Enemy.get(i).setTurret(Hero.getCenter());
			if(canFire(Enemy.get(i)))
				Fire.add(new Shell(Enemy.get(i).getTurret(),Enemy.get(i).getDX()/5,Enemy.get(i).getDY()/5));
					
			if(random.nextInt(100) == 1) //movement changing
			{
				int rand = random.nextInt(10);
				if(rand > 4)	rand = 0;
				Enemy.get(i).setMove(rand);
			}
			
			//movement checking
			if(Enemy.get(i).getMove() == 1)
			{
				Enemy.get(i).moveVert(-1);
				if(boundsConflict(Enemy.get(i)))
					Enemy.get(i).moveVert(1);
			}
			if(Enemy.get(i).getMove() == 2)
			{
				Enemy.get(i).moveVert(1);
				if(boundsConflict(Enemy.get(i)))
					Enemy.get(i).moveVert(-1);
			}
			if(Enemy.get(i).getMove() == 3)
			{
				Enemy.get(i).moveHori(-1);
				if(boundsConflict(Enemy.get(i)))
					Enemy.get(i).moveHori(1);
			}
			if(Enemy.get(i).getMove() == 4)
			{
				Enemy.get(i).moveHori(1);
				if(boundsConflict(Enemy.get(i)))
					Enemy.get(i).moveHori(-1);
			}
		}
	}
	
	public boolean isVisible(Tank e)
	{
		return isVisible(Hero.getCenter(),e.getCenter());
	}
	
	public boolean isVisible(Point j, Point k)
	{
		double dx = (k.x-j.x)/100;
		double dy = (k.y-j.y)/100;
		double x = j.x;
		double y = j.y;
		for(int i = 0; i < 100; i++)
		{
			for(int b = 4; b < Bounds.size(); b++)
			{
				if(Bounds.get(b).getBody().contains(new Point((int)x,(int)y)))
					return false;
			}
			x += dx;
			y += dy;
		}
		return true;
	}
	
	public boolean boundsConflict(Tank t)
	{
		for(int b = 0; b < Bounds.size(); b++)
			if(Bounds.get(b).getRect().intersects(t.getRect()))
				return true;
		return false;
	}
	
	public Point findBanking(Tank e)
	{
		Point a = Hero.getCenter();
		Point c = e.getCenter();
		Point b = new Point(a);
		if(Math.abs(c.x-a.x) > Math.abs(c.y-a.y)) //bounce off of ceiling/floor
		{
			int min = 800;
			for(int i = 0; i < Bounds.size(); i++)
			{
				if(Bounds.get(i).getWidth()>Bounds.get(i).getHeight())
					if(Math.abs(Bounds.get(i).getCenter().y-a.y)<min)
						min = Bounds.get(i).getCenter().y-a.y;
			}
			min += a.y;
			if(min<c.y)
				b.y=min+20;
			else
				b.y=min-20;
			
			b.x = a.x;
			while(!isVisible(b,c))
			{
				if(b.x<c.x)
					b.x++;
				else
					b.x--;
			}
		}
		else
			return new Point(-1,-1);
		return b;
	}
	
	public boolean canFire(Tank e)
	{
		if(random.nextInt(50) != 1)
			return false;
		if(!isVisible(e)) // || (Math.abs(Enemy.get(i).getCenter().x-Hero.getCenter().x)>4*Enemy.get(i).getRadius() && Math.abs(Enemy.get(i).getCenter().y-Hero.getCenter().y)>4*Enemy.get(i).getRadius()))
			return false;
		for(Bound b : Bounds)
			if(b.getBody().contains(e.getTurret()))
				return false;
		
		return true;
	}
}
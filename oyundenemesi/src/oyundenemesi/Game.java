package oyundenemesi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Game extends JPanel implements ActionListener,KeyListener {

	private Timer timer; 
	private int delay=5;
	private boolean play = false;
	private int angle = 45; 
	private int fireX,fireY;
	private Graphics2D g2d;
	private AffineTransform at;
	private static ArrayList bullets;
	private static ArrayList enemies;
	private int ammo = 5000;
	private Random rand = new Random();
	private double health = 100.0;
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	
	Image tank = new ImageIcon(Game.class.getResource("/tank.png")).getImage();
	Image gun = new ImageIcon(Game.class.getResource("/gun.png")).getImage();
	Image bullet = new ImageIcon(Game.class.getResource("/bullet.png")).getImage();
	
	
	 private static int tankX = 200 ,tankY=200;
	private int gunX,gunY,x,y;
	
	
	
	public Game() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		x=gunX;
		y=gunY;
		
	
		
		timer= new Timer(delay,this);
		timer.start();
		
		bullets = new ArrayList();
		enemies = new ArrayList();
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// making angle always positive
		if(angle > 360) {
			angle -= 360;
		}
		else if(angle < 0) {
			angle += 360;
		}
		else if(angle == 360) {
			angle = 0; 
		}
		
		// position of the gun
		gunX = (tankX + tank.getWidth(null)/2)-140;
		gunY = tankY + tank.getHeight(null)/2;
		
		// rotating the gun
		at = AffineTransform.getTranslateInstance(gunX, gunY);
		at.rotate(Math.toRadians(angle),gun.getWidth(null)-10,gun.getHeight(null)/2);
		
		
		// finding the fire position
		fireX = (int) at.getTranslateX();
		fireY = (int) at.getTranslateY();
		
		
		g.drawImage(tank, tankX, tankY,55,118,null); 
		//g.drawImage(bullet,fireX,fireY,20,20,null);
		
		g2d = (Graphics2D) g;
		g2d.drawImage(gun,at,null);
		
		g2d.setColor(Color.BLUE);
		g2d.setFont(new Font("SanSerif",Font.BOLD,18));
		g2d.drawString("AMMO LEFT: " + ammo, 1300, 20);
		g2d.drawString("HEALTH: "+ df2.format(health), 1300, 40);
		
		
		for (int j=0 ;j<enemies.size();j++ ) {
			
			Enemy m = (Enemy) enemies.get(j);
			
			if(m.isAlive() == true) {
				g2d.drawImage(m.getImg(),m.getX(),m.getY(),null);
			}
			
		
		}
		
		
		for (int i=0 ;i<bullets.size();i++ ) {
			
			Bullet m = (Bullet) bullets.get(i);
			g2d.drawImage(m.getImg(),(int) m.getX(),(int) m.getY(),null);
		}
		
		
		System.out.println(angle);
		
}
	
	public void fire() {
		
		if(ammo > 0 ) {
			
			ammo--;
			Bullet z = new Bullet(fireX,fireY,angle);
			bullets.add(z);
		}
		
	}
	
	public void spawn() {
	
			Enemy z = new Enemy(rand.nextInt(50), rand.nextInt(50),"C:/Users/Doðukan/Desktop/Java Workspace/oyundenemesi/texture/p.png");
			enemies.add(z);
	}
	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(play == true) {
			
			timer.start();
			
			for (int i=0 ;i<bullets.size();i++ ) {
				
				Bullet m = (Bullet) bullets.get(i);
				if(m.isVisible() == true) {
					
					m.move();
				}else {
					bullets.remove(i);
				}
				
			}
			
			for (int i=0 ;i<enemies.size();i++ ) {
				
				Enemy m = (Enemy) enemies.get(i);
				
				if(m.isAlive() == true) {
					
					m.move();
				}else {
					enemies.remove(i);
				}
				
			}
			
			for (int i=0 ;i<bullets.size();i++ ) {
				
				Bullet m = (Bullet) bullets.get(i);
				Ellipse2D m1 = m.getBounds();
				
				for(int j=0;j<enemies.size();j++) {
					
					Enemy en = (Enemy) enemies.get(j);
					
					if(m1.intersects(en.getBounds()) && en.isAlive()) {
						en.alive = false;
						m.visible = false;
					}
				}
				
			}
			
			for (int i=0 ;i<enemies.size();i++ ) {
				
				Enemy m = (Enemy) enemies.get(i);
				Rectangle m1 = m.getBounds();
				
			     if(getTankBounds().intersects(m1)) {
			    	 
			    	 health-=0.01;
			    	 
			    	 if(health <= 0) {
			    		 new JOptionPane().showMessageDialog(null, "GAME OVER");
			    		 System.exit(0);
			    	 }
			    	 
			     }
				
			}
			
			repaint();
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			moveUp();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			moveRight();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			moveLeft();
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
	
			moveDown();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_A) {
			
			angle -= 3;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_D) {
			
			angle += 3;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			fire();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P) {
			
			spawn();
			
		}
		
	}
	
	
	public void moveUp() {
		
		play=true;
		tankY-= 5;
		
	}
	
	public void moveRight() {
		
		play = true;
		tankX += 5;
		
	}
	public void moveDown() {
		
		play = true;
		tankY += 5;
		
	}
	public void moveLeft() {
	
	play = true;
	tankX -= 5;
	
}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	public int getGunX() {
		return gunX;
	}


	public void setGunX(int gunX) {
		this.gunX = gunX;
	}


	public int getGunY() {
		return gunY;
	}


	public void setGunY(int gunY) {
		this.gunY = gunY;
	}
	
	public static ArrayList getBullets() {
		return bullets;
	}
	
	public static int getTankX() {
		return tankX;
	}


	public static int getTankY() {
		return tankY;
	}
	
	public Rectangle getTankBounds() {
		return new Rectangle(tankX,tankY,55,118);
	}


}



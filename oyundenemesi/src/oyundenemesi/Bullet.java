package oyundenemesi;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.ImageIcon;

public class Bullet {

	private  double x,y,dx,dy;
	private Image img;
	private int angle;
	boolean visible;
	private static ArrayList bullets;
	
	public Bullet(int startX, int startY,int angle) {
		
		x=startX;
		y=startY;
		this.angle = angle;
		dx = Math.cos(angle);
		dy = Math.cos(angle);
		
		ImageIcon newbullet = new ImageIcon("C:/Users/Doðukan/Desktop/java game icons/newbullet.png");
		img = newbullet.getImage();
		visible = true;
	}
	
	public void move() {
		
		
		if(x > 700 || x < 0 || y < 0 || y > 700) {
			visible = false;
		}
		
		if(angle == 0) {
			
			x-=2;
			
		}
		
		else if(angle == 90) {
			
			y-=2;
			
		}
		
		else if(angle == 180) {
			
			x+=2;
			
		}

		else if(angle == 270) {
	
			y+=2;
	
		}
		
		else if(angle > 0 && angle < 6 ) {
			
			x-=2;
			
		}
		
		else if(angle >=6 && angle <= 21 ) {
			
			x-=2;
			y-=1;
			
		}
		 
		else if(angle > 21 && angle < 42 ) {
			
			x-=2;
			y-=2;
			
		}
		
		else if(angle >=42 && angle < 90 ) {
			
			x-=2;
			y-=2;
			
		}
		
		else if(angle > 90 && angle < 180 ) {
			
			x+=3;
			y-=2;
			
		}
		
		else if(angle > 180 && angle < 270 ) {
			
			x+=3;
			y+=2;
			
		}
		
		else if(angle > 270) {
			
			x-=3;
			y+=2;
			
		}
		
		
		
		 
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public Ellipse2D getBounds() {
		return new Ellipse2D.Double(x,y,12,12);
	}
	
	
	
}

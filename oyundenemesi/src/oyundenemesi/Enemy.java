package oyundenemesi;

import java.awt.*;
import javax.swing.*;


public class Enemy {
	
	private Image img;
	private int x,y,xDif,yDif;
	 boolean alive = true;
	
	public Enemy(int startX,int startY,String location) {
		
		x=startX;
		y=startY;
		ImageIcon l = new ImageIcon(location);
		img = l.getImage();		
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImg() {
		return img;
	}

	public boolean isAlive() {
		return alive;
	}
	
	
	public void move() {
		
		xDif = Math.abs(Game.getTankX()-x);
		yDif = Math.abs(Game.getTankY()-y);
		
		if(xDif-2 > 0) {
			
			if(x < Game.getTankX()) {
				x++;
			}
			else if(x > Game.getTankX()) {
				x--;
			}
		}
		
		if(yDif-2 > 0) {
			
			if(y < Game.getTankY()) {
				y++;
			}
			else if(y > Game.getTankY()) {
				y--;
			}
		}
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,55,53);
		
	}
	
	
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Paddle {
	public boolean player = false;
	public Rectangle r = null;
	public int score = 0;
	
	public Paddle(boolean player, Rectangle r){
		this.player = true;
		this.r = r;
	}
	
	public void update(KeyEvent e){
		if(r.y > 100 && r.y + r.height < 450 && player){
			if(e.getKeyCode() == KeyEvent.VK_UP)
				r.y -= 25;
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				r.y += 25;	
		}
		else if(r.y + r.height == 450){
			if(e.getKeyCode() == KeyEvent.VK_UP)
				r.y -= 25;
		}
		else if(r.y == 100){
			if(e.getKeyCode() == KeyEvent.VK_DOWN)
				r.y += 25;
		}
	}
	
	public void paint(Graphics g){
		g.setColor(Color.green);
		g.drawRect(r.x, r.y, r.width, r.height);
	}
}

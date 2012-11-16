import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Particle {
	public double dx, dy;
	public Rectangle r;
	public long timeCreated;
	public boolean delete = false;
	public int speed = 3;
	
	public Particle(int x, int y, double dx, double dy){
		Random rand = new Random();
		timeCreated = System.currentTimeMillis();
		this.r = new Rectangle(x, y, 1, 1);
		this.dx = Math.cos(rand.nextInt(90) - 90);
		this.dy = Math.sin(rand.nextInt(90) - 90);
	}
	
	public void paint(Graphics g){
		if(!delete){
			g.setColor(Color.green);
			g.drawRect(this.r.x, this.r.y, 5, 5);
		}
	}
	
	public void update(){
		if(System.currentTimeMillis() - 200 > timeCreated)
			delete = true;
		if(!delete){
			normalize();
			r.x += (int)(speed * dx);
			r.y += (int)(speed * dy);
			
		}
	}
	
	public void normalize(){
		dx /= Math.sqrt(dx*dx + dy*dy);
		dy /= Math.sqrt(dx*dx + dy*dy);
	}
}

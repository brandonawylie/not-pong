import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;

public class Ball {
	public ArrayList<Particle> p = new ArrayList();
	public double dx, dy;
	public int speed = 5;
	public Random rand = new Random();
	public int x, y;
	public Rectangle r;
	public long parCounter = 0;
	public Ball(Rectangle r){
		this.r = r;
		dx = 1;dy = 1;
		x = 200; y = 200;
	}
	
	public void update(){
		if(parCounter == 0)
			parCounter = System.currentTimeMillis();
		
		r.x += (int)(dx * speed);
		r.y += (int)(dy * speed);
		this.normalize();
		
		if(dx % dy != 0){
			Particle par = new Particle(this.r.x, this.r.y, this.dx, this.dy);
			p.add(par);
		}
		
		for(Particle par: p){
			par.update();
		}
	} 
	
	public void paint(Graphics g){
		g.setColor(Color.green);
		g.drawOval(r.x, r.y, r.width/2, r.width/2 );
		
		for(Particle par : p){
			par.paint(g);
		}
	}
	
	public void normalize(){
		dx /= Math.sqrt(dx*dx + dy*dy);
		dy /= Math.sqrt(dx*dx + dy*dy);
	}
}

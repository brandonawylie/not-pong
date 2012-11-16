import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

import javax.swing.*;

//Class: GamePanel
//the panel that will handle the drawing of Sprites, will be added to the JFrame
public class GamePanel extends JPanel implements KeyListener{
	
	//class variables
	private BufferedImage doubleBuffer;
	private int width, height;
	Paddle player;
	Paddle comp;
	Ball b;
	Rectangle top, bottom;
    boolean start = false, reset = false;
	long goTimer;
	Random r = new Random();
	
	//CONSTRUCTOR
	public GamePanel(int w, int h){
		//setup
		width = w;height = h;
		this.setSize(new Dimension(w, h));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.setVisible(true);
		this.setCursor(null);
		this.addKeyListener(this);
		initializeComponents();
		
		//top and bottom rectangles for collision with the ball
		top = new Rectangle(0,0, 800, 100);
		bottom = new Rectangle(0, 450, 800, 100);
		this.update();
		
		
		//this is the updater, will be used in sync with the timer
		ActionListener updater =  new ActionListener(){
			public void actionPerformed(ActionEvent e){
				update();
				repaint();
			}
		};
		
		//this timer will throw a Action Event every 10ms (that is 100fps ideally)
		Timer t = new Timer(10, updater);
		t.start();
		
		
	}
	
	//Method: paintComponent
	//overriding JPanel's paint component allows you to draw things using the graphics object
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		
		//erase the board by filling it with all black
		g.fillRect(0, 0, this.width, this.height);
		//drawWalls() function
		drawWalls(g);
		
		//paint the player, computer, and ball
		player.paint(g);
		comp.paint(g);
		b.paint(g);
		
		//the score
		Font f = new Font("newFont", Font.PLAIN, 20);
		g.setFont(f);
		g.drawString("Player", 5, 20);
		g.drawString("" + player.score, 5, 42);
		
		g.drawString("Computer", 580, 20);
		g.drawString("" + comp.score, 580, 42);
		
		if(!start){
			g.setColor(Color.WHITE);
			g.drawString("Ready?", 400, 300);
		}
		if(!(System.currentTimeMillis() - 3000 > goTimer) && start){
			g.setColor(Color.white);
			g.drawString("GO!", 400, 300);
		}
	}
	
	public void update(){
		checkCollision();
		
		//check for scoring
		if(b.r.x < player.r.x){
			comp.score++;
			reset = true;
		}
		if(b.r.x > comp.r.x){
			player.score++;
			reset = true;
		}
		
		b.update();
		
		if(reset){
			reset();
			start = false;
		}
	}
	
	public void initializeComponents(){
		player = new Paddle(true, new Rectangle(20, 200, 5, 50));
		comp = new Paddle(false, new Rectangle(780, 200, 5, 50));
		b = new Ball(new Rectangle(400,300, 20, 20));
		b.dx = 0;
		b.dy = 0;  
	}
	
	public void drawWalls(Graphics g){
		g.setColor(Color.green);
		g.drawLine(0, 100, 800, 100);
		g.drawLine(0, 450, 800, 450);
	}
	
	public void checkCollision(){
		r = new Random();
		if(b.r.intersects(top)){
			b.dy = -b.dy;
		}
		else if(b.r.intersects(bottom)){
			b.dy = -b.dy;
		}
		else if(b.r.intersects(player.r)){
			b.dx = Math.cos(r.nextInt(90) - 90);
		}
		else if(b.r.intersects(comp.r)){
			b.dx = Math.sin(r.nextInt(90) - 90);
		}
		//b.update();
		//System.out.println(b.vx + " " + b.vy);
	}
	
	public void reset(){
		b.dx = 0;
		b.dy = 0;
		b.r.x = 400;
		b.r.y = 300;
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_SPACE && !start){
			start = true;
			goTimer = System.currentTimeMillis();
			
			do{
				r = new Random();
				b.dx = r.nextDouble();
				b.dy = r.nextDouble();
				System.out.println("l");
			}while(b.dx == 0 || b.dy == 0);
			System.out.println("space pressed!");
			reset = false;
		}
		player.update(k);
		comp.update(k);
		//System.out.println("in key pressed");
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		player.update(k);
		comp.update(k);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

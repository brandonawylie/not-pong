import java.awt.Dimension;

import javax.swing.JFrame;


public class Main extends JFrame{
	public Main(){
		this.setSize(new Dimension(800, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GamePanel(this.getWidth(), this.getHeight()));
		this.setCursor(null);
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] args){
		Main d = new Main();
	}
}

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.w3c.dom.css.Rect;

public class Main implements ActionListener,KeyListener {
	static Main mainApp;
	JFrame frame;
	int WIDTH = 600;
	int HEIGHT = 600;
	Renderer renderer;
	Rectangle car;
	ArrayList<Rectangle> cars;
	Random rand;
	int flag = 0;
	boolean started, gameover;
	Main() {
		frame = new JFrame("Demo Car Demo");
		Timer t = new Timer(20, this);
		
		rand = new Random();
		
		renderer = new Renderer();
		car = new Rectangle(WIDTH/2 -25, HEIGHT - 150 , 50, 80);
		cars = new ArrayList<Rectangle>();
		
		addCars(true);
		addCars(true);
		
		frame.add(renderer);
		
		frame.addKeyListener(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		t.start();
	}
	public void addCars(boolean b) {
		int xi = rand.nextInt(100);
		int width = 50;
		int height = 80;
		if(b) {
			cars.add(new Rectangle(WIDTH/4 + xi, -cars.size()*150,width,height));
			cars.add(new Rectangle(WIDTH/4 + xi + 120,-(cars.size()-1)*150, width, height));
		}
		else {
			cars.add(new Rectangle(WIDTH/4 + xi, -cars.size()*150+150,width, height));
			cars.add(new Rectangle(WIDTH/4 + xi + 120,-(cars.size()-1)*150+150, width, height));
		}
	}
	public static void main(String arv[]) {
		mainApp = new Main();
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		g.setColor(Color.green.darker().darker());
		g.fillRect(0, 0, WIDTH/4, HEIGHT);
		g.fillRect(WIDTH*3/4, 0, WIDTH/4, HEIGHT);
		
		g.setColor(Color.RED.darker().darker());
		g.fillRect((WIDTH/4)-20, 0, 20, HEIGHT);
		g.fillRect((WIDTH*3/4), 0, 20, HEIGHT);
		
		g.setColor(Color.red.brighter());
		g.fillRect(car.x, car.y, car.width, car.height);
		
		for(Rectangle rect : cars)
			paintCars(g, rect);
	}
	public void paintCars(Graphics g,Rectangle rect) {
		g.setColor(Color.BLUE.darker());
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int speed = 6;
		for(Rectangle rect : cars)
			rect.y += speed;
		
		for(int i =0; i<cars.size();i++) {
			Rectangle rect = cars.get(i);
			if(rect.y + 150 > car.y + 150 ) {
				cars.remove(i);
				flag++;
				if(flag%2 == 0)
					addCars(false);
			}
		}
		
		renderer.repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		started = true;
		gameover = false;
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

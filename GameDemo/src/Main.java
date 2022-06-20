import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main implements ActionListener, KeyListener, MouseListener {
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
	int score = 0;
	static int speed = 6 ;
	Main() {
		frame = new JFrame("Demo Car Demo");
		Timer t = new Timer(20, this);

		rand = new Random();

		renderer = new Renderer();
		car = new Rectangle(WIDTH / 2 - 25, HEIGHT - 150, 50, 80);
		cars = new ArrayList<Rectangle>();

		frame.add(renderer);

		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setVisible(true);
		t.start();
	}

	public void addCars(boolean b) {
		int xi = rand.nextInt(100);
		int width = 50;
		int height = 80;
		if (b) {
			cars.add(new Rectangle(WIDTH / 4 + xi, -cars.size() * 150, width, height));
			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size() - 1) * 150, width, height));
		} else {
			cars.add(new Rectangle(WIDTH / 4 + xi, -cars.size() * 150 + 150, width, height));
			cars.add(new Rectangle(WIDTH / 4 + xi + 120, -(cars.size() - 1) * 150 + 150, width, height));
		}
	}

	public static void main(String arv[]) {
		mainApp = new Main();
	}

	public void repaint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.green.darker().darker());
		g.fillRect(0, 0, WIDTH / 4, HEIGHT);
		g.fillRect(WIDTH * 3 / 4, 0, WIDTH / 4, HEIGHT);

		g.setColor(Color.RED.darker().darker());
		g.fillRect((WIDTH / 4) - 20, 0, 20, HEIGHT);
		g.fillRect((WIDTH * 3 / 4), 0, 20, HEIGHT);

		g.setColor(Color.red.brighter());
		g.fillRect(car.x, car.y, car.width, car.height);

		for (Rectangle rect : cars)
			paintCars(g, rect);

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 30));
		if (!started && !gameover) {
			g.drawString("Click here to start!", 170, 100);
		}
		if (gameover) {
			g.drawString("Game Over! Your score is : "+score, 80, 200);
			started = false;
			cars.clear();
		}
		if (started && !gameover) {
			g.setFont(new Font("Arial", 1, 20));
			g.drawString("Score : " + score, 10, 300);
		}

	}

	public void paintCars(Graphics g, Rectangle rect) {
		g.setColor(Color.BLUE.darker());
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		speed = 6;
		if (started) {
			for (Rectangle rect : cars)
				rect.y += speed;

			for (int i = 0; i < cars.size(); i++) {
				Rectangle rect = cars.get(i);
				if (rect.y > car.y + 150) {
					cars.remove(i);
					flag++;
					if (rect.y > car.y) {
						score++;
						if(score % 6 == 0)
							speed++;
					}
					if (flag % 2 == 0)
						addCars(false);
				}
			}
		}
		for (Rectangle rect : cars) {
			
			if (rect.intersects(car))
				gameover = true;
		}
		
		renderer.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		score = 0;
		started = true;
		gameover = false;
		speed = 6;
		addCars(true);
		addCars(true);
		addCars(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(car.x > 150)
				car.x -=7;
			}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(car.x < 400)
				car.x += 7;
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		score = 0;
		started = true;
		gameover = false;
		speed = 6;
		addCars(true);
		addCars(true);
		addCars(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}

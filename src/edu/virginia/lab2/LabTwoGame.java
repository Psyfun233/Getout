package edu.virginia.lab2;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.util.GameClock;

public class LabTwoGame extends Game implements MouseListener{
	//position of the mouse click
	int clickPosX = 0;
	int clickPosY = 0;

	int health = 100;

	GameClock clock;

	String result = "";
	int time = 60;

	/* Create a sprite object for our game. We'll use mario */
	AnimatedSprite kuwirama = new AnimatedSprite("kuwirama", "kuwirama_walk_0.png",12,"resource/kuwirama_walk_0.png");

	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabTwoGame() {
		super("Lab Two: KuwiramaClicker2", 1200,800);
		this.getMainFrame().addMouseListener(this);
		this.setUpKuwirama();

	}


	public void setUpKuwirama() {
		kuwirama.setAlpha(1);
		kuwirama.setVisible(true);
		kuwirama.setPositionX(128);
		kuwirama.setPositionY(128);
		kuwirama.setScaleX(1);
		kuwirama.setScaleY(1);
		kuwirama.setPivotPointX(0);
		kuwirama.setPivotPointY(0);
		kuwirama.setRotation(0);
		kuwirama.setEndIndex(11);
		kuwirama.setStartIndex(0);
	}

	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */

	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);

		if(kuwirama != null) kuwirama.update(pressedKeys);

		if (!pressedKeys.contains(KeyEvent.VK_LEFT) || 
				!pressedKeys.contains(KeyEvent.VK_RIGHT) ||
				!pressedKeys.contains(KeyEvent.VK_UP) ||
				!pressedKeys.contains(KeyEvent.VK_DOWN)) {
			
			kuwirama.setPlaying(false);
		}
		
		for(Integer i: pressedKeys) {
			if (i == KeyEvent.VK_LEFT) {
				kuwirama.setPositionX(kuwirama.getPositionX()-10);
				kuwirama.setPlaying(true);
				
				
				if(kuwirama.getScaleX()<0) {
					kuwirama.setScaleX(kuwirama.getScaleX()*-1);
				}
				

			}else if (i == KeyEvent.VK_DOWN) {
				kuwirama.setPositionY(kuwirama.getPositionY()+10);
				kuwirama.setPlaying(true);

			}else if (i==KeyEvent.VK_RIGHT) {
				kuwirama.setPositionX(kuwirama.getPositionX()+10);
				kuwirama.setPlaying(true);
				
				
				if(kuwirama.getScaleX()>0) {
					kuwirama.setScaleX(kuwirama.getScaleX()*-1);
				}
				
			
			}else if (i==KeyEvent.VK_UP) {
				kuwirama.setPositionY(kuwirama.getPositionY()-10);
				kuwirama.setPlaying(true);
			}


			if(kuwirama.getPositionX()<128) kuwirama.setPositionX(128);
			if(kuwirama.getPositionY()<128) kuwirama.setPositionY(128);
			if(kuwirama.getPositionX()>900) kuwirama.setPositionX(900);
			if(kuwirama.getPositionY()>600) kuwirama.setPositionY(600);


			if (i == KeyEvent.VK_V && !this.getToggleTimeOut()) {
				kuwirama.setVisible(!kuwirama.getVisible());
				this.setToggleTimeOut(true);
			}

			if (i == KeyEvent.VK_Z && kuwirama.getAlpha() >= 0.02) {
				kuwirama.setAlpha(kuwirama.getAlpha()-(float)0.02);
			}

			if (i == KeyEvent.VK_X && kuwirama.getAlpha() <= 0.98) {
				kuwirama.setAlpha(kuwirama.getAlpha()+(float)0.02);
			}

			if (i == KeyEvent.VK_Q) {
				kuwirama.setRotation(kuwirama.getRotation()+5);
			}

			if (i == KeyEvent.VK_W) {
				kuwirama.setRotation(kuwirama.getRotation()-5);
			}

			if (i == KeyEvent.VK_A) {
				if(kuwirama.getScaleX()*kuwirama.getScaleY() >= 0) {
					kuwirama.setScaleX(kuwirama.getScaleX()+0.1);
					kuwirama.setScaleY(kuwirama.getScaleY()+0.1);
				}else {
					kuwirama.setScaleX(kuwirama.getScaleX()-0.1);
					kuwirama.setScaleY(kuwirama.getScaleY()+0.1);					
				}
			}

			if (i == KeyEvent.VK_S) {
				if(kuwirama.getScaleX()*kuwirama.getScaleY() >= 0) {
					kuwirama.setScaleX(kuwirama.getScaleX()-0.1);
					kuwirama.setScaleY(kuwirama.getScaleY()-0.1);
				}else {
					kuwirama.setScaleX(kuwirama.getScaleX()+0.1);
					kuwirama.setScaleY(kuwirama.getScaleY()-0.1);					
				}
			}

			if (i == KeyEvent.VK_I) {
				kuwirama.setPivotPointY(kuwirama.getPivotPointY()-10);
			}

			if (i == KeyEvent.VK_J) {
				kuwirama.setPivotPointX(kuwirama.getPivotPointX()-10);
			}

			if (i == KeyEvent.VK_K) {
				kuwirama.setPivotPointY(kuwirama.getPivotPointY()+10);
			}

			if (i == KeyEvent.VK_L) {
				kuwirama.setPivotPointX(kuwirama.getPivotPointX()+10);
			}
		}
		
		
		if ( translateClick(clickPosX,clickPosY).getX() + kuwirama.getPivotPointX() > 0 && 
				translateClick(clickPosX,clickPosY).getX() < kuwirama.getUnscaledWidth() && 
				translateClick(clickPosX,clickPosY).getY() + kuwirama.getPivotPointY() > 0 && 
				translateClick(clickPosX,clickPosY).getY() < kuwirama.getUnscaledHeight()) {
			if(health > 0) {
				health -= 10; 
			}
			clickPosX = 0;
			clickPosY = 0;  
		} 

		if (health <= 0 && time > 0) result = "Player 2 wins!";

		if (time > 0) {
			time = (int)(60 - clock.getElapsedTime()/1000);
			if(time == 0 && health > 0) result = "Player 1 wins!";
		}
	}

	public Point translateClick(double cx, double cy) {
		double scale = Math.sqrt(cx * cx + cy * cy);
		double tx = scale * Math.cos(Math.acos((cx - kuwirama.getPositionX())/kuwirama.getScaleX()/scale)-kuwirama.getRotation()/180*Math.PI);
		double ty = scale * Math.sin(Math.asin((cy - kuwirama.getPositionY())/kuwirama.getScaleY()/scale)-kuwirama.getRotation()/180*Math.PI);
		
		Point p = new Point((int)tx,(int)ty);
		
		return p;
	}
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){

		super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(kuwirama != null) {
			kuwirama.draw(g);
			g.drawString("Pivot Point Positioin: " + kuwirama.getPivotPointX() + ", " + kuwirama.getPivotPointY(), 1000, 200);
			g.drawString("Position: " + kuwirama.getPositionX()+ ", " + kuwirama.getPositionY(), 1000, 250);
		}

		g.drawString("Timer: " + time, 1000, 100);
		g.drawString("Health: " + health, 1000, 150);
		

		g.drawString(result, 1000, 300);


	}



	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		clickPosX = arg0.getX();
		clickPosY = arg0.getY();
		//Math.cos(Math.acos(clickPosX/kuwirama.getScaleX())-kuwirama.getRotation()/180*Math.PI)
		System.out.println(clickPosX/kuwirama.getScaleX());
		System.out.println(Math.acos(clickPosX/kuwirama.getScaleX()));
		System.out.println(kuwirama.getRotation()/180*Math.PI);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}


	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabTwoGame game = new LabTwoGame();
		game.clock = new GameClock();
		game.start();
		if(game.health == 0 || game.time == 0) {
			game.stop();
		}
	}

}

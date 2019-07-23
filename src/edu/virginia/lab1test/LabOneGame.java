package edu.virginia.lab1test;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.util.GameClock;


/**
 * Example game that utilizes our engine. We can create a simple prototype game with just a couple lines of code
 * although, for now, it won't be a very fun game :)
 * */
public class LabOneGame extends Game implements MouseListener{
		
	//position of the mouse click
	int clickPosX = 0;
	int clickPosY = 0;
	
	int health = 100;
	
	GameClock clock;
	
	String result = "";
	int time = 60;
	
	/* Create a sprite object for our game. We'll use mario */
	Sprite mario = new Sprite("Mario", "Mario.png");
	
	/**
	 * Constructor. See constructor in Game.java for details on the parameters given
	 * */
	public LabOneGame() {
		super("Lab One Test Game", 1200,800);
		this.getMainFrame().addMouseListener(this);
		this.setUpMario();
		
	}
	
	
	public void setUpMario() {
		mario.setAlpha(1);
		mario.setVisible(true);
		mario.setPositionX(128);
		mario.setPositionY(128);
		mario.setScaleX(1);
		mario.setScaleY(1);
		mario.setPivotPointX(64);
		mario.setPivotPointY(64);
		mario.setRotation(0);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		/* Make sure mario is not null. Sometimes Swing can auto cause an extra frame to go before everything is initialized */
		if(mario != null) mario.update(pressedKeys);
		
		for(Integer i: pressedKeys) {
			if (i == KeyEvent.VK_LEFT) {
				mario.setPositionX(mario.getPositionX()-10);

				
				if(mario.getScaleX()>0) {
					mario.setScaleX(mario.getScaleX()*-1);
				}
				
				
			}else if (i == KeyEvent.VK_DOWN) {
				mario.setPositionY(mario.getPositionY()+10);
				//mario.setPivotPointY(mario.getPivotPointY()+10);
			}else if (i==KeyEvent.VK_RIGHT) {
				mario.setPositionX(mario.getPositionX()+10);

				if(mario.getScaleX()<0) {
					mario.setScaleX(mario.getScaleX()*-1);
				}
				
			}else if (i==KeyEvent.VK_UP) {
				mario.setPositionY(mario.getPositionY()-10);
				//mario.setPivotPointY(mario.getPivotPointY()-10);
			}
			
			
			if(mario.getPositionX()<128) mario.setPositionX(128);
			if(mario.getPositionY()<128) mario.setPositionY(128);
			if(mario.getPositionX()>1000) mario.setPositionX(1000);
			if(mario.getPositionY()>600) mario.setPositionY(600);
			
			
			if (i == KeyEvent.VK_V && !this.getToggleTimeOut()) {
				mario.setVisible(!mario.getVisible());
				this.setToggleTimeOut(true);
			}
			
			if (i == KeyEvent.VK_Z && mario.getAlpha() >= 0.02) {
				mario.setAlpha(mario.getAlpha()-(float)0.02);
			}
			
			if (i == KeyEvent.VK_X && mario.getAlpha() <= 0.98) {
				mario.setAlpha(mario.getAlpha()+(float)0.02);
			}
			
			if (i == KeyEvent.VK_Q) {
				mario.setRotation(mario.getRotation()+5);
			}
			
			if (i == KeyEvent.VK_W) {
				mario.setRotation(mario.getRotation()-5);
			}
			
			if (i == KeyEvent.VK_A) {
				mario.setScaleX(mario.getScaleX()+0.1);
				mario.setScaleY(mario.getScaleY()+0.1);
			}
			
			if (i == KeyEvent.VK_S) {
				mario.setScaleX(mario.getScaleX()-0.1);
				mario.setScaleY(mario.getScaleY()-0.1);
			}
			
			if (i == KeyEvent.VK_I) {
				mario.setPivotPointY(mario.getPivotPointY()-10);
			}
			
			if (i == KeyEvent.VK_J) {
				mario.setPivotPointX(mario.getPivotPointX()-10);
			}
			
			if (i == KeyEvent.VK_K) {
				mario.setPivotPointY(mario.getPivotPointY()+10);
			}
			
			if (i == KeyEvent.VK_L) {
				mario.setPivotPointX(mario.getPivotPointX()+10);
			}
		}
		
		if (clickPosX > mario.getPositionX() + 30 && 
				clickPosX < mario.getPositionX()+ 120 && 
				clickPosY > mario.getPositionY()+30  && 
				clickPosY < mario.getPositionY()+160) {
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
	
	/**
	 * Engine automatically invokes draw() every frame as well. If we want to make sure mario gets drawn to
	 * the screen, we need to make sure to override this method and call mario's draw method.
	 * */
	@Override
	public void draw(Graphics g){

		super.draw(g);

		/* Same, just check for null in case a frame gets thrown in before Mario is initialized */
		if(mario != null) {
			mario.draw(g);
		}

		g.drawString("Timer: " + time, 1000, 100);
		g.drawString("Health: " + health, 1000, 150);
		g.drawString("Pivot Point Positioin: " + mario.getPivotPointX() + ", " + mario.getPivotPointY(), 1000, 200);
		g.drawString("Positioin: " + mario.getPositionX()+ ", " + mario.getPositionY(), 1000, 250);
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
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	
	/**
	 * Quick main class that simply creates an instance of our game and starts the timer
	 * that calls update() and draw() every frame
	 * */
	public static void main(String[] args) {
		LabOneGame game = new LabOneGame();
		game.clock = new GameClock();
		game.start();
	}

	
}

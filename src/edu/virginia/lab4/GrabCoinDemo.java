package edu.virginia.lab4;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;

public class GrabCoinDemo extends Game{

	Sprite mario = new Sprite("mario","Mario.png");
	Sprite coin = new Sprite("coin","Coin.png");
	MyQuestManager mqm = new MyQuestManager();
	
	
	public GrabCoinDemo() {
		super("Grab Coin Demo", 1200, 800);
		
		this.setUpMario();
		this.setUpCoin();
	}
	
	public void setUpMario() {
		mario.setAlpha(1);
		mario.setVisible(true);
		mario.setPositionX(64);
		mario.setPositionY(64);
		mario.setScaleX(1);
		mario.setScaleY(1);
		mario.setPivotPointX(64);
		mario.setPivotPointY(64);
		mario.setRotation(0);
	}
	
	public void setUpCoin() {
		coin.setAlpha(1);
		coin.setVisible(true);
		coin.setPositionX(800);
		coin.setPositionY(600);
		coin.setScaleX(0.25);
		coin.setScaleY(0.25);
		coin.setPivotPointX(coin.getUnscaledWidth()/2);
		coin.setPivotPointY(coin.getUnscaledHeight()/2);
		coin.setRotation(0);
		coin.addEventListener(mqm, "coinPickedUp");
	}
	
	
	@Override 
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		
		for(int i: pressedKeys) {
			if(i == KeyEvent.VK_UP && mario.getPositionY() > 64) mario.setPositionY(mario.getPositionY() - 10);
			if(i == KeyEvent.VK_DOWN && mario.getPositionY() < 672) mario.setPositionY(mario.getPositionY() + 10);
			if(i == KeyEvent.VK_LEFT && mario.getPositionX() > 64) mario.setPositionX(mario.getPositionX() - 10);
			if(i == KeyEvent.VK_RIGHT && mario.getPositionX() < 1136) mario.setPositionX(mario.getPositionX() + 10);
		}
		
		if(mario != null && coin != null && coin.getVisible()) {
			if (Math.abs(mario.getPositionX()-coin.getPositionX()) 
					< (mario.getUnscaledWidth()/2 + coin.getUnscaledWidth()*coin.getScaleX()/2) &&
					Math.abs(mario.getPositionY()-coin.getPositionY()) 
					< (mario.getUnscaledHeight()/2 + coin.getUnscaledHeight()*coin.getScaleY()/2)) {
				Event e = new Event(MyQuestManager.COIN_PICKED_UP,coin);
				coin.dispatchEvent(e);
				coin.setVisible(false);
			}
		}
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		if (coin != null) coin.draw(g);
		if (mario != null) mario.draw(g);
		if (mqm != null && mqm.d) {
			g.drawString("Mario picked up the coin!", 700, 700);
		}
	}
	
	public static void main(String[] args) {
		GrabCoinDemo demo = new GrabCoinDemo();
		demo.start();
	}
}

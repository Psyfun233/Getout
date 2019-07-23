package edu.virginia.lab5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GetOut.SoundManager;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.TweenManager;
import edu.virginia.engine.tween.Tween;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.tween.TweenableParam;

public class Lab5Demo extends Game{
	private Sprite background = new Sprite("background","Background.png");
	
	
	private Mario mario = new Mario("Mario","Mario.png");
	TweenJuggler tj = new TweenJuggler();
	Tween marioTween = new Tween(mario);
	Sprite coin = new Sprite("coin","Coin.png");
	Tween coinTween = new Tween(coin);
	Tween coinTween2 = new Tween(coin);
	TweenManager tm = new TweenManager();

	
	//CollisionManager mcl = new CollisionManager();	//mario collide listener
	
	private Sprite f1 = new Sprite("floor","Floor.png");
	private Sprite f2 = new Sprite("floor","Floor.png");

	boolean gotCoin = false;
	
	boolean soundPlayed = false;
	//private Sprite mario_child1 = new Sprite("mario child");
	
	public Lab5Demo(String gameId, int width, int height) {
		super(gameId, width, height);
		
		//this.addEventListener(mcl, "collision");
		
		mario.setUp(200, 300, 0.6, 0.6);
		marioTween.animate(TweenableParam.ALPHA, 0, 1, 3000);
		TweenJuggler.getJuggler().add(marioTween);
		
		setUpFloor(f1, 150, 600);
		setUpFloor(f2, 750, 500);
		setUpCoin();
		coinTween.addEventListener(tm, "TweenComplete");
		//Event e = new Event(TweenManager.TWEEN_COMPLETED,coinTween2);
		//coinTween2.dispatchEvent(e);
		
		this.addChild(background);
		this.addChild(f1);
		this.addChild(f2);
		this.addChild(coin);
		this.addChild(mario);
	}
	
	public void setUpCoin() {
		coin.setAlpha(1);
		coin.setVisible(true);
		coin.setPositionX(1000);
		coin.setPositionY(325);
		coin.setScaleX(0.25);
		coin.setScaleY(0.25);
		coin.setPivotPointX(coin.getUnscaledWidth()/2);
		coin.setPivotPointY(coin.getUnscaledHeight()/2);
		coin.setRotation(0);
	}
	
	public void setUpFloor(Sprite f, int x, int y) {
		f.setScaleX(2);
		f.setScaleY(0.25);
		f.setPositionX(x);
		f.setPositionY(y);
	}


	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		if(mario != null) {
			
			
			TweenJuggler.getJuggler().update();
			mario.oldX = mario.getPositionX();
			mario.oldY = mario.getPositionY();
			mario.calculateX();
			if(mario.collideWith(f1) || mario.collideWith(f2)) {
				mario.setPositionX(mario.oldX);
				mario.setVx(0);
			}
			mario.calculateY();
			if(mario.collideWith(f1)||mario.collideWith(f2)) {
				mario.setPositionY(mario.oldY);
				mario.setVy(0);
				mario.setAy(0);
				mario.inAir = false;
			}
			mario.update(pressedKeys);
		}

		if(pressedKeys.contains(KeyEvent.VK_D))	{
			mario.setVx(0.25);
		}
		
		if(pressedKeys.contains(KeyEvent.VK_A)){
			mario.setVx(-0.25);
		}
		
		if(!pressedKeys.contains(KeyEvent.VK_D) && !pressedKeys.contains(KeyEvent.VK_A) ) {
				mario.setVx(0);
		}

		if(pressedKeys.contains(KeyEvent.VK_SPACE) && !mario.inAir) {
			mario.setAy(-0.0275);
			mario.inAir = true;
		}else {
			mario.setAy(0.0);
		}

		if(pressedKeys.contains(KeyEvent.VK_C))		mario.setRotation(mario.getRotation()+2);
		if(pressedKeys.contains(KeyEvent.VK_X)) 	mario.setRotation(mario.getRotation()-2);
		
		if(mario.collideWith(coin) && !soundPlayed) {
			coinTween.animate(TweenableParam.X_POS, coin.getPositionX(), 700, 3000);
			coinTween.animate(TweenableParam.Y_POS, coin.getPositionY(), 400, 3000);
			coinTween.animate(TweenableParam.X_SCALE, coin.getScaleX(), 1, 3000);
			coinTween.animate(TweenableParam.Y_SCALE, coin.getScaleY(), 1, 3000);
			TweenJuggler.getJuggler().add(coinTween);
			gotCoin = true;
			new SoundManager();
			soundPlayed = true;
		}
		if(tm.coinFade) {
			System.out.println("coin fade");
			coinTween2.animate(TweenableParam.ALPHA, 1, 0, 1000);
			coinTween2.animate(TweenableParam.ROTATION, 0, 3000, 1000);
			TweenJuggler.getJuggler().add(coinTween2);
			tm.coinFade = false;
		}
	}

	@Override
	public void draw(Graphics g){
		super.draw(g);
		Graphics2D g2d = (Graphics2D)g;
		
		if(mario != null) {		
			if(gotCoin) {
				g2d.drawString("Mario got the coin!", 500, 600);
			}
			g2d.drawString("mario position: "+mario.getPositionX() + "," +mario.getPositionY(), 1000, 300);
		}
	}

	
	public static void main(String[] args) {
		Lab5Demo demo = new Lab5Demo("Hitbox Demo", 1400,800);
		demo.start();
	}
}

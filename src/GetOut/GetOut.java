package GetOut;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.util.Camera;
import edu.virginia.engine.util.GameClock;

public class GetOut extends Game{

	int gameTime;

	//contain all objects in a certain level
	DisplayObjectContainer generalContainer = new DisplayObjectContainer("general container");
	
	//contain objects that can collide
	DisplayObjectContainer collisionContainer = new DisplayObjectContainer("collision container");
	
	//camera that can follow the player around
	Camera cam = new Camera(0,0);

	//font for message printing
	Font bigFont = new Font("Courier",Font.PLAIN,30);
	
	TweenJuggler tweenJuggler = new TweenJuggler();
	
	int progress = 0;
	
	
	
	boolean fail = false;
	boolean win = false;
	
	//game event manager responsible for changing levels
	private static GameEventManager gameEventManager;
	
	static GameClock newclock;
	private static SoundManager mySoundManager;
	int presscount = 0;
	boolean soundlastframe = false;
	ArrayList<Double> plist = new ArrayList<Double>(); 
	int movingcount = 0;
	boolean backgroundPlayed = false;
	
	GameSounds sounds = new GameSounds();
	
	// all game levels and cut scenes
	StartScreen startScreen = new StartScreen("start screen");
	FallAsleepCutScene cs1 = new FallAsleepCutScene("fall asleep cut scene");
	IntroLevel lvi = new IntroLevel("intro level container");
	LevelTwo lv2 = new LevelTwo("level two container");
	LevelThree lv3 = new LevelThree("level three container");
	LevelFour lv4 = new LevelFour("level four container");
	LevelFive lv5 = new LevelFive("level five container");
	

	/*
	 * **************************************** Constructor ************************************************
	 */
	public GetOut(String gameId, int width, int height) {
		super(gameId, width, height);
		this.getScenePanel().setBackground(Color.BLACK);
		

		this.addChild(generalContainer);
		
		// load start screen
		startScreen.load(generalContainer);
		
		gameEventManager = new GameEventManager(this);
		
		startScreen.addEventListener(gameEventManager, GameEventManager.START_CUT_SCENE_1);
		cs1.addEventListener(gameEventManager, GameEventManager.START_INTRO_LEVEL);
		lvi.addEventListener(gameEventManager, GameEventManager.START_LEVEL_TWO);
		lv2.addEventListener(gameEventManager, GameEventManager.START_LEVEL_THREE);
		lv3.addEventListener(gameEventManager, GameEventManager.START_LEVEL_FOUR);
		lv4.addEventListener(gameEventManager, GameEventManager.START_LEVEL_FIVE);
		

		mySoundManager = new SoundManager();
		mySoundManager.loadMusic("bgm", "bgm.wav");
		mySoundManager.changeMusicVolume("bgm", 0.9f);
		mySoundManager.playMusic("bgm");
		plist.clear();
		for (int i = 0 ; i < 5; i++) plist.add(0.2 + 0.04 * i);
		for (int i = 0; i < 8; i++) plist.add(0.4 + 0.025 * i);
		for (int i = 0; i < 16 ; i++) plist.add(0.6 + 0.0125 * i);
		for (int i = 0; i < 15 ; i++) plist.add(0.8 + 0.0125 * i);
		
		//load all the sounds
		sounds.loadAllSounds();

	}

	/*
	 * **************************************** Update Loop *********************************************
	 * @see edu.virginia.engine.display.DisplayObjectContainer#update(java.util.ArrayList)
	 */
	
	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		
		if(startScreen.playing) {
			startScreen.update(pressedKeys, cam);
		}else if(cs1.playing) {
			cs1.update(pressedKeys,generalContainer, collisionContainer, cam);
		}else if (lvi.playing) {
			lvi.updateAlpha(generalContainer);
			lvi.update(pressedKeys, generalContainer, collisionContainer, cam);
		}else if(lv2.playing ){
			lv2.updateAlpha(generalContainer);
			lv2.update(pressedKeys, generalContainer, collisionContainer, cam);
		}else if(lv3.playing) {
			lv3.updateAlpha(generalContainer);
			lv3.update(pressedKeys, generalContainer, collisionContainer, cam);
		}else if(lv4.playing) {
			lv4.updateAlpha(generalContainer);
			lv4.update(pressedKeys, generalContainer, collisionContainer, cam);
		}else if(lv5.playing) {
			lv5.updateAlpha(generalContainer);
			lv5.update(pressedKeys, generalContainer, collisionContainer, cam);
			if(lv5.player.collideWith(lv5.exit)) win = true;
		}
		
		
		
		if (!pressedKeys.isEmpty()) {
			if (movingcount == 0) {
				mySoundManager.loadSoundEffect("player_step", "player_step.wav");
				mySoundManager.changeEffectVolume("player_step", 0.7f);
				mySoundManager.playSoundEffect("player_step");
				movingcount = 20; //40 frame one moving sound
			}
		} 
		
		if (movingcount != 0) {
			movingcount --;
		}
		
		if (progress > 0 && playstep(newclock.getElapsedTime() / gameTime) && (soundlastframe == false) && !win) {
			mySoundManager.loadSoundEffect("step", "step.wav");
			float temp = (float)((newclock.getElapsedTime() / gameTime)*0.5+0.5);
			mySoundManager.changeEffectVolume("step", temp);
			mySoundManager.playSoundEffect("step");
			soundlastframe = true;
		}
		else {
			soundlastframe = false;
		}
		
		// play scream sound after losing
		if (progress > 0 && (fail == false) && newclock.getElapsedTime() > gameTime && !win) {
			mySoundManager.loadSoundEffect("scream", "scream.wav");
			mySoundManager.changeEffectVolume("scream", 1.0f);
			mySoundManager.playSoundEffect("scream");
			fail = true;
		}
		
		// to restart the game after losing
		if(fail && pressedKeys.contains(KeyEvent.VK_R)) {
			fail = false;
			generalContainer.removeAll();
			collisionContainer.removeAll();
			
			
			startScreen = new StartScreen("start screen");
			cs1 = new FallAsleepCutScene("fall asleep cut scene");
			lvi = new IntroLevel("intro level container");
			lv2 = new LevelTwo("level two container");
			lv3 = new LevelThree("level three container");
			lv4 = new LevelFour("level four container");
			lv5 = new LevelFive("level five container");
			
			startScreen.addEventListener(gameEventManager, GameEventManager.START_CUT_SCENE_1);
			cs1.addEventListener(gameEventManager, GameEventManager.START_INTRO_LEVEL);
			lvi.addEventListener(gameEventManager, GameEventManager.START_LEVEL_TWO);
			lv2.addEventListener(gameEventManager, GameEventManager.START_LEVEL_THREE);
			lv3.addEventListener(gameEventManager, GameEventManager.START_LEVEL_FOUR);
			lv4.addEventListener(gameEventManager, GameEventManager.START_LEVEL_FIVE);
			
			
			gameTime = 60000;
			
			if(progress == 0) cs1.load(generalContainer, collisionContainer);
			else if(progress == 1) lvi.load(generalContainer, collisionContainer);
			else if(progress == 2) lv2.load(generalContainer, collisionContainer);
			else if(progress == 3) lv3.load(generalContainer, collisionContainer);
			else if(progress == 4) lv4.load(generalContainer, collisionContainer);
			else if(progress == 5) lv5.load(generalContainer, collisionContainer);
			
			newclock.resetGameClock();
			mySoundManager.loadMusic("bgm", "bgm.wav");
			mySoundManager.changeMusicVolume("bgm", 0.9f);
			mySoundManager.playMusic("bgm");
			soundlastframe = false;
			backgroundPlayed = false;
			plist.clear();
			for (int i = 0 ; i < 5; i++) plist.add(0.2 + 0.04 * i);
			for (int i = 0; i < 8; i++) plist.add(0.4 + 0.025 * i);
			for (int i = 0; i < 16 ; i++) plist.add(0.6 + 0.0125 * i);
			

		}

	}
	
	/*
	 * ************************************************** draw loop **************************************************
	 * @see edu.virginia.engine.display.Game#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		if(!fail && !win && cam != null) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.translate(this.getMainFrame().getWidth()/2-cam.getCamX(), 
					this.getMainFrame().getHeight()/2-cam.getCamY());
			super.draw(g2d);
			Font font = new Font("Courier",Font.PLAIN,12);
			g2d.setFont(font);
			g2d.setColor(Color.WHITE);
			
			
			
			if(lvi!= null && lvi.playing){
				lvi.drawMessage(g, newclock);
				if((float)((gameTime - newclock.getElapsedTime())/1000) < (0.4f*(float)gameTime/1000f)) {
					g2d.drawString("Level 1", cam.getCamX()-350, cam.getCamY()-150);
					g2d.drawString("Time Left: " + (int)((gameTime - newclock.getElapsedTime())/1000), cam.getCamX()+250, cam.getCamY()-150);

				}
			}

			if(lv2!= null && lv2.playing){
				lv2.drawMessage(g, newclock);
				g2d.drawString("Level 2", cam.getCamX()-350, cam.getCamY()-150);
				g2d.drawString("Time Left: " + (int)((gameTime - newclock.getElapsedTime())/1000), cam.getCamX()+250, cam.getCamY()-150);
			}

			if(lv3!= null && lv3.playing){
				lv3.drawMessage(g, newclock);
				g2d.drawString("Level 3", cam.getCamX()-350, cam.getCamY()-150);
				g2d.drawString("Time Left: " + (int)((gameTime - newclock.getElapsedTime())/1000), cam.getCamX()+250, cam.getCamY()-150);
	
			}
			if(lv4!= null && lv4.playing){
				lv4.drawMessage(g, newclock);
				g2d.drawString("Level 4", cam.getCamX()-350, cam.getCamY()-150);
				g2d.drawString("Time Left: " + (int)((gameTime - newclock.getElapsedTime())/1000), cam.getCamX()+250, cam.getCamY()-150);
			}
			if(lv5!= null && lv5.playing){
				lv5.drawMessage(g, newclock);
				g2d.drawString("Level 5", cam.getCamX()-350, cam.getCamY()-150);
				g2d.drawString("Time Left: " + (int)((gameTime - newclock.getElapsedTime())/1000), cam.getCamX()+250, cam.getCamY()-150);
			}
			
		}else if (fail && !win){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setFont(bigFont);
			g2d.setColor(Color.RED);
			g2d.drawString("You Woke Up without Escaping", (int) (this.getMainFrame().getWidth()*0.25), 
					(int)(this.getMainFrame().getHeight()*0.25));
			g2d.drawString("Press R to try next night", (int) (this.getMainFrame().getWidth()*0.25), 
					(int)(this.getMainFrame().getHeight()*0.25 + 100));
			mySoundManager.stopMusic("bgm");
		}else if (!fail && win) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setFont(bigFont);
			g2d.setColor(Color.WHITE);
			g2d.drawString("You Escaped Your Nightmare!",(int) (this.getMainFrame().getWidth()*0.25), 
					(int)(this.getMainFrame().getHeight()*0.5));
			mySoundManager.stopMusic("bgm");
		}
	}
	
	
	
	public boolean playstep (double percent) {
		if (plist.isEmpty()) return false;
		if (percent >= plist.get(0)) {
			Iterator<Double> it = plist.iterator();
			while(it.hasNext()) {
				Double p = it.next();
				if (p < percent)
					it.remove();
			}	
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String[] arg) {
		GetOut demo = new GetOut("Get Out Demo",800, 400);
		newclock = new GameClock();
		demo.start();
	}
}

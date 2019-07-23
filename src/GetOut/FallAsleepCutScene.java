package GetOut;

import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.Camera;

public class FallAsleepCutScene extends Level{

	Player player = new Player ("player","player_walk_1.png",16,"player_walk");
	
	AnimatedSprite scene = new AnimatedSprite("bed scene","bedroom.png",2,"fall_asleep");
	
	int animationTime = 500;
	
	int currentFrame = 0;
	
	boolean playing = false;
	
	public FallAsleepCutScene(String id) {
		super(id);
	}
	

	
	@Override
	public void load(DisplayObjectContainer gc, DisplayObjectContainer cc){
		this.playing = true;
		
		scene.setPositionX(50);
		scene.setPositionY(50);
		scene.setVisible(true);
		scene.setScaleX(0.5);
		scene.setScaleY(0.5);
		scene.setPlaying(false);
		scene.setPivotPointToCenter();
		player.setUp(90, 50, 0.5, 0.5);
		
		scene.setCurrentFrame(0);
		scene.setStartIndex(0);
		scene.setEndIndex(1);
		scene.setAnimationSpeed(20);
		
		gc.addChild(scene);
		gc.addChild(player);
		
	}

	@Override
	public void update(ArrayList<Integer> pressedKeys,DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
		super.update(pressedKeys);
		
		//this.playing = false;
		//this.dispatchEvent(new Event(GameEventManager.START_INTRO_LEVEL,this));
		
		if(player != null) player.update(pressedKeys);
		if(scene != null) scene.update(pressedKeys);
		
		if(currentFrame > 180 && currentFrame < 260) {
			this.player.setPositionY(this.player.getPositionY()-1);
			this.player.setPlaying(true);
			this.player.setStartIndex(12);
			this.player.setEndIndex(15);
		}
		
		if(currentFrame >= 260 && currentFrame < 265) {
			this.player.setPositionX(this.player.getPositionX()-1);
			this.player.setPlaying(true);
			this.player.setStartIndex(4);
			this.player.setEndIndex(7);
		}
		
		if(currentFrame >= 265 && currentFrame < 280) {
			this.player.setPlaying(false);
		}
		
		if(currentFrame >= 280 && currentFrame < 500) {
			this.player.setVisible(false);
			scene.setPlaying(true);
		}
		
		cam.setCamX(scene.getPositionX());
		cam.setCamY(scene.getPositionY());
		
		if(currentFrame == animationTime) {
			this.playing = false;
			this.dispatchEvent(new Event(GameEventManager.START_INTRO_LEVEL,this));
		}else {
			currentFrame ++;
		}
	}

	
}

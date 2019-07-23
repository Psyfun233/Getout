package GetOut;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.AnimatedSprite;
import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.Camera;

public class StartScreen extends DisplayObjectContainer{

	AnimatedSprite startScreenSprite = new AnimatedSprite("start screen", "Start_Screen_1.png",2,"Start_Screen");
	boolean playing = false;
	
	public StartScreen(String id) {
		super(id);
	}

	public void load(DisplayObjectContainer gc) {
		this.playing = true;
		
		gc.addChild(startScreenSprite);
		startScreenSprite.setPositionX(0);
		startScreenSprite.setPositionY(0);
		startScreenSprite.setVisible(true);
		startScreenSprite.setScaleX(1);
		startScreenSprite.setScaleY(1);
		startScreenSprite.setPlaying(true);
		startScreenSprite.setPivotPointToCenter();
		
		startScreenSprite.setCurrentFrame(0);
		startScreenSprite.setStartIndex(0);
		startScreenSprite.setEndIndex(1);
		startScreenSprite.setAnimationSpeed(20);
	}
	

	public void update(ArrayList<Integer> pressedKeys, Camera cam) {
		super.update(pressedKeys);
		
		if(startScreenSprite != null) startScreenSprite.update(pressedKeys);
		
		startScreenSprite.setPlaying(true);
		cam.setCamX(startScreenSprite.getPositionX());
		cam.setCamY(startScreenSprite.getPositionY());
		
		if(pressedKeys.contains(KeyEvent.VK_E)) {
			playing = false;
			Event e = new Event(GameEventManager.START_CUT_SCENE_1,this);
			this.dispatchEvent(e);
		}
	}

}

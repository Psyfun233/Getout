package GetOut;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class GameEventManager implements IEventListener{
	public static final String START_CUT_SCENE_1 = "start cut scene 1";
	public static final String START_INTRO_LEVEL = "start intro level";
	public static final String START_LEVEL_TWO = "start level 2";
	public static final String START_LEVEL_THREE = "start level 3";
	public static final String START_LEVEL_FOUR = "start level 4";
	public static final String START_LEVEL_FIVE = "start level 5";
	
	
	private GetOut game;
	
	public GameEventManager(GetOut game) {
		this.game = game;
	}
	
	@Override
	public void handleEvent(Event e) {
		if(e.getEventType().equals(GameEventManager.START_CUT_SCENE_1)) {
			game.gameTime = 50000;
			game.progress = 0;
			loadLevel(game.cs1,game.generalContainer, game.collisionContainer);
		}
		
		if(e.getEventType().equals(GameEventManager.START_INTRO_LEVEL)) {
			game.gameTime = 60000;
			game.progress = 1;
			loadLevel(game.lvi,game.generalContainer, game.collisionContainer);
		}
		
		if(e.getEventType().equals(GameEventManager.START_LEVEL_TWO)) {
			game.gameTime = 60000;
			game.progress = 2;
			loadLevel(game.lv2,game.generalContainer, game.collisionContainer);
		}
		
		if(e.getEventType().equals(GameEventManager.START_LEVEL_THREE)) {
			game.gameTime = 60000;
			game.progress = 3;
			loadLevel(game.lv3,game.generalContainer, game.collisionContainer);
		}
		
		if(e.getEventType().equals(GameEventManager.START_LEVEL_FOUR)) {
			game.gameTime = 60000;
			game.progress = 4;
			loadLevel(game.lv4,game.generalContainer, game.collisionContainer);
		}
		
		if(e.getEventType().equals(GameEventManager.START_LEVEL_FIVE)) {
			game.gameTime = 60000;
			game.progress = 5;
			loadLevel(game.lv5,game.generalContainer, game.collisionContainer);
		}
	}
	
	public void loadLevel(Level l, DisplayObjectContainer gc, DisplayObjectContainer cc) {
		gc.removeAll();
		cc.removeAll();
		l.load(gc, cc);
	}
}

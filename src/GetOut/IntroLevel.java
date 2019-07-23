package GetOut;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.tween.Tween;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.tween.TweenableParam;
import edu.virginia.engine.util.Camera;
import java.awt.Graphics2D;
import edu.virginia.engine.util.GameClock;

public class IntroLevel extends  Level{

	Wall w1 = new Wall("wall1");
	Wall w2 = new Wall("wall2");
	Wall w3 = new Wall("wall3");
	Wall w4 = new Wall("wall4");
	Wall w5 = new Wall("wall5");
	Stone stone = new Stone("stone", "ruby.png");
	Tile tile = new Tile("tile", "red_tile.png");
	Door door = new Door("door", "reddoor_closed.png");
	
	DisplayObjectContainer portalEntrance = new DisplayObjectContainer("portal entrance");
	Portal portal1 = new Portal("portal1", "Blue_Portal.png");
	Portal portal2 = new Portal("portal2", "Yellow_portal.png");
	
	Tween playerTween = new Tween(player);

	
	public IntroLevel(String id) {
		super(id);
	}

	@Override
	public void load(DisplayObjectContainer gc, DisplayObjectContainer cc) {
	    super.load(gc,cc);

	    playing = true;
		
		player.setUp(132, 132, 0.5, 0.5);
		player.setPivotPointToCenter();
		stone.setUp(200,200, 0.25, 0.25);
		tile.setUp(350,350, 1, 1);
		
		portalEntrance.addChild(portal1);
		portal1.setUp(250, 250, 0.05, 0.05);
		portal2.setUp(380, 380, 0.1,0.1);

		door.setUp(420, 420, 1, 1);
		
		exit.setPositionX(580);
		exit.setPositionY(420);
		exit.setPivotPointToCenter();
		
		w1.setUp(100, 100, 11, 12,"wall_5.png");
		w2.setUp(420, 388, 6, -9,"wall_5.png");
		w3.setUp(132, 452, 15, 0,"wall_5.png");
		w4.setUp(292, 292, 5, 4, "wall_5.png");
		w5.setUp(164, 388, 4, -3, "wall_5.png");
		
		
		gc.addChild(exit);
		gc.addChild(door);
		gc.addChild(w1);
		gc.addChild(w2);
		gc.addChild(w3);
		gc.addChild(w4);
		gc.addChild(w5);
		gc.addChild(stone);
		gc.addChild(tile);
		gc.addChild(portal1);
		gc.addChild(portal2);
		gc.addChild(player);
		cc.addChild(w1);
		cc.addChild(w2);
		cc.addChild(w3);
		cc.addChild(w4);
		cc.addChild(w5);
		cc.addChild(door);
	}
	
    @Override
	public void update(ArrayList<Integer> pressedKeys,DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
		super.update(pressedKeys,gc,cc, cam);

//		this.playing = false;
//		this.dispatchEvent(new Event(GameEventManager.START_LEVEL_TWO,this));

        if(this.player != null) this.player.update(pressedKeys);
        if(this.exit != null) this.exit.update(pressedKeys);
		if(this.door != null) this.door.update(pressedKeys);



		double distance  = this.stone.getDistance(this.player);
		if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone.isPickUp()){
		    player.setHasRuby(true);
			stone.setPickUp(false);
			player.addChild(this.stone);
			gc.removeChild(this.stone);
			this.stone.setUp(-10000,-10000,0.1,0.1);
			if(tile.isActive()) {
				tile.setActive(false);
				door.close();
				cc.addChild(door);
			}

		}

		double distance2 = this.tile.getDistance(this.player);
		if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone)&&(stone.isPickUp())){

			stone.setPickUp(false);
			this.player.removeChild(this.stone);
			player.setHasRuby(false);
			this.stone.setUp(this.tile.getPositionX(),this.tile.getPositionY(),0.1,0.1);
			tile.setActive(true);
			if(!this.door.isOpen()){
				this.door.open();
			}
			cc.removeChild(this.door);
		}
		
		if(!pressedKeys.contains(KeyEvent.VK_E)) {
			stone.setPickUp(true);
		}

		
		TweenJuggler.getJuggler();
		if(player.collideWith(portalEntrance) && !TweenJuggler.getTweenList().contains(playerTween)) {
			player.setVisible(false);
			playerTween.animate(TweenableParam.X_POS, player.getPositionX(), 
					portal2.getPositionX(), 1000);
			playerTween.animate(TweenableParam.Y_POS, player.getPositionY(), 
					portal2.getPositionY(), 1000);
			TweenJuggler.getJuggler().add(playerTween);
		}
		
		if(player.collideWith(portal2)) {
			player.setVisible(true);
		}
		
		//after all the updates, update the camera to the player's position
		cam.setCamX(this.player.getPositionX());
		cam.setCamY(this.player.getPositionY());
		
		TweenJuggler.getJuggler().update();

        if(player.collideWith(exit)) {
            this.playing = false;
            this.dispatchEvent(new Event(GameEventManager.START_LEVEL_TWO,this));
        }

	}
	

	public void drawMessage(Graphics g, GameClock time ){
		Graphics2D g2d = (Graphics2D)g;
		if(time.getElapsedTime() < 20000){
			g2d.drawString("Where am I?", player.getPositionX()-48, player.getPositionY()-16);
		}

		g2d.drawString(stone.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

		g2d.drawString(tile.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

	}


	
}

package GetOut;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.tween.Tween;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.tween.TweenableParam;
import edu.virginia.engine.util.Camera;
import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LevelTwo extends Level{

//    Player player = new Player ("player","player_walk_1.png",16,"player_walk");
    Wall w1 = new Wall("wall1");
    Wall w2 = new Wall("wall2");
    Wall w3 = new Wall("wall3");
    Wall w4 = new Wall("wall4");
    Wall w5 = new Wall("wall5");
    Wall w6 = new Wall("wall6");
    Wall w7 = new Wall("wall7");
    Wall w8 = new Wall("wall8");
    Wall w9 = new Wall("wall9");
    Wall w10 = new Wall("wall10");
    Wall w11 = new Wall("wall11");
    Wall w12 = new Wall("wall12");
    Wall w13 = new Wall("wall13");
    Wall w14 = new Wall("wall14");
    Wall w15 = new Wall("wall15");
    Wall w16 = new Wall("wall16");
    Wall w17 = new Wall("wall17");
    Wall w18 = new Wall("wall18");
    Wall w19 = new Wall("wall19");
//    PhysicsSprite exit = new PhysicsSprite ("exit","exit.png");
    Stone stone = new Stone("sapphire", "sapphire.png");
    Tile tile = new Tile("blue tile", "blue_tile.png");
    Door door = new Door("blue door", "bluedoor_closed.png");
    Portal portal1 = new Portal("portal1", "Blue_Portal.png");
    Portal portal2 = new Portal("portal2", "Yellow_portal.png");
    
    DisplayObjectContainer portalEntrance = new DisplayObjectContainer("portal entrance");
	Tween playerTween = new Tween(player);
	boolean playing = false;

    public LevelTwo(String id) {
        super(id);
    }

    public void load(DisplayObjectContainer gc, DisplayObjectContainer cc) {
    	this.playing = true;
    	
        player.setPositionX(300);
        player.setPositionY(164);
        player.setScaleX(0.5);
        player.setScaleY(0.5);
        player.setPivotPointToCenter();

        stone.setUp(1010,670, 0.25, 0.25);
        tile.setUp(680,30, 1, 1);

        portalEntrance.addChild(portal1);
        portal1.setUp(1020, 730, 0.05, 0.05);
        portal2.setUp(380, 200, 0.1,0.1);

        door.setUp(740, 68, 1, 1);

        exit.setPositionX(932);
        exit.setPositionY(68);
        exit.setPivotPointToCenter();


        w1.setUp(228, 100, 5, 11,"wall_5.png");
        w2.setUp(420, -60, 0, 15,"wall_5.png");
        w3.setUp(228, 452, 7, 0,"wall_5.png");
        w4.setUp(452,388, 20,0,"wall_5.png");
        w5.setUp(452,452,10,0,"wall_5.png");
        w6.setUp(740,452,0,10,"wall_5.png");
        w7.setUp(1060,388,0,12,"wall_5.png");
        w8.setUp(740,772,11,0,"wall_5.png");
        w9.setUp(772,484,6,0,"wall_5.png");
        w10.setUp(996,388,0,8,"wall_5.png");
        w11.setUp(804, 580,5,6,"wall_5.png");
        w12.setUp(932,580,0,5,"wall_5.png");
        w13.setUp(356,-92,0,6,"wall_5.png");
        w14.setUp(356, -92, 12,0 ,"wall_5.png");
        w15.setUp(420, -28, 6,0,"wall_5.png");
        w16.setUp(612,-60,0,6,"wall_5.png");
        w17.setUp(740,-124,0,6,"wall_5.png");
        w18.setUp(612,100,11,0,"wall_5.png");
        w19.setUp(772,36,6,0,"wall_5.png");

        gc.addChild(player);
        gc.addChild(exit);
        gc.addChild(door);
        gc.addChild(w1);
        gc.addChild(w2);
        gc.addChild(w3);
        gc.addChild(w4);
        gc.addChild(w5);
        gc.addChild(w6);
        gc.addChild(w7);
        gc.addChild(w8);
        gc.addChild(w9);
        gc.addChild(w10);
        gc.addChild(w11);
        gc.addChild(w12);
        gc.addChild(w13);
        gc.addChild(w14);
        gc.addChild(w15);
        gc.addChild(w16);
        gc.addChild(w17);
        gc.addChild(w18);
        gc.addChild(w19);
        gc.addChild(stone);
        gc.addChild(tile);
        gc.addChild(portal1);
        gc.addChild(portal2);

        cc.addChild(w1);
        cc.addChild(w2);
        cc.addChild(w3);
        cc.addChild(w4);
        cc.addChild(w5);
        cc.addChild(w6);
        cc.addChild(w7);
        cc.addChild(w8);
        cc.addChild(w9);
        cc.addChild(w10);
        cc.addChild(w11);
        cc.addChild(w12);
        cc.addChild(w13);
        cc.addChild(w14);
        cc.addChild(w15);
        cc.addChild(w16);
        cc.addChild(w17);
        cc.addChild(w18);
        cc.addChild(w19);
        cc.addChild(door);

    }

    public void update(ArrayList<Integer> pressedKeys, DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
        super.update(pressedKeys,gc,cc,cam);

//        this.playing = false;
//		this.dispatchEvent(new Event(GameEventManager.START_LEVEL_THREE,this));


        if(this.door != null) this.door.update(pressedKeys);

        double distance  = this.stone.getDistance(this.player);
		if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone.isPickUp()){
			stone.setPickUp(false);
			player.addChild(this.stone);
			gc.removeChild(this.stone);
            player.setHasSapphire(true);
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
            player.setHasSapphire(false);
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
			this.dispatchEvent(new Event(GameEventManager.START_LEVEL_THREE,this));
		}
    }



    public void drawMessage(Graphics g, GameClock time ){
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawString(stone.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
    }
}


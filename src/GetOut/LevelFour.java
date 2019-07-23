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
import edu.virginia.engine.util.GameClock;

public class LevelFour extends Level{

//	Player player = new Player ("player","player_walk_1.png",16,"player_walk");
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
    Wall w20 = new Wall("wall20");
    Wall w21 = new Wall("wall21");
    Wall w22 = new Wall("wall22");
    Wall w23 = new Wall("wall23");
    
//	PhysicsSprite exit = new PhysicsSprite ("exit","exit.png");
    Stone stone1 = new Stone("ruby", "ruby.png");
    Stone stone2 = new Stone("emerald", "emerald.png");
    Tile tile1 = new Tile("red tile", "red_tile.png");
    Tile tile2 = new Tile("green tile", "green_tile.png");
    Door door1 = new Door("red door", "reddoor_closed.png");
    Door door2 = new Door("green door", "greendoor_closed.png");
    Portal portal1 = new Portal("portal1", "Blue_Portal.png");
    Portal portal2 = new Portal("portal2", "Yellow_portal.png");
    Portal portal3 = new Portal("portal3", "Blue_Portal.png");
    Portal portal4 = new Portal("portal4", "Yellow_portal.png");
	
    DisplayObjectContainer portalEntrance = new DisplayObjectContainer("portal entrance");
    
    boolean playing = false;
	Tween playerTween = new Tween(player);
    
	public LevelFour(String id) {
		super(id);
	}

	public void load(DisplayObjectContainer gc, DisplayObjectContainer cc) {
		playing = true;
		
        player.setUp(300, 300, 0.5, 0.5);
        player.setPivotPointToCenter();
        stone1.setUp(36, 36, 0.25, 0.25);
        stone2.setUp(556,396, 0.25,0.25);
        tile1.setUp(576,576, 1, 1);
        tile2.setUp(332, 230, 1, 1);
        
        portalEntrance.addChild(portal1);
        portalEntrance.addChild(portal3);
        portal1.setUp(-100, 596, 0.05, 0.05);
        portal2.setUp(332, 44, 0.1,0.1);      
        portal3.setUp(580, 230, 0.05, 0.05);
        portal4.setUp(-200, 596, 0.1,0.1);       
        door2.setUp(588, 12, 1, 1);
        door1.setUp(12, 588, 1, 1);

        exit.setPositionX(588);
        exit.setPositionY(-148);
        exit.setPivotPointToCenter();
		
        w1.setUp(300, 268, 10, -9,"wall_5.png");
        w2.setUp(12, 12, 18, 18,"wall_5.png");
        w3.setUp(620,620, -20, -20,"wall_5.png");
        w4.setUp(236,332,4,-4,"wall_5.png");
        w5.setUp(108,172,4,12,"wall_5.png");
        w6.setUp(236,76,-6,4,"wall_5.png");
        w7.setUp(76,44,1,0,"wall_5.png");
        w8.setUp(172,172,0,7, "wall_5.png");
        
        w9.setUp(172,396,4,0,"wall_5.png");
        w10.setUp(268,396,0,3,"wall_5.png");
        w11.setUp(268,492,9,0,"wall_5.png");
        
        w12.setUp(332,332,0,3,"wall_5.png");
        w13.setUp(332,428,9,0,"wall_5.png");
        w14.setUp(396,268,0,4,"wall_5.png");
        w15.setUp(460,364,-2,-2,"wall_5.png");
        w16.setUp(524,460,0,-5,"wall_5.png");
        
        w17.setUp(556,460,0,3,"wall_5.png");
        w18.setUp(76,140,0,7,"wall_5.png");
        w19.setUp(44,364,0,6,"wall_5.png");
        w20.setUp(556,-148,1,5,"wall_5.png");
        w21.setUp(620,-148,1,5,"wall_5.png");
		
        w22.setUp(-244,556,8,4,"wall_5.png");
        w23.setUp(-244,652,9,0,"wall_5.png");
		
		gc.addChild(exit);
		gc.addChild(door1);
		gc.addChild(door2);
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
		gc.addChild(w20);
		gc.addChild(w21);
		gc.addChild(w22);
		gc.addChild(w23);
		gc.addChild(stone1);
		gc.addChild(stone2);
		gc.addChild(tile1);
		gc.addChild(tile2);
		gc.addChild(portal1);
		gc.addChild(portal2);
		gc.addChild(portal3);
		gc.addChild(portal4);
		gc.addChild(player);
		
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
		cc.addChild(w20);
		cc.addChild(w21);
		cc.addChild(w22);
		cc.addChild(w23);
		cc.addChild(door1);
		cc.addChild(door2);
	}
	

	public void update(ArrayList<Integer> pressedKeys,DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
		super.update(pressedKeys,gc,cc,cam);

//		this.playing = false;
//		this.dispatchEvent(new Event(GameEventManager.START_LEVEL_FOUR,this));


		if(this.door1 != null) this.door1.update(pressedKeys);
		if(this.door2 != null) this.door2.update(pressedKeys);


        double distance  = this.stone1.getDistance(this.player);
		if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone1.isPickUp()){
			stone1.setPickUp(false);
			player.addChild(this.stone1);
			gc.removeChild(this.stone1);
			player.setHasRuby(true);
			this.stone1.setUp(-10000,-10000,0.1,0.1);
			if(tile1.isActive()) {
				tile1.setActive(false);
				door1.close();
				cc.addChild(door1);
			}
		}
		

		distance  = this.stone2.getDistance(this.player);
		if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone2.isPickUp()){
			stone2.setPickUp(false);
			player.addChild(this.stone2);
			gc.removeChild(this.stone2);
			player.setHasEmerald(true);
			this.stone2.setUp(-10000,-10000,0.1,0.1);
			if(tile2.isActive()) {
				tile2.setActive(false);
				door2.close();
				cc.addChild(door2);
			}
		}
        
		double distance2 = this.tile1.getDistance(this.player);
		if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone1)&&(stone1.isPickUp())){
			stone1.setPickUp(false);
			this.player.removeChild(this.stone1);
			player.setHasRuby(false);
			this.stone1.setUp(this.tile1.getPositionX(),this.tile1.getPositionY(),0.1,0.1);
			tile1.setActive(true);
			if(!this.door1.isOpen()){
				this.door1.open();
			}
			cc.removeChild(this.door1);
		}
		
		if(!pressedKeys.contains(KeyEvent.VK_E)) {
			stone1.setPickUp(true);
		}
		
		
		distance2 = this.tile2.getDistance(this.player);
		if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone2)&&(stone2.isPickUp())){
			stone2.setPickUp(false);
			this.player.removeChild(this.stone2);
			player.setHasEmerald(false);
			this.stone2.setUp(this.tile2.getPositionX(),this.tile2.getPositionY(),0.1,0.1);
			tile2.setActive(true);
			if(!this.door2.isOpen()){
				this.door2.open();
			}
			cc.removeChild(this.door2);
		}
		
		if(!pressedKeys.contains(KeyEvent.VK_E)) {
			stone2.setPickUp(true);
		}


		TweenJuggler.getJuggler();
		if(player.collideWith(portalEntrance) && !TweenJuggler.getTweenList().contains(playerTween)) {
			player.setVisible(false);
			if(player.collideWith(portal1)) {
				playerTween.animate(TweenableParam.X_POS, player.getPositionX(), 
						portal2.getPositionX(), 1000);
				playerTween.animate(TweenableParam.Y_POS, player.getPositionY(), 
						portal2.getPositionY(), 1000);
				TweenJuggler.getJuggler().add(playerTween);
			}else if(player.collideWith(portal3)) {
				playerTween.animate(TweenableParam.X_POS, player.getPositionX(), 
						portal4.getPositionX(), 1000);
				playerTween.animate(TweenableParam.Y_POS, player.getPositionY(), 
						portal4.getPositionY(), 1000);
				TweenJuggler.getJuggler().add(playerTween);
			}
		}
		
		if(player.collideWith(portal2) ||player.collideWith(portal4) ) {
			player.setVisible(true);
		}
		
		//after all the updates, update the camera to the player's position
		cam.setCamX(this.player.getPositionX());
		cam.setCamY(this.player.getPositionY());
		
		TweenJuggler.getJuggler().update();
        if(player.collideWith(exit)) {
            this.playing = false;
            this.dispatchEvent(new Event(GameEventManager.START_LEVEL_FIVE,this));
        }
	}


	public void drawMessage(Graphics g, GameClock time ){
		Graphics2D g2d = (Graphics2D)g;

		g2d.drawString(stone1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
		g2d.drawString(stone2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

		g2d.drawString(tile1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
		g2d.drawString(tile2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
	}

}

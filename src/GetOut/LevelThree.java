package GetOut;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.tween.Tween;
import edu.virginia.engine.util.Camera;

import java.awt.*;
import java.awt.event.KeyEvent;
import edu.virginia.engine.tween.TweenJuggler;
import edu.virginia.engine.tween.TweenableParam;
import edu.virginia.engine.util.GameClock;

import java.util.ArrayList;

public class LevelThree extends Level {

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

    Stone stone1 = new Stone("stone1");
    Stone stone2 = new Stone("stone2");
    Stone stone3 = new Stone("stone3");
    Tile tile1 = new Tile("tile1");
    Tile tile2 = new Tile("tile2");
    Tile tile3 = new Tile("tile3");
    Door door1 = new Door("door1");
    Door door2 = new Door("door2");
    Door door3 = new Door("door3");

    DisplayObjectContainer portalEntrance = new DisplayObjectContainer("portal entrance");
    Portal portal1 = new Portal("portal1", "Blue_Portal.png");
    Portal portal2 = new Portal("portal2", "Yellow_portal.png");
    Portal portal3 = new Portal("portal3", "Blue_Portal.png");
    Portal portal4 = new Portal("portal4", "Yellow_portal.png");
    Portal portal5 = new Portal("portal5", "Blue_Portal.png");
    Portal portal6 = new Portal("portal6", "Yellow_portal.png");

    Tween playerTween = new Tween(player);

    boolean playing = false;

    public LevelThree(String id) {
        super(id);
    }

    public void load(DisplayObjectContainer gc, DisplayObjectContainer cc) {
        playing = true;

        player.setPositionX(150);
        player.setPositionY(200);
        player.setScaleX(0.5);
        player.setScaleY(0.5);
        player.setPivotPointToCenter();

        stone1.setUp(150, 150, 0.25, 0.25);
        stone2.setUp(436,396,0.25,0.25);
        stone3.setUp(200,-180,0.25,0.25);
        stone1.setType(Type.EMERALD);
        stone2.setType(Type.SAPPHIRE);
        stone3.setType(Type.RUBY);
        stone1.setPivotPointToCenter();
        stone2.setPivotPointToCenter();
        stone3.setPivotPointToCenter();
        tile1.setUp(512,600, 1, 1);
        tile2.setUp(300, -150, 1, 1);
        tile3.setUp(36,250,1,1);
        tile1.setType(Type.EMERALD);
        tile2.setType(Type.SAPPHIRE);
        tile3.setType(Type.RUBY);
        tile1.setPivotPointToCenter();
        tile2.setPivotPointToCenter();
        tile3.setPivotPointToCenter();

        portal1.setUp(200, 220, 0.05, 0.05);
        portal2.setUp(400, 600, 0.1,0.1);

        portal3.setUp(300, 500, 0.05, 0.05);
        portal4.setUp(400, 0, 0.1,0.1);

        portal5.setUp(400,-150,0.05,0.05);
        portal6.setUp(220,100,0.1,0.1);


        portalEntrance.addChild(portal1);
        portalEntrance.addChild(portal3);
        portalEntrance.addChild(portal4);

        door1.setUp(512, 480, 1, 1);
        door2.setUp(256, -223, 1, 1);
        door3.setUp(0,288,1,1);
        door1.setType(Type.EMERALD);
        door2.setType(Type.SAPPHIRE);
        door3.setType(Type.RUBY);
        door1.setPivotPointToCenter();
        door2.setPivotPointToCenter();
        door3.setPivotPointToCenter();

        exit.setPositionX(-288);
        exit.setPositionY(288);
        exit.setPivotPointToCenter();

        w1.setUp(320, 320, -11, -11,"wall_5.png");
        w2.setUp(0, 0, 10, 9,"wall_5.png");
        w3.setUp(160,352, 12, 12,"wall_5.png");
        w4.setUp(544,736,-13,-13,"wall_5.png");
        w5.setUp(128,0,0,-8,"wall_5.png");
        w6.setUp(128,-255,13,0,"wall_5.png");
        w7.setUp(512,-255,0,12,"wall_5.png");
        w8.setUp(512,129,-6,0, "wall_5.png");
        w9.setUp(0,256,-10,0,"wall_5.png");
        w10.setUp(0,320,-10,0,"wall_5.png");
        w11.setUp(384,352,0,4,"wall_5.png");
        w12.setUp(384,480,4,0,"wall_5.png");
        w13.setUp(128,-63,5,0,"wall_5.png");
        w14.setUp(256,-63,0,-5,"wall_5.png");

        gc.addChild(door1);
        gc.addChild(door2);
        gc.addChild(door3);
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
        gc.addChild(stone1);
        gc.addChild(stone2);
        gc.addChild(stone3);
        gc.addChild(tile1);
        gc.addChild(tile2);
        gc.addChild(tile3);
        gc.addChild(portal1);
        gc.addChild(portal2);
        gc.addChild(portal3);
        gc.addChild(portal4);
        gc.addChild(portal5);
        gc.addChild(portal6);
        gc.addChild(exit);
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
        cc.addChild(door1);
        cc.addChild(door2);
        cc.addChild(door3);
    }

    public void update(ArrayList<Integer> pressedKeys, DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
        super.update(pressedKeys,gc,cc,cam);

        if(this.door1 != null) this.door1.update(pressedKeys);

        if(this.door2 != null) this.door1.update(pressedKeys);

        if(this.door3 != null) this.door1.update(pressedKeys);

        double distance2 = this.tile1.getDistance(this.player);
        if(distance2 <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && player.contains(stone1) && (stone1.isPickUp())){
            stone1.setPickUp(false);
            this.player.removeChild(this.stone1);
//            gc.addChild(this.stone);
            player.setHasEmerald(false);
            this.stone1.setUp(this.tile1.getPositionX(),this.tile1.getPositionY(),0.1,0.1);
            tile1.setActive(true);
            if(!this.door1.isOpen()){
                this.door1.open();
            }
            cc.removeChild(this.door1);
        }

        double distance  = this.stone1.getDistance(this.player);

        if(distance <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && (stone1.isPickUp())){
            stone1.setPickUp(false);
            this.removeChild(this.stone1);
            this.player.addChild(this.stone1);
            gc.removeChild(this.stone1);
            player.setHasEmerald(true);
            this.stone1.setUp(-10000,-10000,0.1,0.1);
            if(tile1.isActive()){
                tile1.setActive(false);
                door1.close();
                cc.addChild(door1);
            }
        }

        if (!pressedKeys.contains(KeyEvent.VK_E)){
            stone1.setPickUp(true);
            stone2.setPickUp(true);
            stone3.setPickUp(true);
        }

        double distance3 = this.tile2.getDistance(this.player);
        if(distance3 <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && player.contains(stone2) && (stone2.isPickUp())){
            stone2.setPickUp(false);
            this.player.removeChild(this.stone2);
//            gc.addChild(this.stone);
            player.setHasSapphire(false);
            this.stone2.setUp(this.tile2.getPositionX(),this.tile2.getPositionY(),0.1,0.1);
            tile2.setActive(true);
            if(!this.door2.isOpen()){
                this.door2.open();
            }
            cc.removeChild(this.door2);
        }

        double distance4  = this.stone2.getDistance(this.player);

        if(distance4 <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && (stone2.isPickUp())){
            stone2.setPickUp(false);
            this.removeChild(this.stone2);
            this.player.addChild(this.stone2);
            gc.removeChild(this.stone2);
            player.setHasSapphire(true);
            this.stone2.setUp(-10000,-10000,0.1,0.1);
            if(tile2.isActive()){
                tile2.setActive(false);
                door2.close();
                cc.addChild(door2);
            }
        }

        double distance6 = this.tile3.getDistance(this.player);
        if(distance6 <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && player.contains(stone3) && (stone3.isPickUp())){
            stone3.setPickUp(false);
            this.player.removeChild(this.stone3);
//            gc.addChild(this.stone);
            player.setHasRuby(false);
            this.stone3.setUp(this.tile3.getPositionX(),this.tile3.getPositionY(),0.1,0.1);
            tile3.setActive(true);
            if(!this.door3.isOpen()){
                this.door3.open();
            }
            cc.removeChild(this.door3);
        }

        double distance7  = this.stone3.getDistance(this.player);

        if(distance7 <= 1000 && pressedKeys.contains(KeyEvent.VK_E) && (stone3.isPickUp())){
            stone3.setPickUp(false);
            this.removeChild(this.stone3);
            this.player.addChild(this.stone3);
            gc.removeChild(this.stone3);
            player.setHasRuby(true);
            this.stone3.setUp(-10000,-10000,0.1,0.1);
            if(tile3.isActive()){
                tile3.setActive(false);
                door3.close();
                cc.addChild(door3);
            }
        }

        TweenJuggler.getJuggler();
        if(player.collideWith(portal1) && !TweenJuggler.getTweenList().contains(playerTween)) {
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

        if(player.collideWith(portal3) && !TweenJuggler.getTweenList().contains(playerTween)) {
            player.setVisible(false);
            playerTween.animate(TweenableParam.X_POS, player.getPositionX(),
                    portal4.getPositionX(), 1000);
            playerTween.animate(TweenableParam.Y_POS, player.getPositionY(),
                    portal4.getPositionY(), 1000);
            TweenJuggler.getJuggler().add(playerTween);
        }

        if(player.collideWith(portal4)) {
            player.setVisible(true);
        }

        if(player.collideWith(portal5) && !TweenJuggler.getTweenList().contains(playerTween)) {
            player.setVisible(false);
            playerTween.animate(TweenableParam.X_POS, player.getPositionX(),
                    portal6.getPositionX(), 1000);
            playerTween.animate(TweenableParam.Y_POS, player.getPositionY(),
                    portal6.getPositionY(), 1000);
            TweenJuggler.getJuggler().add(playerTween);
        }

        if(player.collideWith(portal6)) {
            player.setVisible(true);
        }

        //after all the updates, update the camera to the player's position
        cam.setCamX(this.player.getPositionX());
        cam.setCamY(this.player.getPositionY());

        TweenJuggler.getJuggler().update();

		if(player.collideWith(exit)) {
			this.playing = false;
			this.dispatchEvent(new Event(GameEventManager.START_LEVEL_FOUR,this));
		}
//        if(player.collideWith(exit)) {
//            this.playing = false;
//            this.dispatchEvent(new Event(GameEventManager.START_LEVEL_TWO,this));
//        }

    }

    public void drawMessage(Graphics g, GameClock time ){
        Graphics2D g2d = (Graphics2D)g;

        
        g2d.drawString(stone1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(stone2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(stone3.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile3.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);


    }


}

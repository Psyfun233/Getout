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

public class LevelFive extends Level{

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

    Stone stone1 = new Stone("stone1", "sapphire.png");
    Stone stone2 = new Stone("stone2", "emerald.png");
    Stone stone3 = new Stone("stone3", "ruby.png");
    Tile tile1 = new Tile("tile1", "blue_tile.png");
    Tile tile2 = new Tile("tile2", "green_tile.png");
    Tile tile3 = new Tile("tile3", "red_tile.png");
    Tile tile4 = new Tile("tile4", "red_tile.png");
    Tile tile5 = new Tile("tile5", "green_tile.png");
    Tile tile6 = new Tile("tile6", "blue_tile.png");


    Door door1 = new Door("door1", "bluedoor_closed.png");
    Door door2 = new Door("door2", "greendoor_closed.png");
    Door door3 = new Door("door3", "reddoor_closed.png");
    Door door4 = new Door("door4", "reddoor_closed.png");
    Door door5 = new Door("door5", "greendoor_closed.png");
    Door door6 = new Door("door6", "bluedoor_closed.png");


    DisplayObjectContainer portalEntrance = new DisplayObjectContainer("portal entrance");
    Portal portal1 = new Portal("portal1", "Blue_Portal.png");
    Portal portal2 = new Portal("portal2", "Yellow_portal.png");
    Portal portal3 = new Portal("portal3", "Blue_Portal.png");
    Portal portal4 = new Portal("portal4", "Yellow_portal.png");


    Tween playerTween = new Tween(player);

    boolean playing = false;

    public LevelFive(String id) {
        super(id);
    }



    public void load(DisplayObjectContainer gc, DisplayObjectContainer cc) {
        playing = true;

        player.setUp(480, 160, 0.5, 0.5);
        stone1.setUp(480,160, 0.25, 0.25);
        stone2.setUp(160,160, 0.25, 0.25);
        stone3.setUp(480,480, 0.25, 0.25);

        tile1.setUp(352,160, 1, 1);
        tile2.setUp(608,160, 1, 1);
        tile3.setUp(480,352, 1, 1);
        tile4.setUp(480,32, 1, 1);
        tile5.setUp(480,-448, 1, 1);
        tile6.setUp(480,-928, 1, 1);

        door1.setUp(320, 160, 1, 1);
        door2.setUp(640, 160, 1, 1);
        door3.setUp(480, 320, 1, 1);
        door4.setUp(480, 0, 1, 1);
        door5.setUp(480, -480, 1, 1);
        door6.setUp(480, -960, 1, 1);


        portalEntrance.addChild(portal1);
        portalEntrance.addChild(portal3);
        portal1.setUp(928, 32, 0.05, 0.05);
        portal2.setUp(352, 608, 0.1,0.1);
        portal3.setUp(608, 608, 0.05, 0.05);
        portal4.setUp(32, 32, 0.1, 0.1);


        exit.setPositionX(480);
        exit.setPositionY(-1440);
        exit.setPivotPointToCenter();

        w1.setUp(0, 0, 15, 10,"wall_5.png");
        w7.setUp(960,0,-15,10,"wall_5.png");
        w2.setUp(0, 320, 15, 0,"wall_5.png");
        w8.setUp(960, 320, -15, 0,"wall_5.png");
        w3.setUp(320, 0, 0, 5,"wall_5.png");
        w4.setUp(320, 160, 0, 5, "wall_5.png");
        w5.setUp(640, 0, 0, 5, "wall_5.png");
        w6.setUp(640, 160, 0, 5, "wall_5.png");
        w9.setUp(320, 640, 11, -10, "wall_5.png");
        w10.setUp(640, 320, 0, 10, "wall_5.png");
        w11.setUp(448, 0, 0, -45, "wall_5.png");
        w12.setUp(512, 0, 0, -45, "wall_5.png");
        w13.setUp(448, -1440, 1, 0, "wall_5.png");
        w14.setUp(512, -1440, -1, 0, "wall_5.png");


        gc.addChild(exit);
        gc.addChild(door1);
        gc.addChild(door2);
        gc.addChild(door3);
        gc.addChild(door4);
        gc.addChild(door5);
        gc.addChild(door6);
        gc.addChild(stone1);
        gc.addChild(stone2);
        gc.addChild(stone3);
        gc.addChild(tile1);
        gc.addChild(tile2);
        gc.addChild(tile3);
        gc.addChild(tile4);
        gc.addChild(tile5);
        gc.addChild(tile6);
        gc.addChild(portal1);
        gc.addChild(portal2);
        gc.addChild(portal3);
        gc.addChild(portal4);
        gc.addChild(player);
        gc.addChild(door1);
        gc.addChild(door2);
        gc.addChild(door3);
        gc.addChild(door4);
        gc.addChild(door5);
        gc.addChild(door6);
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



        cc.addChild(door1);
        cc.addChild(door2);
        cc.addChild(door3);
        cc.addChild(door4);
        cc.addChild(door5);
        cc.addChild(door6);
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

    }


    public void update(ArrayList<Integer> pressedKeys,DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
        super.update(pressedKeys,gc,cc,cam);
        
//        this.playing = false;
//        this.dispatchEvent(new Event(GameEventManager.START_LEVEL_FIVE,this));

        if(this.door1 != null) this.door1.update(pressedKeys);
        if(this.door2 != null) this.door2.update(pressedKeys);
        if(this.door3 != null) this.door3.update(pressedKeys);
        if(this.door4 != null) this.door4.update(pressedKeys);
        if(this.door5 != null) this.door5.update(pressedKeys);
        if(this.door6 != null) this.door6.update(pressedKeys);

        double distance  = this.stone1.getDistance(this.player);
        if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone1.isPickUp()){
            stone1.setPickUp(false);
            player.addChild(this.stone1);
            gc.removeChild(this.stone1);
            player.setHasSapphire(true);
            this.stone1.setUp(-10000,-10000,0.1,0.1);
            if(tile1.isActive()) {
                tile1.setActive(false);
                door1.close();
                cc.addChild(door1);
            }
            else if(tile6.isActive()) {
                tile6.setActive(false);
                door6.close();
                cc.addChild(door6);
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
            else if(tile5.isActive()) {
                tile5.setActive(false);
                door5.close();
                cc.addChild(door5);
            }
        }

        distance  = this.stone3.getDistance(this.player);
        if(distance <= 500 && pressedKeys.contains(KeyEvent.VK_E) && stone3.isPickUp()){
            stone3.setPickUp(false);
            player.addChild(this.stone3);
            gc.removeChild(this.stone3);
            player.setHasRuby(true);
            this.stone3.setUp(-10000,-10000,0.1,0.1);
            if(tile3.isActive()) {
                tile3.setActive(false);
                door3.close();
                cc.addChild(door3);
            }
            else if(tile4.isActive()) {
                tile4.setActive(false);
                door4.close();
                cc.addChild(door4);
            }
        }

        double distance2 = this.tile1.getDistance(this.player);
        if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone1)&&(stone1.isPickUp())){
            stone1.setPickUp(false);
            this.player.removeChild(this.stone1);

            this.stone1.setUp(this.tile1.getPositionX(),this.tile1.getPositionY(),0.1,0.1);
            tile1.setActive(true);
            if(!this.door1.isOpen()){
                this.door1.open();
            }
            cc.removeChild(this.door1);
        }

        distance2 = this.tile6.getDistance(this.player);
        if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone1)&&(stone1.isPickUp())){
            stone1.setPickUp(false);
            this.player.removeChild(this.stone1);
            player.setHasSapphire(false);
            this.stone1.setUp(this.tile6.getPositionX(),this.tile6.getPositionY(),0.1,0.1);
            tile6.setActive(true);
            if(!this.door6.isOpen()){
                this.door6.open();
            }
            cc.removeChild(this.door6);
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

        distance2 = this.tile5.getDistance(this.player);
        if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone2)&&(stone2.isPickUp())){
            stone2.setPickUp(false);
            this.player.removeChild(this.stone2);
            player.setHasEmerald(false);
            this.stone2.setUp(this.tile5.getPositionX(),this.tile5.getPositionY(),0.1,0.1);
            tile5.setActive(true);
            if(!this.door5.isOpen()){
                this.door5.open();
            }
            cc.removeChild(this.door5);
        }

        distance2 = this.tile3.getDistance(this.player);
        if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone3)&&(stone3.isPickUp())){
            stone3.setPickUp(false);
            this.player.removeChild(this.stone3);
            player.setHasRuby(false);
            this.stone3.setUp(this.tile3.getPositionX(),this.tile3.getPositionY(),0.1,0.1);
            tile3.setActive(true);
            if(!this.door3.isOpen()){
                this.door3.open();
            }
            cc.removeChild(this.door3);
        }

        distance2 = this.tile4.getDistance(this.player);
        if(distance2 <= 500 && pressedKeys.contains(KeyEvent.VK_E)&& player.contains(stone3)&&(stone3.isPickUp())){
            stone3.setPickUp(false);
            this.player.removeChild(this.stone3);
            player.setHasRuby(false);
            this.stone3.setUp(this.tile4.getPositionX(),this.tile4.getPositionY(),0.1,0.1);
            tile4.setActive(true);
            if(!this.door4.isOpen()){
                this.door4.open();
            }
            cc.removeChild(this.door4);
        }

        if(!pressedKeys.contains(KeyEvent.VK_E)) {
            stone1.setPickUp(true);
            stone2.setPickUp(true);
            stone3.setPickUp(true);
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
        //after all the updates, update the camera to the player's position
        cam.setCamX(this.player.getPositionX());
        cam.setCamY(this.player.getPositionY());

        TweenJuggler.getJuggler().update();


    }

    public void drawMessage(Graphics g, GameClock time ){
        Graphics2D g2d = (Graphics2D)g;

        
        g2d.drawString(stone1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile1.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(stone2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile2.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(stone3.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);

        g2d.drawString(tile3.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);


        g2d.drawString(tile4.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(tile5.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
        g2d.drawString(tile6.GameMessage(player), player.getPositionX()-100, player.getPositionY()+150);
    }


}

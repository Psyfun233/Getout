package GetOut;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.PhysicsSprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.util.Camera;
import edu.virginia.engine.util.GameClock;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Level extends DisplayObjectContainer {

    Player player = new Player ("player","player_walk_1.png",16,"player_walk");
    PhysicsSprite exit = new PhysicsSprite ("exit","exit.png");

    boolean playing = false;
    
    public Level(String id){
        super(id);
    }

	public void load(DisplayObjectContainer gc, DisplayObjectContainer cc){
        playing = true;
        player.setUp(100,100,0.5,0.5);
        exit.setPositionX(580);
        exit.setPositionY(420);
        exit.setPivotPointToCenter();

    }

    public void update(ArrayList<Integer> pressedKeys, DisplayObjectContainer gc, DisplayObjectContainer cc, Camera cam) {
        super.update(pressedKeys);

        if(this.player != null) this.player.update(pressedKeys);
        if(this.exit != null) this.exit.update(pressedKeys);

        // store old player position to restore in case of collision
        int oldX = this.player.getPositionX();
        int oldY = this.player.getPositionY();

        if(pressedKeys.contains(KeyEvent.VK_D))	{
            this.player.setPositionX(this.player.getPositionX()+4);
            this.player.setPlaying(true);
            this.player.setStartIndex(8);
            this.player.setEndIndex(11);

            if(this.player.collideWith(cc) && player.getVisible()){
                //System.out.println("Colliding");
                this.player.setPositionX(oldX);
            }
        }

        if(pressedKeys.contains(KeyEvent.VK_A)){
            this.player.setPositionX(this.player.getPositionX()-4);
            this.player.setPlaying(true);
            this.player.setStartIndex(4);
            this.player.setEndIndex(7);

            if(this.player.collideWith(cc) && player.getVisible()){
                this.player.setPositionX(oldX);
            }
        }

        if(pressedKeys.contains(KeyEvent.VK_S))	{
            this.player.setPositionY(this.player.getPositionY()+4);
            this.player.setPlaying(true);
            this.player.setStartIndex(0);
            this.player.setEndIndex(3);

            if(this.player.collideWith(cc) && player.getVisible()){
                this.player.setPositionY(oldY);
            }
        }

        if(pressedKeys.contains(KeyEvent.VK_W)){
            this.player.setPositionY(this.player.getPositionY()-4);
            this.player.setPlaying(true);
            this.player.setStartIndex(12);
            this.player.setEndIndex(15);

            if(this.player.collideWith(cc) && player.getVisible()){
                this.player.setPositionY(oldY);
            }
        }

        if(!pressedKeys.contains(KeyEvent.VK_W) && !pressedKeys.contains(KeyEvent.VK_S) 
        		&& !pressedKeys.contains(KeyEvent.VK_A) && !pressedKeys.contains(KeyEvent.VK_D)) {
            this.player.setPlaying(false);
        }

    }

    public void updateAlpha(DisplayObjectContainer c) {
        for(DisplayObjectContainer o: c.getChildren()) {
            if(o.getChildren().size() > 0) {
                updateAlpha(o);
            }
            int distance = (int)(Math.pow(this.player.getPositionX()-o.getPositionX(), 2) +
                    Math.pow(this.player.getPositionY()-o.getPositionY(), 2));
            if(distance >=  10000) {
                o.setAlpha(0);
            }else {
                o.setAlpha((float)(1-distance*0.00009));
            }
        }
    }
}

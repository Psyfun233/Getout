package GetOut;

import edu.virginia.engine.display.Sprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile extends Sprite {

    private Type type;
    private boolean active = false;

    public Tile(String id){
        super(id);
    }

    public Tile(String id, String imageFileName){
        super(id, imageFileName);
        if(imageFileName ==  "red_tile.png"){
            this.type = Type.RUBY;
        }
        if(imageFileName == "blue_tile.png"){
            this.type = Type.SAPPHIRE;
        }
        if(imageFileName == "green_tile.png"){
            this.type = Type.EMERALD;
        }
    }

    public void setType(Type type){
        if(type == Type.RUBY){
            this.setImage("red_tile.png");
            this.type = Type.RUBY;
        }
        if(type == Type.SAPPHIRE){
            this.setImage("blue_tile.png");
            this.type = Type.SAPPHIRE;
        }
        if(type == Type.EMERALD){
            this.setImage("green_tile.png");
            this.type = Type.EMERALD;
        }
    }

    public Type getType(){
        return this.type;
    }

    public void setUp(int posX, int posY, double scaleX, double scaleY) {
        this.setPositionX(posX);
        this.setPositionY(posY);
        this.setScaleX(scaleX);
        this.setScaleY(scaleY);
        this.setPivotPointToCenter();
//        this.getClock().resetGameClock();
    }

    public double getDistance(Sprite player){
        double distance = Math.pow(player.getPositionX()-this.getPositionX(), 2) + Math.pow(player.getPositionY()-this.getPositionY(), 2);
        return distance;

    }

    public void setActive(boolean active) {
        this.active = active;
        if(active){
            if(type == Type.EMERALD){
                BufferedImage image = this.readImage("green_active.png");
                this.setImage(image);
            }
            if(this.type == Type.SAPPHIRE){
                BufferedImage image = this.readImage("blue_active.png");
                this.setImage(image);
            }
            if(type == Type.RUBY){
                BufferedImage image = this.readImage("red_active.png");
                this.setImage(image);
            }

        }
        else{
            if(type == Type.EMERALD){
                BufferedImage image = this.readImage("green_tile.png");
                this.setImage(image);
            }
            if(type == Type.SAPPHIRE){
                BufferedImage image = this.readImage("blue_tile.png");
                this.setImage(image);
            }
            if(type == Type.RUBY){
                BufferedImage image = this.readImage("red_tile.png");
                this.setImage(image);
            }
        }
    }

    public boolean isActive(){
        return this.active;
    }

    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);
    }

    public String GameMessage(Player player){
        if(this.getDistance(player) < 500 && !this.active){
            if(this.type == Type.RUBY && player.isHasRuby()){
                return "Press E to activate";
            }

            if(this.type == Type.SAPPHIRE && player.isHasSapphire()){
                return "Press E to activate";
            }

            if(this.type == Type.EMERALD && player.isHasEmerald()){
                return "Press E to activate";
            }

            else{
                return "You don't have " + this.type + " to activate";
            }
        }

    return "";
    }

}

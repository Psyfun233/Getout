package GetOut;

import edu.virginia.engine.display.DisplayObjectContainer;
import edu.virginia.engine.display.PhysicsSprite;

import java.util.ArrayList;

public class Door extends PhysicsSprite{

    private boolean open = false;
    private Type type;


    public Door(String id){
        super(id);
    }
    public Door(String id, String fileName){

        super(id,fileName);

        if(fileName == "bluedoor_closed.png"){
            this.type = Type.SAPPHIRE;
        }

        if(fileName == "greendoor_closed.png"){
            this.type = Type.EMERALD;
        }

        if(fileName == "reddoor_closed.png"){
            this.type = Type.RUBY;

        }
    }

    public void setType(Type type){
        if(type == Type.RUBY){
            this.setImage("reddoor_closed.png");
            this.type = Type.RUBY;
        }
        if(type == Type.SAPPHIRE){
            this.setImage("bluedoor_closed.png");
            this.type =Type.SAPPHIRE;
        }
        if(type == Type.EMERALD){
            this.setImage("greendoor_closed.png");
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
        this.setCurrentFrame(1);
    }

    @Override
    public void update(ArrayList<Integer> pressedKeys){
        super.update(pressedKeys);
        if(this.isPlaying()) {
            if(this.getAnimationSpeed() < this.getStartIndex() || this.getCurrentFrame() > this.getEndIndex()) {
                this.setCurrentFrame(this.getStartIndex());
            }
            if(this.getCurrentFrame() < this.getEndIndex()) {
                this.setCurrentFrame(this.getCurrentFrame()+1);
            }else {
                this.setPlaying(false);
            }
            super.setImage(this.getFrames()[this.getCurrentFrame()]);
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void open(){
        if(!this.open){
            this.open = true;
            if(type == Type.RUBY){
                this.setImage("reddoor_open.png");
            }
            if(type == Type.SAPPHIRE){
                this.setImage("bluedoor_open.png");
            }
            if(type == Type.EMERALD){
                this.setImage("greendoor_open.png");
            }
        }


    }

    public void close(){
        if(this.open){
            if(type == Type.RUBY){
                this.setImage("reddoor_closed.png");
            }
            if(type == Type.SAPPHIRE){
                this.setImage("bluedoor_closed.png");
            }
            if(type == Type.EMERALD){
                this.setImage("greendoor_closed.png");
            }

        }
        this.open = false;

    }

    @Override
    public boolean collideWith(DisplayObjectContainer ob) {
        if(this.open){
            return false;
        }else {
            return super.collideWith(ob);
        }
    }




}
package GetOut;

import edu.virginia.engine.display.Sprite;

public class Stone extends Sprite {


    public boolean pickUp = true;
    private Type type;
    private boolean visible = true;

    @Override
    public boolean getVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Stone(String id, String imageFileName){
        super(id, imageFileName);

        if(imageFileName == "sapphire.png"){
            this.type = Type.SAPPHIRE;
        }
        if(imageFileName == "ruby.png"){
            this.type = Type.RUBY;
        }
        if(imageFileName == "emerald.png"){
            this.type = Type.EMERALD;
        }

    }

    public Stone(String id){
        super(id);
    }

    public void setType(Type type){
        if(type == Type.RUBY){
            this.setImage("ruby.png");
            this.type = Type.RUBY;
        }
        if(type == Type.SAPPHIRE){
            this.setImage("sapphire.png");
            this.type = Type.SAPPHIRE;
        }
        if(type == Type.EMERALD){
            this.setImage("emerald.png");
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
    }

    public double getDistance(Sprite player){
        double distance = Math.pow(player.getPositionX()-this.getPositionX(), 2) + Math.pow(player.getPositionY()-this.getPositionY(), 2);
        return distance;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public String GameMessage(Sprite player){
        if(this.getDistance(player) < 500){
            return "Press E to pick up " + this.type;
        }
        return "";

    }


}
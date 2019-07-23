package GetOut;

import edu.virginia.engine.display.Sprite;

public class Portal extends Sprite{

	int type; 	// type = 1 is entrance; type = 2 is exit
	Portal anotherPortal;
	
    public Portal(String id) {
        super(id);
    }

    public Portal(String id, String imageFileName) {
        super(id, imageFileName);
    }
    
    public Portal getAnotherPortal() {
    	return this.anotherPortal;
    }
    
    public void setAnotherPortal(Portal p) {
    	this.anotherPortal = p;
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
}

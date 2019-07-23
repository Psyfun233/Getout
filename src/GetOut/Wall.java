package GetOut;

import edu.virginia.engine.display.PhysicsSprite;

public class Wall extends PhysicsSprite{

	public Wall(String id) {
		super(id);
	}

	public Wall(String id, String fileNames) {
		super(id,fileNames);
	}
	
	public void setUp(int PosX, int PosY, int lengthX, int lengthY, String fileNames) {
		this.setPositionX(PosX);
		this.setPositionY(PosY);
		this.setPivotPointX(PosX);
		this.setPivotPointY(PosY);
		
		if(lengthX >= 0) {
			for(int i = 0; i < lengthX; i++) {
				Wall c = new Wall((this.getId()+"_X_"+i),fileNames);
				c.setPivotPointToCenter();
				c.setPositionX(PosX + i*32);
				c.setPositionY(PosY);
				this.addChild(c);
			}
		}else {
			for(int i = 0; i > lengthX; i--) {
				Wall c = new Wall((this.getId()+"_X_"+i),fileNames);
				c.setPivotPointToCenter();
				c.setPositionX(PosX + i*32);
				c.setPositionY(PosY);
				this.addChild(c);
			}
		}
		if(lengthY >= 0) {
			for(int i = 1; i < lengthY; i++) {
				Wall c = new Wall((this.getId()+"_Y_"+i),fileNames);
				c.setPivotPointToCenter();
				c.setPositionX(PosX);
				c.setPositionY(PosY+ i*32);
				this.addChild(c);
			}
		}else {
			for(int i = -1; i > lengthY; i--) {
				Wall c = new Wall((this.getId()+"_Y_"+i),fileNames);
				c.setPivotPointToCenter();
				c.setPositionX(PosX);
				c.setPositionY(PosY+ i*32);
				this.addChild(c);
			}
		}
	}
	
	
	
}

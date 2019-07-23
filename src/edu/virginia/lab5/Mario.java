package edu.virginia.lab5;

import edu.virginia.engine.display.PhysicsSprite;

public class Mario extends PhysicsSprite{
	
	int jumpTimer = 0;
	boolean inAir = false;
	int oldX;
	int oldY;
	
	
	
	public Mario (String id, String fileName) {
		super(id,fileName);
	}
	
	public Mario(String id, String fileName, int numFrames,String path) {
		super(id,fileName,numFrames,path);
	}
	
	public void setUp(int posX, int posY, double scaleX, double scaleY) {
		this.setPositionX(posX);
		this.setPositionY(posY);
		this.setScaleX(scaleX);
		this.setScaleY(scaleY);
		this.setPivotPointToCenter();
		this.getClock().resetGameClock();
	}
}

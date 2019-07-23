package edu.virginia.engine.util;

public class Camera {
	private int camX;
	private int camY;
	
	public Camera(int camX, int camY) {
		this.camX = camX;
		this.camY = camY;
	}
	
	public int getCamX() {
		return camX;
	}
	public void setCamX(int camX) {
		this.camX = camX;
	}
	public int getCamY() {
		return camY;
	}
	public void setCamY(int camY) {
		this.camY = camY;
	}
	
	
}

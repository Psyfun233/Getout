package edu.virginia.engine.display;

import java.util.ArrayList;

import edu.virginia.engine.util.GameClock;

public class PhysicsSprite extends AnimatedSprite{

	private double vx = 0;
	private double vy = 0;
	private double ax = 0;
	private double ay = 0;
	private double g = 0.001;
	private double dt = 0;
	private GameClock clock = new GameClock();
	
	/*
	 * ********************* Constructors *********************************
	 */
	
	public PhysicsSprite(String id) {
		super(id);
	}
	
	public PhysicsSprite(String id, String fileName, int numFrames,String path) {
		super(id, fileName, numFrames,path);
	}

	public PhysicsSprite(String id, String fileNames) {
		super(id, fileNames);
	}
	
	/*
	 * ********************* Getters and Setters **************************
	 */

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getAx() {
		return ax;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}

	public double getAy() {
		return ay;
	}

	public void setAy(double ay) {
		this.ay = ay;
	}

	public double getGravity() {
		return g;
	}

	public void setGravity(double gravity) {
		this.g = gravity;
	}

	public double getDt() {
		return dt;
	}
	
	public void setDt(double dt) {
		this.dt = dt;
	}

	public GameClock getClock() {
		return clock;
	}
	
	public void calculateX() {
		this.setPositionX((int)(this.getPositionX() + vx*dt + ax*dt*dt/2));
		vx = vx + dt * ax;
	}
	
	public void calculateY() {
		this.setPositionY((int)(this.getPositionY() + vy*dt + (ay+g)*dt*dt/2));
		vy = vy + dt * (ay + g);
	}
	
	
	/*
	 * ****************************Update and Draw Loop ******************************
	 */
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		dt = clock.getElapsedTime();
		clock.resetGameClock();;
	}




	
	
}

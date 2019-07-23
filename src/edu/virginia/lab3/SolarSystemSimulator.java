package edu.virginia.lab3;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import edu.virginia.engine.display.Game;
import edu.virginia.engine.display.Sprite;


public class SolarSystemSimulator extends Game{
	Sprite sun = new Sprite("sun","Sun.png");
	Sprite planet1 = new Sprite("planet1","planet_1.png");
	Sprite planet2 = new Sprite("planet2","planet_2.png");
	Sprite planet3 = new Sprite("planet3","planet_3.png");
	Sprite sc1 = new Sprite("sc1");
	Sprite sc2 = new Sprite("sc2");
	Sprite sc3 = new Sprite("sc3");
	Sprite pc1 = new Sprite("pc1");
	Sprite pc2 = new Sprite ("pc2");
	Sprite moon1 = new Sprite("moon1","moon_1.png");
	Sprite moon2 = new Sprite("moon2","moon_2.png");
		
	public SolarSystemSimulator() {
		super("Solar System Simulator", 1200, 800);
		this.addChild(sun);
		setUpSun();
		setUpPlanets(planet1, sc1, 2000, 0, 1, 1);
		setUpPlanets(planet2, sc2, 3000, 0, 1.5, 1.5);
		setUpPlanets(planet3, sc3, 4000, 0, 1, 1);
		setUpMoons(moon1, planet1, pc1, 400, 0, 1, 1);
		setUpMoons(moon2, planet3, pc2, 300, 0, 1, 1);

	}
	
	public void setUpSun() {
		sun.setPositionX(this.getMainFrame().getWidth()/2-50);
		sun.setPositionY(this.getMainFrame().getHeight()/2-50);
		sun.setScaleX(0.07);
		sun.setScaleY(0.07);
		sun.setPivotPointX((int)(sun.getUnscaledWidth()/2));
		sun.setPivotPointY((int)(sun.getUnscaledHeight()/2));
		sun.setVisible(true);
		sun.setAlpha(1);
		sun.setRotation(0);
	}
	
	public void setUpPlanets(Sprite p, Sprite c, int x, int y, double sx, double sy) {		//p: the planet; 
		sun.addChild(c);
		c.setParent(sun);
		c.addChild(p);
		p.setParent(c);
		p.setPositionX(x);
		p.setPositionY(y);
		p.setScaleX(sx);
		p.setScaleY(sy);
		p.setVisible(true);
		p.setPivotPointX((int)(p.getUnscaledWidth()/2));
		p.setPivotPointY((int)(p.getUnscaledHeight()/2));
		p.setAlpha(1);
		p.setRotation(90);
	}
	
	public void setUpMoons(Sprite m, Sprite p, Sprite c, int x, int y, double sx, double sy) {
		p.addChild(c);
		c.setParent(p);
		c.addChild(m);
		m.setParent(c);
		m.setPositionX(x);
		m.setPositionY(y);
		m.setScaleX(sx);
		m.setScaleY(sy);
		m.setVisible(true);
		m.setPivotPointX((int)(m.getUnscaledWidth()/2));
		m.setPivotPointY((int)(m.getUnscaledHeight()/2));
		m.setAlpha(1);
		m.setRotation(0);
	}
	
	/**
	 * Engine will automatically call this update method once per frame and pass to us
	 * the set of keys (as strings) that are currently being pressed down
	 * */

	@Override
	public void update(ArrayList<Integer> pressedKeys){
		super.update(pressedKeys);
		//sun.setRotation(sun.getRotation()+0.5);
		sc1.setRotation(sc1.getRotation()+1);

		planet1.setPositionX((int)(900+1500*(1+Math.cos(sc1.getRotation()*Math.PI/180)/2)));
		
		sc2.setRotation(sc2.getRotation()+2.5);
		planet2.setPositionX((int)(1200+2500*(1+Math.cos(sc2.getRotation()*Math.PI/180)/2)));
		
		sc3.setRotation(sc3.getRotation()+1.5);
		
		planet3.setPositionX((int)(1600+3500*(1+Math.cos(sc3.getRotation()*Math.PI/180)/2)));
		pc1.setRotation(pc1.getRotation()+5.5);
		moon1.setPositionX((int)(400+350*(1+Math.cos(pc1.getRotation()*Math.PI/180)/2)));
		pc2.setRotation(pc2.getRotation()+4.5);
		moon2.setPositionX((int)(300+350*(1+Math.cos(pc2.getRotation()*Math.PI/180)/2)));
		for(Integer i: pressedKeys) {
			if(i == KeyEvent.VK_UP) {
				sun.setPositionY(sun.getPositionY()-10);
			}
			if(i == KeyEvent.VK_DOWN) {
				sun.setPositionY(sun.getPositionY()+10);
			}
			if(i == KeyEvent.VK_LEFT) {
				sun.setPositionX(sun.getPositionX()-10);
			}
			if(i == KeyEvent.VK_RIGHT) {
				sun.setPositionX(sun.getPositionX()+10);
			}
			if(i == KeyEvent.VK_A) {
				sun.setRotation(sun.getRotation()+1);
			}
			if(i == KeyEvent.VK_S) {
				sun.setRotation(sun.getRotation()-1);
			}
			if(i == KeyEvent.VK_Q) {
				
				sun.setScaleX(sun.getScaleX()+0.02);
				sun.setScaleY(sun.getScaleY()+0.02);
				
			}
			if(i == KeyEvent.VK_W) {
				if(sun.getScaleX()>=0.02) {
					sun.setScaleX(sun.getScaleX()-0.02);
					sun.setScaleY(sun.getScaleY()-0.02);
				}
			}
		}
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
//		g.drawString("Sun's position: " + sun.getPositionX() + ", " + sun.getPositionY(), 200, 200);
//		g.drawString("Sun's Pivot position: " + sun.getPivotPointX() + ", " + sun.getPivotPointY(), 200, 230);
//		g.drawString("sc1's position: " + sc1.getPositionX() + ", " + sc1.getPositionY(), 200, 260);
//		g.drawString("sc1's Pivot position: " + sc1.getPivotPointX() + ", " + sc1.getPivotPointY(), 200, 290);
		//g.drawLine(600, 300, 600, 500);
		//g.drawLine(500, 400, 700, 400);
		
		//g.drawLine(647, 300, 647, 500);
		//g.drawLine(500, 447, 700, 447);
	}
	
	
	public static void main(String[] args) {
		SolarSystemSimulator game = new SolarSystemSimulator();
		game.start();
	}
}

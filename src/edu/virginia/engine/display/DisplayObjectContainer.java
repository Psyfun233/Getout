package edu.virginia.engine.display;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DisplayObjectContainer extends DisplayObject{

	Vector<DisplayObjectContainer> children;
	
	
	//Constructor
	public DisplayObjectContainer(String id) {
		super(id);
		children = new Vector<DisplayObjectContainer>();
	}

	public DisplayObjectContainer(String id, String imageFileName) {
		super(id, imageFileName);
		children = new Vector<DisplayObjectContainer>();
	}

	
	//add and remove methods
	public void addChild(DisplayObjectContainer o) {
		children.add(o);
		o.setParent(this);
	}
	
	public void addChildAtIndex(DisplayObjectContainer o, int i) {
		children.add(i, o);
		o.setParent(this);
	}
	
	public void removeChild(DisplayObjectContainer o) {
		children.remove(o);
		o.setParent(null);
	}
	
	public void removeByIndex(int i) {
		children.get(i).setParent(null);
		children.remove(i);
	}
	
	public void removeAll() {
		children.clear();
	}
	
	public boolean contains(DisplayObjectContainer j) {
		return children.contains(j);
	}
	
	public DisplayObjectContainer getChild(int i) {
		return children.get(i);
	}
	
	public DisplayObjectContainer getChild(String id) {
		for(DisplayObjectContainer k: children) {
			if(k.getId().equals(id)) {
				return k;
			}
		}
		return null;
	}
	
	
	public List<DisplayObjectContainer> getChildren(){
		return children;
	}
	
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
	}
	

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.draw(g);
		this.applyTransformations(g2d);
		for (DisplayObjectContainer child: children) {
			child.draw(g2d);
		}
		this.reverseTransformations(g2d);
	}

}

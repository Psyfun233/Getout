package edu.virginia.engine.display;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import edu.virginia.engine.events.EventDispatcher;

/**
 * A very basic display object for a java based gaming engine
 * 
 * */
public class DisplayObject extends EventDispatcher{

	/*
	 *  ******************************************** field variables ***********************************************************
	 */
	
	private String id;
	private BufferedImage displayImage;
	
	private boolean visible = true;
	
	private int positionX;
	private int positionY;
	
	private int pivotPointX;
	private int pivotPointY;
	
	private double scaleX = 1.0;
	private double scaleY = 1.0;
	
	private double rotation;
	private float alpha = 1.0f;
	
	private DisplayObject parent;
	
	/*
	 * ********************************************  Constructors ********************************************************* 
	 * can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
	}

	/*
	 *  ********************************************  getters and setters  ***************************************************
	 */
	
	public void setId(String id) {this.id = id;}
	public String getId() {return id;}

	public boolean getVisible() {return visible;}
	public void setVisible(boolean x) {visible = x;}
	
	public int getPositionX() {return positionX;}
	public void setPositionX(int x) {positionX = x;}
	
	public int getPositionY() {return positionY;}
	public void setPositionY(int y) {positionY = y;}
	
	public int getPivotPointX() {return pivotPointX;}
	public void setPivotPointX(int x) {pivotPointX = x;}
	
	public int getPivotPointY() {return pivotPointY;}
	public void setPivotPointY(int y) {pivotPointY = y;}
	
	public void setPivotPointToCenter() {
		pivotPointX = this.getUnscaledWidth()/2;
		pivotPointY = this.getUnscaledHeight()/2;
	}
	
	public double getScaleX() {return scaleX;}
	public void setScaleX(double x) {scaleX = x;}
	
	public double getScaleY() {return scaleY;}
	public void setScaleY(double y) {scaleY = y;}
	
	public double getRotation() {return rotation;}
	public void setRotation(double r) {rotation = r;}
	
	public float getAlpha() {return alpha;}
	public void setAlpha(float a) {
		if(alpha > 1) {
			alpha = 1;
		}
		else if(alpha < 0) {
			alpha = 0;
		}
		else{
			alpha = a;
		}
	}
	
	public DisplayObject getParent() {return parent;}
	public void setParent(DisplayObject parent) {this.parent = parent;}
	
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {return this.displayImage;}
	
	//helper method to setImage	
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + "image" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}
	
	protected void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}
	
	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}

	
	/*
	 * ****************************************************  transformation methods ************************************************
	 */
	
	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(positionX, positionY);
		g2d.rotate(rotation*Math.PI/180);
		g2d.scale(scaleX, scaleY);
		g2d.translate(-pivotPointX, -pivotPointY);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.translate(pivotPointX, pivotPointY);
		g2d.scale(1/scaleX, 1/scaleY);
		g2d.rotate(-rotation*Math.PI/180);
		g2d.translate(-positionX,-positionY);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}
	
	public AffineTransform getLocalTransform() {
		AffineTransform at = new AffineTransform();
		at.translate(this.positionX, this.positionY);
		at.rotate(this.rotation*Math.PI/180);
		at.scale(this.scaleX, this.scaleY);
		at.translate(-this.pivotPointX, -this.pivotPointY);
		return at;
	}
	
	public AffineTransform getGlobalTransform() {
		AffineTransform at;
		if (this.parent == null) {
			at = new AffineTransform();
		} else {
			at = this.getParent().getGlobalTransform();
		}
		at.concatenate(getLocalTransform());
		return at;
	}
	
	/*
	 * ***************************************************  collision detection ************************************************
	 */
	
	public Shape getGlobalHitbox() {
		return this.getGlobalTransform().createTransformedShape(new Rectangle(0, 0, 
				this.getUnscaledWidth(), this.getUnscaledHeight()));
	}
	
	public Shape getLocalHitbox() {
		return this.getLocalTransform().createTransformedShape(new Rectangle(0, 0,
				this.getUnscaledWidth(),this.getUnscaledHeight()));
	}
	
	public boolean collideWith(DisplayObjectContainer ob) {
		// first check collision with children
		if(ob.getChildren().size() > 0) {
			for(DisplayObjectContainer doc: ob.getChildren()) {
				if(collideWith(doc)) {
					return true;
				}
			}
		}
		
		// if no child collides with it check if the container itself collide
		Area a = new Area(getGlobalHitbox());
		a.intersect(new Area(ob.getGlobalHitbox()));
		//		if(!a.isEmpty()) {
		//			dispatchEvent(new Event("collision",this));
		//		}
		return !a.isEmpty();

	}
	

	
	/*
	 * ***************************************************** update / draw loop ***************************************************
	 */

	protected void update(ArrayList<Integer> pressedKeys) {
		
	}

	public void draw(Graphics g) {
		if (displayImage != null && visible) {
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);
			g2d.drawImage(displayImage, 0, 0,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);
			reverseTransformations(g2d);
		}
	}





}

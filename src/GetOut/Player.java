package GetOut;

import java.awt.Rectangle;
import java.awt.Shape;

import edu.virginia.engine.display.PhysicsSprite;

public class Player extends PhysicsSprite{

	boolean hasRuby = false;
	boolean hasSapphire = false;
	boolean hasEmerald  = false;


	public Player(String id) {
		super(id);
	}
	
	public Player(String id, String fileName, int numFrames,String path) {
		super(id,fileName,numFrames,path);
	}
	
	public void setUp(int x, int y, double sx, double sy) {
		this.setPositionX(x);
		this.setPositionY(y);
		this.setScaleX(sx);
		this.setScaleY(sy);
		this.setPivotPointToCenter();
	}
	
	@Override
	public Shape getGlobalHitbox() {
		return this.getGlobalTransform().createTransformedShape(new Rectangle((int)(this.getUnscaledWidth()*0.3),
				(int)(this.getUnscaledHeight()*0.2),(int)(this.getUnscaledWidth()*0.4),(int)(this.getUnscaledHeight()*0.6)));
	}

	public boolean isHasRuby() {
		return hasRuby;
	}

	public void setHasRuby(boolean hasRuby) {
		this.hasRuby = hasRuby;
	}

	public boolean isHasSapphire() {
		return hasSapphire;
	}

	public void setHasSapphire(boolean hasSapphire) {
		this.hasSapphire = hasSapphire;
	}

	public boolean isHasEmerald() {
		return hasEmerald;
	}

	public void setHasEmerald(boolean hasEmerald) {
		this.hasEmerald = hasEmerald;
	}

	
}

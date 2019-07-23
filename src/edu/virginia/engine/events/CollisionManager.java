package edu.virginia.engine.events;

public class CollisionManager implements IEventListener{
	
	boolean collide = false;
	
	@Override
	public void handleEvent(Event e) {
		collide = true;
	//	System.out.println("colliding");
	}

}

package edu.virginia.lab4;

import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;

public class MyQuestManager implements IEventListener{

	public static final String COIN_PICKED_UP = "coinPickedUp";
	boolean d = false;
	
	@Override
	public void handleEvent(Event e) {
		System.out.println("Mario picked up the coin!");
		d = true;
	}

}

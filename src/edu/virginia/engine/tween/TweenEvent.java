package edu.virginia.engine.tween;

import edu.virginia.engine.events.Event;

public class TweenEvent extends Event{

	public static final String TWEEN_COMPLETE_EVENT = "TweenComplete";

	
	public TweenEvent(String e, Tween s) {
		super(e, s);
	}
	
	public Tween getTween() {
		return (Tween)this.getSource();
	}

}

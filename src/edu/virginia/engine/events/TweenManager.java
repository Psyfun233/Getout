package edu.virginia.engine.events;

public class TweenManager implements IEventListener{
	
	public static final String TWEEN_COMPLETED = "TweenComplete";
	public boolean coinFade = false;
	
	@Override
	public void handleEvent(Event e) {
		if(e.getEventType().equals("TweenComplete")) {
			//System.out.println("coin fading");
			coinFade = true;
		}
		
	}

}

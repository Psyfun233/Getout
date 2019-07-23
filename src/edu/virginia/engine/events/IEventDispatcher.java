package edu.virginia.engine.events;


public interface IEventDispatcher {
	public void addEventListener(IEventListener listener, String eventType);
	
	public void removeEventListener(IEventListener listener, String eventType);
	
	public void dispatchEvent(Event e);
	
	public boolean hasEventListener(IEventListener	listener, String eventType);
}

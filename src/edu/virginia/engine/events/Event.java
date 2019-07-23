package edu.virginia.engine.events;

public class Event {
	String eventType;
	IEventDispatcher source;
	
	
	public Event(String e, IEventDispatcher s) {
		eventType = e;
		source = s;
	}
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String s) {
		eventType = s;
	}
	
	public IEventDispatcher getSource() {
		return source;
	}
	
	public void setSource(IEventDispatcher s) {
		source = s;
	}
}

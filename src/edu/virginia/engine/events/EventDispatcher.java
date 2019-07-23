package edu.virginia.engine.events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventDispatcher implements IEventDispatcher {
		
	private HashMap<String,ArrayList<IEventListener> > eventObservers = new HashMap<String,ArrayList<IEventListener>>();
	
	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		if (eventObservers.containsKey(eventType)) {
			eventObservers.get(eventType).add(listener);
		}else {
			ArrayList<IEventListener> a = new ArrayList<IEventListener>();
			eventObservers.put(eventType, a);
			a.add(listener);
		}
	}

	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		if(eventObservers.containsKey(eventType)) {
			eventObservers.get(eventType).remove(listener);
		}else {
//			System.out.println("No listeners for this event");
		}
	}

	@Override
	public void dispatchEvent(Event event) {
//		System.out.println(eventObservers.containsKey(event.getEventType()));
		if(this.getObservers().containsKey(event.getEventType())) {
			for(IEventListener el: eventObservers.get(event.getEventType())) {
				el.handleEvent(event);
			}
		}else {
//			System.out.println(event.getEventType());
//			System.out.println("Event is not being listened to");
		}
	}

	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		if(eventObservers.containsKey(eventType)) {
			if (eventObservers.get(eventType).contains(listener)) {
				return true;
			} else {
				return false;
			}
		}else {
//			System.out.println("event " + eventType + " is not being listened");
			return false;
		}
	}
	
	public HashMap<String,ArrayList<IEventListener>> getObservers(){
		return eventObservers;
	}
}

package edu.virginia.engine.tween;

import java.util.ArrayList;
import java.util.Iterator;

public class TweenJuggler {
	
	private static TweenJuggler juggler;
	private static ArrayList<Tween> tweenList = new ArrayList<Tween>();
	
	public TweenJuggler() {
		if(juggler != null) System.out.println("Error: Cannot re-initialize singleton class!");
		juggler = this;
	}
	
	public static ArrayList<Tween> getTweenList() {
		return tweenList;
	}
	
	public static TweenJuggler getJuggler() {
		return juggler;
	}
	
	public void add(Tween tween) {
		tweenList.add(tween);
	}
	
	public void update() {
		Iterator<Tween> it = tweenList.iterator();
		while(it.hasNext()) {
			Tween t = it.next();
			t.update();
			if(t.isComplete()) {
				it.remove();
			}
		}
	}
}	

package edu.virginia.engine.tween;

public class TweenTransitions {
	
	Tween t;
	TweenParam tp;
	
	public void applyTransitions(double percentDone) {
		t.setValue(tp.getParam(), tp.getStartVal()+tp.getEndVal()*percentDone);
	}
	
	public void easeInOut(double percentDone){
		applyTransitions(percentDone*percentDone);
	}
}

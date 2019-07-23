package edu.virginia.engine.tween;

import java.util.ArrayList;
import java.util.Iterator;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.EventDispatcher;

public class Tween extends EventDispatcher{
	private DisplayObject object;
	private ArrayList<TweenParam> parameters = new ArrayList<TweenParam>();
	
	
	public Tween (DisplayObject o) {
		this.object = o;
	}
	
	public void animate(TweenableParam field, double startVal, double endVal, double time) {
		TweenParam tp = new TweenParam(field,startVal,endVal, time);
		parameters.add(tp);
		tp.c.resetGameClock();
	}
	
	
	
	public void update() {
		Iterator<TweenParam> it = parameters.iterator();
		while(it.hasNext()) {
			TweenParam p = it.next();
			if(p.c.getElapsedTime()<=p.tweenTime) {
				setValue(p.getParam(),p.startVal + p.c.getElapsedTime()*(p.endVal - p.startVal)/p.tweenTime);
			}else {
				it.remove();
			}
			if(parameters.isEmpty()) {
				this.dispatchEvent(new Event("TweenComplete",this));
			}
		}
		
	}
	
	public boolean isComplete() {
		return parameters.isEmpty();
	}
	
	public void setValue(TweenableParam param, double value) {
		if (param == TweenableParam.ALPHA) object.setAlpha((float)value);
		if (param == TweenableParam.X_SCALE) object.setScaleX(value);
		if (param == TweenableParam.Y_SCALE) object.setScaleY(value);
		if (param == TweenableParam.X_POS) object.setPositionX((int)value);
		if (param == TweenableParam.Y_POS) object.setPositionY((int)value);
		if (param == TweenableParam.ROTATION) object.setRotation((int) value);
	}
}

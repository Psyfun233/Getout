package edu.virginia.engine.tween;

import edu.virginia.engine.util.GameClock;

public class TweenParam {
	
	TweenableParam param;
	double startVal;
	double endVal;
	double tweenTime;	// in ms
	GameClock c = new GameClock();
	
	public TweenParam(TweenableParam tp, double startVal, double endVal, double time) {
		this.param = tp;
		this.startVal = startVal;
		this.endVal = endVal;
		this.tweenTime = time;  
	}
	
	public TweenableParam getParam() {
		return param;
	}
	
	public double getStartVal() {
		return startVal;
	}
	
	public double getEndVal() {
		return endVal;
	}
	
	public double getTweenTime() {
		return tweenTime;
	}
	
	
}

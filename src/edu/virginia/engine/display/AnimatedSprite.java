package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite{

	BufferedImage[] frames;
	
	private int currentFrame; 
	private int startIndex;
	private int endIndex;
	private boolean playing;
	private int animationSpeed;
	private int frameCounter;
	
	
	public AnimatedSprite(String id, String fileName, int numFrames,String type) {
		super(id,fileName);
		setAnimationSpeed(4);
		frames = new BufferedImage[numFrames];
		for(int i = 0; i < numFrames; i++) {
			frames[i] = this.readImage(type + "_" + (i+1) + ".png");
		}
		setPlaying(false);
		frameCounter = 0;
	}
	
	
	public AnimatedSprite(String id, String fileNames) {
		super(id,fileNames);
	}

	public AnimatedSprite(String id) {
		super(id);
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		
		if(playing) {
			if (this.frameCounter < this.animationSpeed) {
				frameCounter ++;
			}else {

				if(currentFrame < startIndex || currentFrame > endIndex) {
					currentFrame = startIndex;
				}
				if(currentFrame < endIndex) {
					currentFrame++;
				}else {
					currentFrame = startIndex;
				}
				super.setImage(frames[currentFrame]);
				frameCounter = 0;
			}
		}
	}


	public int getCurrentFrame() {
		return currentFrame;
	}


	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	public int getEndIndex() {
		return endIndex;
	}


	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}


	public boolean isPlaying() {
		return playing;
	}


	public void setPlaying(boolean playing) {
		this.playing = playing;
	}


	public int getAnimationSpeed() {
		return animationSpeed;
	}


	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed = animationSpeed;
	}
	
	public BufferedImage[] getFrames() {
		return frames;
	}
}

package edu.virginia.engine.sound;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sounds {
	 private AudioInputStream soundStream;
	 private HashMap<String, Clip> allSounds = new HashMap<String, Clip>();
	
	 /*
	  * *****************************************constructor**************************************************
	  */
	 public Sounds() {
	 }
	 
	/*
	 * ***************************************getters and setters *******************************************
	 */
	public AudioInputStream getSoundStream() {
		return soundStream;
	}
	public void setSoundStream(AudioInputStream soundStream) {
		this.soundStream = soundStream;
	}
	public HashMap<String, Clip> getAllSound() {
		return allSounds;
	}
	public void setAllSound(HashMap<String, Clip> allSound) {
		this.allSounds = allSound;
	}
	 
	/*
	 * ******************************************methods******************************************************
	 */
	public void loadSound(String id, String fileName) {
		try {
			soundStream = AudioSystem.getAudioInputStream(new File("resources" + File.separator + fileName));
    		allSounds.put(id, AudioSystem.getClip());
    		allSounds.get(id).open(soundStream);
		} catch (Exception e) {
		}
	}
	
	public void playSoundEffect(String	id) {
		 allSounds.get(id).start();
	 };	
	 
	 //make the clip loop continuously
	public void loopContinuously(String id) {
		allSounds.get(id).loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	 public void playSound(String id) {
		 allSounds.get(id).start();
	 };
	 
	 public void stopSound(String id) {
		 allSounds.get(id).stop();
	 }
	 
	 //rewind the clip and start from beginning
	 public void rewindSound(String id) {
		 allSounds.get(id).setFramePosition(0);
	 }
	 
	 public void changeSoundVolume(String id, float percent) {
		 Clip c = allSounds.get(id);
		 FloatControl volume = 
				    (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		 if (percent >= 1.0f) percent = 1.0f;
		 if (percent <= 0.0f) percent = 0.0f;
		 float value = percent * (volume.getMaximum() - volume.getMinimum()) + volume.getMinimum() ;
		 volume.setValue(value);
	 }
}

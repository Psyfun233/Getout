package GetOut;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager{
	
	
    AudioInputStream music;
    AudioInputStream soundeffect;
    HashMap<String, Clip> musicclip = new HashMap<String, Clip>();
    HashMap<String, Clip> effectclip = new HashMap<String, Clip>();
    
    public SoundManager() {
    }

    public void loadSoundEffect(String id,	String	filename) {
    	try
    	{
    		//https://www.geeksforgeeks.org/play-audio-file-using-java/
    		soundeffect = AudioSystem.getAudioInputStream(new File("resources" + File.separator + filename));
    		effectclip.put(id, AudioSystem.getClip());
    		effectclip.get(id).open(soundeffect);
    	}
    	catch (Exception e){
    		System.out.println("No file");
    	} 
    };

	 public void playSoundEffect(String	id) {
		 effectclip.get(id).start();
	 };	

	 public void loadMusic(String id, String filename) {
		 try {
			 music = AudioSystem.getAudioInputStream(new File("resources" + File.separator + filename));
			 musicclip.put(id, AudioSystem.getClip());
			 musicclip.get(id).open(music);
			 musicclip.get(id).loop(Clip.LOOP_CONTINUOUSLY);
		 } catch (Exception e){
			 System.out.println("No file");
		 }

	 };

	 public void playMusic(String id) {
		 musicclip.get(id).start();
		 //musicclip.get(id).loop(Clip.LOOP_CONTINUOUSLY);
	 };	//music	loops	and	plays	forever,	consider	adding	a	parameter for	looping

	 public void stopSoundEffect(String id) {
		 effectclip.get(id).stop();
	 }
	 
	 public void stopMusic(String id) {
		 musicclip.get(id).stop();
	 }
	 
	 public void changeEffectVolume(String id, float percent) {
		 Clip c = effectclip.get(id);
		 FloatControl volume = 
				    (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		 if (percent >= 1.0f) percent = 1.0f;
		 if (percent <= 0.0f) percent = 0.0f;
		 float value = percent * (volume.getMaximum() - volume.getMinimum()) + volume.getMinimum() ;
		 volume.setValue(value);
	 }
	 
	 public void changeMusicVolume(String id, float percent) {
		 Clip c = musicclip.get(id);
		 FloatControl volume = 
				    (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
		 if (percent > 1.0f) percent = 1.0f;
		 if (percent < 0.0f) percent = 0.0f;
		 float value = percent * (volume.getMaximum() - volume.getMinimum()) + volume.getMinimum();
		 volume.setValue(value);
	 }

}

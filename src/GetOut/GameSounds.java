package GetOut;

import edu.virginia.engine.sound.Sounds;

public class GameSounds extends Sounds {

	public void loadAllSounds() {
		this.loadSound("bgm", "bgm.wav");
		this.loadSound("scream", "scream.wav");
		this.loadSound("player_step", "player_step.wav");
		this.loadSound("step", "step.wav");
		this.loadSound("door_open", "door_open.wav");
		this.loadSound("door_close","door_close.wav");
	}
}

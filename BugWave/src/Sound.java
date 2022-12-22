import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    AudioInputStream audioInputStream;
    Clip clip;
    String song;

    public Sound(String var1) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.song = var1;
        this.clip = AudioSystem.getClip();
        this.audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(this.getClass().getResourceAsStream(var1)));
        this.clip.open(this.audioInputStream);
    }

    public void play() {
        this.clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        this.clip.stop();
    }

}


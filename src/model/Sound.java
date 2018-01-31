package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;


public class Sound {

    private MediaPlayer player;

    public Sound(String file) {
        Media sound = new Media(new File(file).toURI().toString());
        player = new MediaPlayer(sound);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
    }

    public void playSound() {
        player.play();
    }

    public void pauseSound() {
        player.pause();
    }

    public void stopSound(){
        player.stop();
    }
}

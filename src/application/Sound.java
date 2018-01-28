package application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class Sound {

    MediaPlayer player;

    public Sound() {
        Media sound = new Media(new File("src/resources/sound/Eminem.wav").toURI().toString());
        player = new MediaPlayer(sound);
        playSound();
    }

    public void playSound() {
        player.play();
    }
}

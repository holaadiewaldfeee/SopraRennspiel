package controller;

import application.Main;
import application.Sound;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.GameModel;

import java.util.ArrayList;

public class MainController {

    private static Stage stage;
    public static ArrayList<Controller> controllers;
    private static int indx = 0;

    private Sound s;

    /**
     * The Main Controller of the game. It will manage all views as well as the subcontrollers designated to interact
     * with the views
     *
     * @param controllers the initial sub-controllers for the game
     */
    public MainController(Stage stage, ArrayList<Controller> controllers) {
        this.stage = stage;
        this.controllers = controllers;
        changeController(indx);

        //s = new Sound("src/resources/sound/Eminem.wav");
        //s.playSound();
    }

    /**
     * Updates all needed dependencies every frame
     *
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {
        controllers.get(indx).updateKeys();
        controllers.get(indx).update();
        GameModel.update(timeDifferenceInSeconds);
    }

    // change views through controllers
    static void changeController(int i) {
        indx = i;
        stage.setScene(controllers.get(indx).getView().getScene());
    }

}

package controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import model.*;
import view.ViewManager;

public class GameController {

    private GameModel gameModel;
    private ViewManager view;
    private Scene scene;

    public GameController(GameModel gameModel, ViewManager view) {
        this.view = view;
        this.gameModel = gameModel;
        this.scene = view.stage.getScene();
        // Set up keylistener
        setUpInputHandler();
    }

    /**
     * Updates all needed dependencies every frame
     *
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {

    }

    private void setUpInputHandler() {
        /*
         * Useful actions:
         * setOnKeyPressed, setOnKeyReleased
         */
        System.out.println("fooooooooooo");
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        System.out.println(code);
                    }
                });


    }
}

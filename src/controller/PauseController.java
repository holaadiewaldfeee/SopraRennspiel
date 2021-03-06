package controller;

import javafx.scene.input.KeyCode;
import model.GameModel;
import view.PauseView;
import view.View;

/**
 * The controller reacting to input and handling user interaction in pause mode.
 */
public class PauseController implements Controller {

    private PauseView pauseView;
    private static GameModel gameModel;


    public PauseController(GameModel model) {
        pauseView = new PauseView();
        gameModel = model;
        setupInteraction();
    }

    @Override
    public void update(){
        pauseView.render(gameModel);
        GameModel.getCar().getSound().pauseSound();
    }

    //handle input
    @Override
    public void setupInteraction() {

        pauseView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                System.out.println("close");
                System.exit(0);
            }
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.P) {
                System.out.println("play");
                MainController.changeController(1);
                GameModel.startRound();
                GameModel.isPaused = false;
            }

        });
    }

    @Override
    public View getView() {
        return this.pauseView;
    }

    @Override
    public void updateKeys() {

    }
}

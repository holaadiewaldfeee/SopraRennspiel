package controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.GameModel;
import view.PauseView;
import view.View;


public class PauseController implements Controller {

    private PauseView pauseView;
    // Main game gameModel
    private static GameModel gameModel;

    public PauseController(GameModel model) {
        pauseView = new PauseView();
        gameModel = model;
        setupInteraction();
    }

    public void update(){}

    public void render(){}


    @Override
    public void setupInteraction() {
        pauseView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                System.out.println("close");
                System.exit(0);
            }

        });
    }

    @Override
    public View getView() {
        return this.pauseView;
    }
}

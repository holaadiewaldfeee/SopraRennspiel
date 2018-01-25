package controller;

import javafx.scene.input.KeyCode;
import model.GameModel;
import view.PauseView;
import view.View;


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
    }

    @Override
    public void render(){}

    //handle input
    @Override
    public void setupInteraction() {

        pauseView.backToGame.setOnAction(e -> {
            System.out.println("play game");
            MainController.changeController(1);
        });

        pauseView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                System.out.println("close");
                System.exit(0);
            }
            if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.P) {
                System.out.println("play");
                MainController.changeController(1);
            }

        });
    }

    @Override
    public View getView() {
        return this.pauseView;
    }
}

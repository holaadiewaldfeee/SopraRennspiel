package controller;

import javafx.scene.input.KeyCode;
import model.GameModel;
import view.StartView;
import view.View;


public class StartController implements Controller {

    private StartView startView;
    private static GameModel gameModel;


    public StartController(GameModel model) {
        startView = new StartView();
        gameModel = model;
        setupInteraction();
    }

    @Override
    public void update() {
        startView.render(gameModel);
    }

    //handle input
    @Override
    public void setupInteraction() {

        startView.startButton.setOnAction(e -> {
            System.out.println("game started");
            MainController.changeController(1);
        });

        startView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                System.out.println("hadebye");
                System.exit(0);
            }
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("gogogogo");
                MainController.changeController(1);
            }
        });

    }

    @Override
    public View getView() {
        return this.startView;
    }

    @Override
    public void updateKeys() {

    }

}

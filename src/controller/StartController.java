package controller;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.GameModel;
import view.StartView;
import view.View;

import java.util.Observable;


public class StartController implements Controller {

    private StartView startView;
    // Main game gameModel
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

    @Override
    public void render() {
    }

    @Override
    public void setupInteraction() {
        startView.backToStartView.setOnAction(e -> {
            System.out.println("game started");
        });
        startView.getScene().setOnKeyPressed(e -> {

            if (e.getCode() == KeyCode.ESCAPE){
                System.out.println("hadebye");
                System.exit(0);
            }
            if (e.getCode() == KeyCode.ENTER){
                System.out.println("gogogog");
                MainController.changeController(1);
            }
        });

    }

    @Override
    public View getView() {
        return this.startView;
    }
}

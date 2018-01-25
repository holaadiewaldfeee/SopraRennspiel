package controller;

import application.Main;
import javafx.scene.input.KeyCode;
import model.GameModel;
import view.GameView;
import view.View;

//todo: auto dreht und fährt nicht gleichzeitig

public class GameController implements Controller {

    private GameView gameView;
    private static GameModel gameModel;

    public GameController(GameModel model) {
        gameView = new GameView();
        gameModel = model;
        setupInteraction();
    }

    @Override
    public void update() {
        gameView.render(gameModel);
        if (gameModel.getCar().getSpeed() != 0.0d) {
            //System.out.println(gameModel.getCar().getX() + " " + gameModel.getCar().getY());
            //System.out.println(gameModel.getCar().getSpeed());
        }
    }

    @Override
    public void render() {
    }

    @Override
    public void setupInteraction() {

        gameView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                gameModel.getCar().rotate(-10);
            }
            if (e.getCode() == KeyCode.RIGHT) {
                gameModel.getCar().rotate(10);
            }
            if (e.getCode() == KeyCode.UP) {
                gameModel.getCar().setSpeed(-1);
            }
            if (e.getCode() == KeyCode.DOWN) {
                gameModel.getCar().setSpeed(1);
            }
            if (e.getCode() == KeyCode.P){
                System.out.println("pause Game");
                MainController.changeController(2);
            }
            if (e.getCode() == KeyCode.R){
                System.out.println("reset Game");
                //todo: reseten geht noch nicht -> stage neu laden?!
                //Main.newGame();
            }
            if (e.getCode() == KeyCode.ESCAPE){
                System.out.println("hadebye");
                System.exit(0);
            }
        });
    }

    public View getView(){
        return this.gameView;
    }
}

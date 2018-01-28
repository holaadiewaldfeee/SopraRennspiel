package controller;

import javafx.scene.input.KeyCode;
import model.GameModel;
import view.GameView;
import view.View;
import java.util.ArrayList;;

//todo: auto dreht und fährt nicht gleichzeitig

public class GameController implements Controller {

    private GameView gameView;
    private static GameModel gameModel;
    private final static int ROTATEINTENSITY = 1;


    ArrayList<String> input = new ArrayList<String>();

    public ArrayList<String> getInput() {
        return input;
    }



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
    public void updateKeys(){
        if (input.contains("LEFT")){
            gameModel.getCar().rotate(-ROTATEINTENSITY);
            System.out.println("left");
        }
        if (input.contains("RIGHT")){
            System.out.println("right");
            gameModel.getCar().rotate(ROTATEINTENSITY);

        }
        if (input.contains("UP")){
            gameModel.getCar().setSpeed(-1);
            System.out.println("up");
        }
        if (input.contains("DOWN")){
            System.out.println("down");
            gameModel.getCar().setSpeed(1)
            ;
        }
    }

    @Override
    public void setupInteraction() {



        gameView.getScene().setOnKeyPressed(
                e -> {
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
                    String code = e.getCode().toString();

                    //only add once
                    if ( !input.contains(code) )
                        input.add( code );
                });

        gameView.getScene().setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

    }




    public View getView(){
        return this.gameView;
    }

}

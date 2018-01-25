package controller;

import application.Main;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import view.GameView;
import view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//todo: auto dreht und fährt nicht gleichzeitig

public class GameController implements Controller {

    private GameView gameView;
    private static GameModel gameModel;



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
            gameModel.getCar().rotate(-1);
            System.out.println("left");
        }
        if (input.contains("RIGHT")){
            gameModel.getCar().rotate(1);

        }
        if (input.contains("UP")){
            gameModel.getCar().setSpeed(-1);
            System.out.println("up");
        }
        if (input.contains("DOWN")){
            gameModel.getCar().setSpeed(1);
        }
    }

    @Override
    public void setupInteraction() {



        gameView.getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
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

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        gameView.getScene().setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });

        //das alte
        /*gameView.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                System.out.println("left");

                //gameModel.getCar().rotate(-10);
            }
            if (e.getCode() == KeyCode.RIGHT) {
                System.out.println("right");
                //gameModel.getCar().rotate(10);
            }
            if (e.getCode() == KeyCode.UP) {
                System.out.println("up");
                //gameModel.getCar().setSpeed(-1);
            }
            if (e.getCode() == KeyCode.DOWN) {
                System.out.println("down");
                //gameModel.getCar().setSpeed(1);
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
            System.out.println("---------------------");
        });*/
    }




    public View getView(){
        return this.gameView;
    }

}

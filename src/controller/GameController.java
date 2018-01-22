package controller;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import view.ViewManager;


public class GameController {

    private GameModel gameModel;
    private ViewManager view;
    private Scene scene;
    private boolean help = false;


    public GameController(GameModel gameModel, ViewManager view) {
        this.view = view;
        this.gameModel = gameModel;
        this.scene = view.stage.getScene();
        // Set up keylistener
        setUpInputHandler();
    }


    /**
     * Updates all needed dependencies every frame
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {

    }

    private void setUpInputHandler() {
        /**----------------------------------------
         * Useful actions:
         * setOnKeyPressed, setOnKeyReleased
         */
        System.out.println("fooooooooooo");
        //for keyboard input
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        String code = e.getCode().toString();
                        System.out.println(code);
                        if(e.getCode() == KeyCode.P){
                            //klappt so noch nicht mit wieder p zurück ins spiel
                            if(!help) {
                                help = true;
                                view.changeScene(2);
                                System.out.println("heheh");
                            }else{
                                view.changeScene(1);
                                System.out.println("ihihihihihih");
                            }
                        }
                        if(e.getCode() == KeyCode.ENTER){
                            view.changeScene(1);
                        }
                    }
                });
    }
}

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
     *
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {
        // :todo hier uodate oder so von GameModel wegen auto und so und vllt render methode zum auto zeichnen oder auh nicht
        System.out.println(gameModel.getCar().getX() + " " + gameModel.getCar().getY());

        // Sage dem model, dass jetzt zeit vergangen ist und ein neuer update zyklus abgeschlossen ist
        // wenn wir die update methode des models aufrufen sollen alle seine informationen aktualisiert werden :)
        gameModel.update();
        this.view.render(gameModel.getCar());
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
                        if (e.getCode() == KeyCode.P) {
                            //klappt so noch nicht mit wieder p zurück ins spiel
                            if (!help) {
                                help = true;
                                view.changeScene(2);
                                System.out.println("heheh");
                            } else {
                                view.changeScene(1);
                                System.out.println("ihihihihihih");
                            }
                        }
                        if (e.getCode() == KeyCode.ENTER) {
                            view.changeScene(1);
                        }
                    }
                });

        view.sceneList.get(1).getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        // TODO: hier auf den rihtigen button prüfen
                        //System.out.println("pause gemaked");
                        //view.changeScene(2);
                        if (e.getCode() == KeyCode.LEFT) {
                            gameModel.getCar().setSpeed(-1,0);
                        }
                        if (e.getCode() == KeyCode.RIGHT) {
                            gameModel.getCar().setSpeed(1,0);
                        }
                        if (e.getCode() == KeyCode.UP) {
                            gameModel.getCar().setSpeed(0,-1);
                        }
                        if (e.getCode() == KeyCode.DOWN) {
                            gameModel.getCar().setSpeed(0,1);
                        }
                    }
                });

        view.sceneList.get(2).getScene().setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {
                        System.out.println("pause unmaked");
                        view.changeScene(1);
                    }
                });
    }
}

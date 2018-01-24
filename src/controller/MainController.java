package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainController {

    private static Stage stage;
    // Generically typed view. This is to always offer the same methods in the view
    private static ArrayList<Controller> controllers;
    private static int indx = 0;

    /**
     * The Main Controller of the game. It will manage all views as well as the subcontrollers designated to interact
     * with the views
     *
     * @param controllers the initial sub-controllers for the game
     */
    public MainController(Stage stage, ArrayList<Controller> controllers) {
        this.stage = stage;
        this.controllers = controllers;
        changeController(indx);
    }

    /**
     * Updates all needed dependencies every frame
     *
     * @param timeDifferenceInSeconds the time passed since last frame
     */
    public void updateContinuously(double timeDifferenceInSeconds) {
        // Sage dem model, dass jetzt zeit vergangen ist und ein neuer update zyklus abgeschlossen ist
        // wenn wir die update methode des models aufrufen sollen alle seine informationen aktualisiert werden :)

        controllers.get(indx).update();
        controllers.get(indx).render();
    }

    // Hier den controllers wechseln
    static void changeController(int i){
        indx = i; // irgendwas anderes...
        stage.setScene(controllers.get(indx).getView().getScene());
    }
}

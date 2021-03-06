package application;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import java.util.ArrayList;

/**
 * Main entrance point into the game. The javafx application will launch a screen and show the scenes to the user.
 */
public class Main extends Application {

    private long oldTime;
    static GameModel gameModel = new GameModel();
    static ArrayList<Controller> controllerList;

    @Override
    public void start(Stage stage) throws Exception {

        //Create the instances for the game

        // Sets general parameters for the stage
        stage.setTitle("Rennspiel_Sabrina_Boehm");
        stage.setResizable(false);
        stage.sizeToScene();

        StartController startController = new StartController(gameModel);
        GameController gameController = new GameController(gameModel);
        PauseController pauseController = new PauseController(gameModel);
        controllerList = new ArrayList<>();
        controllerList.add(startController); // index 0
        controllerList.add(gameController); // index 1
        controllerList.add(pauseController); // index 2

        MainController controller = new MainController(stage, controllerList);

        //Start the gameloop. It is executed every frame, the long now is the current timestamp

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                /**
                 timeDifferenceInSeconds calculates the time between 2 frames.
                 It compares the last time with the current time (now) and
                 is divided by 1000000000.0 to get the time in seconds
                 */
                double timeDifferenceInSeconds = (now - oldTime) / 1000000000.0;

                //Sets the oldTime to now, so the next loop can take the difference
                oldTime = now;

                //Use the controller to update all dependencies
                controller.updateContinuously(timeDifferenceInSeconds);

            }
        }.start();

        stage.show();
    }


    /**
     * Launches the Application (calls startAfterWon overriden startAfterWon method)
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
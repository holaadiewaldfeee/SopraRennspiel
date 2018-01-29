package application;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;

import java.util.ArrayList;



/*todo:
0) Sound für crash und bei reset aus
1) Timer: bei pause nicht weiterlaufen
2) Kollision: mit den obstacles und mit den linien muss noch rein
3) Totalschaden: bei zu hoher geschwindigkeit muss das auto boom kaputt gehen und game over dialog
4) game over dialog und game won dialog
5) auto physik: rollwiderstand (gras und straße unterscheiden) und strömungswiderstand und ausrollen und beschleunigen
6) Unit Tests
7) nicht rausfahren aus dem fenster
8) Start und pause png zeug plus auto bilder etc gui zeug
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
     * Launches the Application (calls startButton overriden startButton method)
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}

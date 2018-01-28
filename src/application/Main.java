package application;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import java.util.ArrayList;



/*todo:
1) Sound: hab ne sound klasse gemacht und wollte dass es los geht sobald man die anwendung startet ging nicht und man muss noch n brum geräusch
machen wenns auto fährt
2) Timer: hab in der gameview n timer text gemacht der muss laufen wenn das auto losfährt
3) Reset: beim r klicken soll alles zurückgesetzt werden also car auf anfangs position und timer zurück etc
4) obstacles sollen noch nen mindestabstand zur start ziel linie und checkpoint linie haben und zu sich selbst
5) Kollision: mit den obstacles und mit den linien muss noch rein
6) Totalschaden: bei zu hoher geschwindigkeit muss das auto boom kaputt gehen und game over dialog
7) game over dialog und game won dialog
8) auto physik: rollwiderstand (gras und straße unterscheiden) und strömungswiderstand und ausrollen und beschleunigen
9) Unit Tests
10) Start und pause png zeug
 */




public class Main extends Application {

    private long oldTime;

    static GameModel gameModel = new GameModel();
    static ArrayList<Controller> controllerList;
    @Override
    public void start(Stage stage) throws Exception {

        //Create the instances for the game
///home/sabrina/Schreibtisch/Rennspiel_Sabrina_Boehm/src/resources/sound

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

        /*Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        InputStream midiFile = ClassLoader.getSystemResourceAsStream("src/resources/sound/music" );
        sequencer.setSequence( MidiSystem.getSequence(midiFile) );
        sequencer.start();
        sequencer.stop();*/


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

    //todo: hier wollte ich das reset dings machen
    /*public static void newGame(){
        controllerList.remove(1);
        controllerList.remove(2);
        controllerList.add(new GameController(gameModel));
        controllerList.add(new PauseController(gameModel));
    }*/


    /**
     * Launches the Application (calls startButton overriden startButton method)
     *
     * @param args
     */
    public static void main(String[] args) {

        //Sound s = new Sound();
        launch(args);
    }
}

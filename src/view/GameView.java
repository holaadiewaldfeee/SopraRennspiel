package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


/**
 * Contains every GUI element
 */
public class GameView implements MainView {


    private static ViewManager myMother;

    //The scene where all is stacked up
    private Scene scene;

    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;

    private Pane gamePane;
    public Scene getScene() {
        return scene;
    }


    public Button startButton;
    public Button pause;

    /**
     * GameView object for setting up the GUI
     *
     * @param stage the primary stage
     */
    public GameView(ViewManager vm) {
        myMother = vm;
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setUpGameWindow();
        setUpInputHandler();
    }

    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    public void setUpGameWindow() {
        gamePane = new Pane();
        Text text = new Text("Rennspiel");
        text.setLayoutX(100);
        text.setLayoutY(100);
        startButton = new Button("Sabrina ist ganz cool");
        startButton.setLayoutX(650);
        startButton.setLayoutY(700);
        gamePane.getChildren().add(startButton);
        gamePane.getChildren().add(text);
        pause = new Button("pause maken");
        pause.setLayoutY(400);
        pause.setLayoutX(400);
        //BorderPane border = new BorderPane();
        //ImageView imgView = new ImageView(new Image("resources/Autos/car_black_1.png"));
        //border.setCenter(imgView);
        //border.setRight(pause);
        //gamePane.getChildren().add(border);
        gamePane.getChildren().add(pause);
        rootPane.getChildren().add(gamePane);
    }



    private void setUpInputHandler() {

        startButton.setOnAction(e -> {
            myMother.changeScene(0);
            System.out.println("game started");
        });

        pause.setOnAction(e -> {
            myMother.changeScene(2);
            System.out.println("pause");
        });

    }
}
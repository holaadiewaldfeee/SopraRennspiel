package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Contains every GUI element
 */
public class StartView implements MainView {

    private static ViewManager myMother;

    //The scene where all is stacked up
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;

    // Buttons
    public Button startButton;
    public Button neuerFancyButton;

    /**
     * GameView object for setting up the GUI
     *
     * @param stage the primary stage
     */
    public StartView(ViewManager vm) {
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
        Pane gamePane = new Pane();
        Text text = new Text("Hallo!");
        text.setLayoutX(650);
        text.setLayoutY(100);
        text.setFont(new Font("Arial Black", 50));
        gamePane.getChildren().add(text);
        startButton = new Button("Start");
        startButton.setLayoutX(650);
        startButton.setLayoutY(700);
        gamePane.getChildren().add(startButton);
        rootPane.getChildren().add(gamePane);
        neuerFancyButton = new Button("hallo tobi du nudel");
        neuerFancyButton.setLayoutX(250);
        neuerFancyButton.setLayoutY(200);
        gamePane.getChildren().add(neuerFancyButton);

    }

    private void setUpInputHandler() {
        startButton.setOnAction(e -> {
            myMother.changeScene(1);
            System.out.println("game started");
        });

    }
}
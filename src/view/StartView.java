package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameModel;

public class StartView implements View {


    private Scene scene;
    private StackPane rootPane;
    private Pane gamePane;
    public Button startButton;


    public StartView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }



    //Sets up start window
    public void setupGameWindow() {

        //todo: startbild mit erklärung zum spiel ziel etc
        gamePane = new Pane();

        Text text = new Text("Rennspiel_StartView");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));

        startButton = new Button("Play Game!");
        startButton.setLayoutX(550);
        startButton.setLayoutY(600);
        startButton.setStyle("-fx-font-size: 40pt;");

        gamePane.getChildren().add(text);
        gamePane.getChildren().add(startButton);
        rootPane.getChildren().add(gamePane);


    }
    public Scene getScene() {
        return scene;
    }

    @Override
    public void render(GameModel model) {

    }
}

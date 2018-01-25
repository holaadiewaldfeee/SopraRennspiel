package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameModel;

public class PauseView implements View {


    private Scene scene;
    private StackPane rootPane;
    private Pane gamePane;

    public Button backToGame;

    public Text text;
    public Text pausetext;

    public PauseView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }

    //Sets up pauseButton window
    @Override
    public void setupGameWindow() {

        gamePane = new Pane();

        Text text = new Text("Rennspiel_PauseView");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));

        pausetext = new Text("Jetzt ist gerade Pause, drücke p um sie zu beenden");
        pausetext.setLayoutX(100);
        pausetext.setLayoutY(200);
        pausetext.setFont(new Font("Arial Black", 20));

        backToGame = new Button("Pause beenden");
        backToGame.setLayoutX(800);
        backToGame.setLayoutY(500);
        backToGame.setStyle("-fx-font-size: 40pt;");

        gamePane.getChildren().add(text);
        gamePane.getChildren().add(pausetext);
        gamePane.getChildren().add(backToGame);
        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {

        return scene;
    }

    public void render(GameModel model) {
    }
}

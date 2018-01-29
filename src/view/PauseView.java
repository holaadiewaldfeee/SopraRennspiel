package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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

        backToGame = new Button("Pause beenden");
        backToGame.setLayoutX(850);
        backToGame.setLayoutY(650);
        backToGame.setStyle("-fx-font-size: 40pt;");

        BorderPane paus = new BorderPane();
        ImageView imgView2 = new ImageView(new Image("resources/pauseView11l.png"));
        imgView2.setFitHeight(800);
        imgView2.setFitWidth(1300);
        paus.setCenter(imgView2);

        gamePane.getChildren().add(paus);
        gamePane.getChildren().add(backToGame);
        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {

        return scene;
    }

    public void render(GameModel model) {
    }
}

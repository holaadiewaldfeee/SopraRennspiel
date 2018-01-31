package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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



    /** Sets up start window
    @author
     */
    public void setupGameWindow() {

        gamePane = new Pane();

        startButton = new Button("START!");
        startButton.setLayoutX(850);
        startButton.setLayoutY(650);
        startButton.setStyle("-fx-font-size: 40pt;");

        BorderPane start = new BorderPane();
        ImageView imgView = new ImageView(new Image("resources/startViewImages/startView11l.png"));
        imgView.setFitHeight(800);
        imgView.setFitWidth(1300);
        start.setCenter(imgView);


        gamePane.getChildren().add(start);
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

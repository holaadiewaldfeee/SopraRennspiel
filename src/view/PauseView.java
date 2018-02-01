package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.GameModel;

/**
 * Pause view for the action loaded racing game!
 *
 * This view is being showed when the player is in the pause. The game will not continue to run.
 */
public class PauseView implements View {

    private Scene scene;
    private StackPane rootPane;
    private Pane gamePane;


    public PauseView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }

    //Sets up pause window
    @Override
    public void setupGameWindow() {

        gamePane = new Pane();

        BorderPane paus = new BorderPane();
        ImageView imgView2 = new ImageView(new Image("resources/startViewImages/pauseView11l.png"));
        imgView2.setFitHeight(800);
        imgView2.setFitWidth(1300);
        paus.setCenter(imgView2);

        gamePane.getChildren().add(paus);
        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {
        return scene;
    }

    public void render(GameModel model) {
    }
}

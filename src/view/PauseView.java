package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameModel;

public class PauseView implements View {


    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;
    private Pane gamePane;

    public Button backToStartView;
    public Button pause;

    public PauseView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        //setupGameWindow();
    }

    @Override
    public void setupGameWindow() {

    }

    //Sets up main pause window
   /* public void setupGameWindow() {

        gamePane = new Pane();

        Text text = new Text("Rennspiel_PauseViewlalalalal");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));

        //obligatory
        backToStartView = new Button("zurück ins Start Menu");
        backToStartView.setLayoutX(1000);
        backToStartView.setLayoutY(700);

        pause = new Button("hier pause maken");
        pause.setLayoutY(50);
        pause.setLayoutX(50);
        pause.setStyle("-fx-font-size: 20pt;");

        gamePane.getChildren().add(backToStartView);
        gamePane.getChildren().add(text);
        gamePane.getChildren().add(pause);
        rootPane.getChildren().add(gamePane);
    }*/

    public Scene getScene() {
        return scene;
    }

    public void render(GameModel model) {
    }
}

package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GameModel;

public class StartView implements View {


    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;
    private Pane gamePane;

    public StartView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }


    public Button backToStartView;
    public Button pause;


    //Sets up main start window
    public void setupGameWindow() {

        gamePane = new Pane();

        Text text = new Text("Rennspiel_StartView");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));

        //obligatory
        backToStartView = new Button("zurück ins Start Menu");
        backToStartView.setLayoutX(1000);
        backToStartView.setLayoutY(700);


        gamePane.getChildren().add(backToStartView);
        gamePane.getChildren().add(text);
        // gamePane.getChildren().add(pause);
        rootPane.getChildren().add(gamePane);


    }
    public Scene getScene() {
        return scene;
    }

    @Override
    public void render(GameModel model) {

    }
}

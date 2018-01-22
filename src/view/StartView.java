package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Car;

public class StartView implements MainView {

    //remember father view
    private static ViewManager father;
    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;

    // Buttons
    public Button startButton;
    public Button neuerFancyButton;


    public Scene getScene() {

        return scene;
    }

    @Override
    public void render(Car car) {

    }


    public StartView(ViewManager vm) {

        father = vm;
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setUpGameWindow();
        setUpInputHandler();
    }


    public void setUpGameWindow() {

        Pane gamePane = new Pane();

        Text text = new Text("Rennspiel_StartView");
        text.setLayoutX(400);
        text.setLayoutY(100);
        text.setFont(new Font("Arial Black", 50));
        gamePane.getChildren().add(text);

        startButton = new Button("START");
        startButton.setLayoutX(1050);
        startButton.setLayoutY(700);
        startButton.setStyle("-fx-font-size: 30pt;");
        gamePane.getChildren().add(startButton);

        rootPane.getChildren().add(gamePane);


    }


    private void setUpInputHandler() {

        startButton.setOnAction(e -> {
            father.changeScene(1);
            System.out.println("game started");
        });

    }
}
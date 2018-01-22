package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PauseView implements MainView{

    private static ViewManager father;
    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;

    // Buttons
    public Button pauseEnde;


    public Scene getScene() {

        return scene;
    }


    public PauseView(ViewManager vm) {

        father = vm;
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setUpGameWindow();
        setUpInputHandler();
    }


    public void setUpGameWindow() {

        Pane gamePane = new Pane();
        Text text = new Text("Pause");
        text.setLayoutX(400);
        text.setLayoutY(100);
        text.setFont(new Font("Arial Black", 50));
        gamePane.getChildren().add(text);

        pauseEnde = new Button("hier pause antimaken");
        pauseEnde.setLayoutY(50);
        pauseEnde.setLayoutX(50);
        pauseEnde.setStyle("-fx-font-size: 20pt;");
        gamePane.getChildren().add(pauseEnde);

        rootPane.getChildren().add(gamePane);

    }


    private void setUpInputHandler() {

        pauseEnde.setOnAction(e -> {
            father.changeScene(1);
            System.out.println("game started");
        });

    }

}

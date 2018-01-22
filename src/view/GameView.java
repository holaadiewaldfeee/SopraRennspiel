package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameView implements MainView {

    //remember father view
    private static ViewManager father;
    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;
    private Pane gamePane;

    //Buttons
    public Button backToStartView;
    public Button pause;


    public Scene getScene() {

        return scene;
    }

    public GameView(ViewManager vm) {

        father = vm;
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setUpGameWindow();
        setUpInputHandler();
    }

    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    //Sets up main game window
    public void setUpGameWindow() {

        gamePane = new Pane();

        Text text = new Text("Rennspiel_GameView");
        text.setLayoutX(400);
        text.setLayoutY(100);
        text.setFont(new Font("Arial Black", 50));


        //obligatory
        backToStartView = new Button("zurück ins Start Menu");
        backToStartView.setLayoutX(650);
        backToStartView.setLayoutY(700);


        pause = new Button("hier pause maken");
        pause.setLayoutY(50);
        pause.setLayoutX(50);
        pause.setStyle("-fx-font-size: 20pt;");




        //test
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(650);
        ellipse.setCenterY(400);
        // Radius X
        ellipse.setRadiusX(500);
        // Radius Y
        ellipse.setRadiusY(300);
        // Fill color.
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.GRAY);
        ellipse.setStrokeWidth(100);
        /*Ellipse ellipse2 = new Ellipse();
        ellipse2.setCenterX(650);
        ellipse2.setCenterY(400);
        // Radius X
        ellipse2.setRadiusX(450);
        // Radius Y
        ellipse2.setRadiusY(250);
        */
        BorderPane border = new BorderPane();
        ImageView imgView = new ImageView(new Image("resources/racetrack/land_grass04.png"));
        imgView.setScaleX(1200);
        imgView.setScaleY(800);
        border.setCenter(imgView);


        gamePane.getChildren().add(border);

        gamePane.getChildren().add(ellipse);
//        gamePane.getChildren().add(ellipse2);
        gamePane.getChildren().add(backToStartView);
        gamePane.getChildren().add(text);
        gamePane.getChildren().add(pause);
        rootPane.getChildren().add(gamePane);
    }


    private void setUpInputHandler() {

        backToStartView.setOnAction(e -> {
            father.changeScene(0);
            System.out.println("game started");
        });

        pause.setOnAction(e -> {
            father.changeScene(2);
            System.out.println("pause");
        });
    }
}
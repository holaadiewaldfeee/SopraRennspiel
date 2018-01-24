package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Car;
import model.GameModel;

public class GameView implements View {

    //The scene where all is stacked up
    private Scene scene;
    //Stackpane, where all dialogs are stacked
    private StackPane rootPane;
    private Pane gamePane;

    private Rectangle car;

    //Buttons
    public Button backToStartView;
    public Button pause;

    public GameView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }

    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    //Sets up main game window
    public void setupGameWindow() {

        gamePane = new Pane();

        Text text = new Text("Rennspiel_GameView");
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
        BorderPane border = new BorderPane();
        ImageView imgView = new ImageView(new Image("resources/racetrack/land_grass04.png"));
        imgView.setScaleX(1200);
        imgView.setScaleY(800);
        border.setCenter(imgView);


        gamePane.getChildren().add(border);

        gamePane.getChildren().add(ellipse);
        gamePane.getChildren().add(backToStartView);
        gamePane.getChildren().add(text);
        gamePane.getChildren().add(pause);

        car = new Rectangle(100,100,2.027,4.255);
        gamePane.getChildren().add(car);
        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {
        return scene;
    }

    public void render(GameModel m) {
        Car c = m.getCar();
        c.update();
        car.relocate(c.getX(), c.getY());
        car.setScaleX(c.getWidth());
        car.setScaleY(c.getHeight());
        car.setRotate(c.getDirection());
        car.setFill(new ImagePattern(c.getLook()));
    }
}
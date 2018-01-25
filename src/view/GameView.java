package view;

import javafx.scene.Scene;
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

    private Scene scene;
    private StackPane rootPane;
    private Pane gamePane;

    private Rectangle car;
    private Ellipse ellipse;

    public GameView() {
        rootPane = new StackPane();
        scene = new Scene(rootPane, 1300, 800);
        setupGameWindow();
    }

    /**
     * Sets up the main game window with the course as panebackground,
     * the car in the initial Position
     */
    //Sets up game window
    public void setupGameWindow() {


        //todo: Startlinie und checkpoint hinzufügen und obstacles
        gamePane = new Pane();

        Text text = new Text("Rennspiel_GameView");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));

        ellipse = new Ellipse();
        ellipse.setCenterX(650);
        ellipse.setCenterY(400);
        ellipse.setRadiusX(500);
        ellipse.setRadiusY(300);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.GRAY);
        //better strokeWidth then two ellipses;)
        ellipse.setStrokeWidth(100);
        BorderPane border = new BorderPane();
        ImageView imgView = new ImageView(new Image("resources/racetrack/land_grass04.png"));
        imgView.setScaleX(1200);
        imgView.setScaleY(800);
        border.setCenter(imgView);

        gamePane.getChildren().add(border);
        gamePane.getChildren().add(ellipse);
        gamePane.getChildren().add(text);

        car = new Rectangle(1,1);
        gamePane.getChildren().add(car);

        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {
        return scene;
    }

    //update car
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
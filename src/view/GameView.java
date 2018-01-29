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
import model.Obstacle;
import model.GameModel;


public class GameView implements View {

    private Scene scene;
    private StackPane rootPane;
    private Pane gamePane;

    private Rectangle car;
    private static Rectangle startLine;
    private static Rectangle startLineBounds;
    private static Rectangle checkLineBounds;
    private Rectangle checkpointLine;
    private static Ellipse ellipse;
    private static Ellipse ellipse2;
    private Ellipse ellipse3;

    public boolean checkPoint = false;

    private Text time;

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


        gamePane = new Pane();

        Text text = new Text("Rennspiel_GameView");
        text.setLayoutX(10);
        text.setLayoutY(20);
        text.setFont(new Font("Arial Black", 20));


        time = new Text("00:00");
        time.setLayoutX(100);
        time.setLayoutY(100);
        time.setFont(new Font("Arial Black", 30));


        ellipse = getEllipse();

        BorderPane border = new BorderPane();
        ImageView imgView = new ImageView(new Image("resources/racetrack/gras.png"));
        imgView.setX(1300);
        imgView.setY(800);
        imgView.setFitHeight(800);
        imgView.setFitWidth(1300);
        border.setCenter(imgView);

        //inner
        ellipse2 = getEllipse2();

        //outer
        ellipse3 = new Ellipse();
        ellipse3.setCenterX(650);
        ellipse3.setCenterY(400);
        ellipse3.setRadiusX(550);
        ellipse3.setRadiusY(350);
        ellipse3.setFill(Color.TRANSPARENT);
        ellipse3.setStroke(Color.WHITE);

        startLine = getStartLine();

        startLineBounds = getStartLineBounds();
        checkLineBounds = getCheckLineBounds();

        checkpointLine = new Rectangle(10, 100);
        checkpointLine.setLayoutX(650);
        checkpointLine.setLayoutY(650);
        if (checkPoint) {
            checkpointLine.setFill(Color.YELLOWGREEN);
        } else {
            checkpointLine.setFill(Color.INDIANRED);
        }

        gamePane.getChildren().add(border);
        gamePane.getChildren().add(ellipse);
        gamePane.getChildren().add(ellipse2);
        gamePane.getChildren().add(ellipse3);
        gamePane.getChildren().add(text);
        gamePane.getChildren().add(time);
        gamePane.getChildren().add(startLine);
        gamePane.getChildren().add(startLineBounds);
        gamePane.getChildren().add(checkLineBounds);
        gamePane.getChildren().add(checkpointLine);

        //obs generate

        for (Obstacle o : GameModel.getObstacles()) {
            Rectangle rec = new Rectangle(o.getX(), o.getY(), o.getWidth(), o.getHeight());
            //System.out.println(rec);
            rec.setFill(new ImagePattern(o.getLook()));
            gamePane.getChildren().add(rec);
        }


        car = new Rectangle(1, 1);
        gamePane.getChildren().add(car);

        rootPane.getChildren().add(gamePane);
    }

    public Scene getScene() {
        return scene;
    }

    //update car
    //todo: update obstacles
    public void render(GameModel m) {
        Car c = m.getCar();
        c.update();
        car.relocate(c.getX(), c.getY());
        car.setScaleX(c.getWidth());
        car.setScaleY(c.getHeight());
        car.setRotate(c.getDirection());
        car.setFill(new ImagePattern(c.getLook()));

        int i = 0;
        for (Obstacle ob : GameModel.getObstacles()) {
            Obstacle o = m.getObstacles().get(i);
            i++;

        }

        int seconds = (int) GameModel.roundTime % 60;
        int minute = (int) (GameModel.roundTime / 60);
        time.setText(String.format("%d:%d", minute, seconds));
    }

    public static Ellipse getEllipse() {
        ellipse = new Ellipse();
        ellipse.setCenterX(650);
        ellipse.setCenterY(400);
        ellipse.setRadiusX(500);
        ellipse.setRadiusY(300);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.GRAY);
        //better strokeWidth then two ellipses;)
        ellipse.setStrokeWidth(100);
        return ellipse;
    }

    public static Ellipse getEllipse2() {
        ellipse2 = new Ellipse();
        ellipse2.setCenterX(650);
        ellipse2.setCenterY(400);
        ellipse2.setRadiusX(450);
        ellipse2.setRadiusY(250);
        ellipse2.setFill(Color.TRANSPARENT);
        ellipse2.setStroke(Color.WHITE);
        return ellipse2;
    }

    public static Rectangle getStartLineBounds() {
        startLineBounds = new Rectangle(525, 50, 150, 100);
        startLineBounds.setFill(Color.TRANSPARENT);
        return startLineBounds;
    }

    public static Rectangle getCheckLineBounds() {
        checkLineBounds = new Rectangle(525, 650, 200, 100);
        checkLineBounds.setFill(Color.TRANSPARENT);
        return checkLineBounds;
    }

    public static Rectangle getStartLine() {
        startLine = new Rectangle(10, 100);
        startLine.setLayoutX(650);
        startLine.setLayoutY(50);
        startLine.setFill(Color.WHITESMOKE);
        startLine.setFill(new ImagePattern(new Image("resources/lines/barrier_white.png")));
        return startLine;
    }
}
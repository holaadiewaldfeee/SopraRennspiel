package controller;


import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.Car;
import model.GameModel;
import model.Obstacle;
import view.GameView;
import view.View;

import java.util.ArrayList;

public class GameController implements Controller {

    private GameView gameView;
    private static GameModel gameModel;
    private final static int ROTATEINTENSITY = 3;
    ArrayList<String> input = new ArrayList<>();

    public GameController(GameModel model) {
        gameView = new GameView();
        gameModel = model;
        setupInteraction();
    }

    @Override
    public void update() {

        Car brumm = GameModel.getCar();

        Rectangle sL = GameView.getStartLine();
        double tempX = sL.getLayoutX() + sL.getWidth() / 2;
        double tempY = sL.getLayoutY() + sL.getHeight() * 1.2;
        if (Math.abs(brumm.getMidPoint().getX() - tempX) < 5 &&
                brumm.getMidPoint().getY() > sL.getLayoutY() &&
                brumm.getMidPoint().getY() < tempY) {
            if (GameModel.checkpointPassed && GameModel.roundStarted) {
                GameView.wonPane.setVisible(true);
                GameModel.stopRound();

                int seconds = (int) GameModel.roundTime % 60;
                int minute = (int) (GameModel.roundTime / 60);
                Text time = new Text(String.format("%02d:%02d", minute, seconds));

                time.setLayoutX(450);
                time.setLayoutY(200);
                time.setStyle("-fx-font-size: 40pt;");
                GameView.wonPane.getChildren().add(time);
            } else {
                GameModel.startRound();
            }
        }
        //todo: hier versuchen checkpoint linie farbe zu ändern
        Rectangle cL = GameView.getCheckLine();
        double tempCX = cL.getLayoutX() + cL.getWidth() / 2;
        double tempCY = cL.getLayoutY() + cL.getHeight() * 1.2;
        if (Math.abs(brumm.getMidPoint().getX() - tempCX) < 5 &&
                brumm.getMidPoint().getY() > cL.getLayoutY() &&
                brumm.getMidPoint().getY() < tempCY &&
                GameModel.roundStarted) {
            GameModel.checkpointPassed = true;
        }

        Ellipse ell = GameView.getEllipse();
        Ellipse ell2 = GameView.getEllipse2();
        brumm.onAsphalt = ell.contains(brumm.getMidPoint().getX(), brumm.getMidPoint().getY()) &&
                !(ell2.contains(brumm.getMidPoint().getX(), brumm.getMidPoint().getY()));

        System.out.println(brumm.getSpeed());

        for (Obstacle ob : GameModel.getObstacles()) {
            Rectangle t = new Rectangle(ob.getX(), ob.getY(), ob.getWidth(), ob.getHeight());
            Rectangle t2 = new Rectangle(brumm.getX() - brumm.getWidth() / 2,
                    brumm.getY() - brumm.getHeight() / 2,
                    brumm.getWidth(),
                    brumm.getHeight());
            t2.setRotate(brumm.getDirection());
            Shape s = Shape.intersect(t, t2);
            if (!s.getLayoutBounds().isEmpty()) {
                if (Math.abs(brumm.getSpeed()) > brumm.crashesAt) {
                    GameView.lostPane.setVisible(true);
                }
                GameModel.getCar().crash();
            }
            t.getBoundsInParent();
            // s.setFill(Color.MAGENTA);
            // GameView.debugPane.getChildren().add(s);
        }

        Rectangle auto = new Rectangle(brumm.getX(), brumm.getY(), brumm.getWidth(), brumm.getHeight());
        auto.setFill(Color.TRANSPARENT);
        auto.setRotate(brumm.getDirection());
        GameView.debugPane.getChildren().add(auto);
        gameView.render(gameModel);
    }


    @Override
    public void updateKeys() {
        if (input.contains("LEFT")) {
            gameModel.getCar().rotate(-ROTATEINTENSITY);
            //System.out.println("left");
        }
        if (input.contains("RIGHT")) {
            gameModel.getCar().rotate(ROTATEINTENSITY);
            //System.out.println("right");

        }
        gameModel.getCar().isAccelerating(0);
        if (input.contains("UP")) {
            gameModel.getCar().isAccelerating(-1);
            //System.out.println("up");
        } else if (input.contains("DOWN")) {
            gameModel.getCar().isAccelerating(1);
            //System.out.println("down");
        }
    }

    @Override
    public void setupInteraction() {

        gameView.getScene().setOnKeyPressed(
                e -> {
                    if (e.getCode() == KeyCode.P) {
                        //System.out.println("pause Game");
                        MainController.changeController(2);
                        GameModel.stopRound();
                    }
                    if (e.getCode() == KeyCode.R) {
                        newGame();
                    }
                    if (e.getCode() == KeyCode.ESCAPE) {
                        System.out.println("hadebye");
                        System.exit(0);
                    }
                    String code = e.getCode().toString();

                    //only add once
                    if (!input.contains(code))
                        input.add(code);
                });

        gameView.getScene().setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove(code);
                });

        gameView.startAfterWon.setOnAction(e -> {
            GameView.wonPane.setVisible(false);
            newGame();
        });

        gameView.startAfterLost.setOnAction(e -> {
            GameView.lostPane.setVisible(false);
            newGame();
        });
    }


    //todo: hier wollte ich das reset dings machen
    private void newGame() {
/*        controllerList.remove(1);
        controllerList.remove(2);
        controllerList.add(new GameController(gameModel));
        controllerList.add(new PauseController(gameModel));*/
        GameModel.getCar().sound.stopSound();
        GameModel.checkpointPassed = false;
        GameModel.initializeCar();
        GameModel.initializeObstacles();
        GameModel.resetTime();

        gameView.setupGameWindow();
        setupInteraction();
    }

    public View getView() {
        return this.gameView;
    }

}

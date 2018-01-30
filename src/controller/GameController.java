package controller;


import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import model.Car;
import model.GameModel;
import view.GameView;
import view.View;
import java.util.ArrayList;

public class GameController implements Controller {

    private GameView gameView;
    private static GameModel gameModel;
    private final static int ROTATEINTENSITY = 1;
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
        double tX = sL.getLayoutX()+ sL.getWidth()/2;
        double tY = sL.getLayoutY() + sL.getHeight();
        if(Math.abs(brumm.getMidPoint().getX() - tX) < 5 &&
                brumm.getMidPoint().getY() > sL.getY() &&
                brumm.getMidPoint().getY() < tY){
            GameModel.startRound();
        }
        //todo: hier versuchen checkpoint linie farbe zu ändern
       /* Rectangle cL = GameView.getCheckLine();
        double cX = cL.getLayoutX()+ cL.getWidth()/2;
        double cY = cL.getLayoutY() + cL.getHeight();
        if(Math.abs(brumm.getMidPoint().getX() - cX) < 5 &&
                brumm.getMidPoint().getY() > cL.getY() &&
                brumm.getMidPoint().getY() < cY){
           GameView.checkPoint = true;
           GameView.changeCheckpoint();
        }*/
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
        if (input.contains("UP")) {
            gameModel.getCar().setSpeed(-1);
            //System.out.println("up");
        }
        if (input.contains("DOWN")) {
            gameModel.getCar().setSpeed(1);
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
                    }
                    if (e.getCode() == KeyCode.R) {
                        //System.out.println("reset Game");
                        newGame();
                        gameView.setupGameWindow();
                        Car.sound.pauseSound();
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

    }


    //todo: hier wollte ich das reset dings machen
    private void newGame() {
/*        controllerList.remove(1);
        controllerList.remove(2);
        controllerList.add(new GameController(gameModel));
        controllerList.add(new PauseController(gameModel));*/
        GameModel.initializeCar();
        GameModel.initializeObstacles();
        GameModel.resetTime();

    }

    public View getView() {
        return this.gameView;
    }

}

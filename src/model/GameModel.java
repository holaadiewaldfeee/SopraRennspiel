package model;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import view.GameView;

import java.util.ArrayList;

/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {

    private static Car car;
    private static ArrayList<Obstacle> obstaclesList = new ArrayList<>(Obstacle.MAXOBS);
    static Ellipse ell = GameView.getEllipse();
    static Ellipse ell2 = GameView.getEllipse2();
    static Rectangle sLB = GameView.getStartLineBounds();
    static Rectangle cLB = GameView.getCheckLineBounds();

    public static double roundTime = 0;
    public static boolean roundStarted = false;
    public static boolean checkpointPassed = false;
    public static boolean isPaused = false;

    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {

        //initialize Car
        car = initializeCar();
        initializeObstacles();
    }

    /**
     * Initializes a car with the initial values
     *
     * @return the initialized car
     */
    public static Car initializeCar() {
        //initialize a new car and give it the init values set in the static variables
        car = new Car();
        car.setUpLookAndFeel();
        return car;
    }

    public static void initializeObstacles() {
        obstaclesList.clear();
        //create obstacles
        do {
            Obstacle o = new Obstacle();
            o.setLookImage();
            //System.out.println(o);
            boolean genugabstand = true;
            for (Obstacle oTemp : obstaclesList) {
                double abstand = Math.sqrt(Math.pow(o.getX() - oTemp.getX(), 2) + Math.pow(o.getY() - oTemp.getY(), 2));
                if (abstand < 100) {
                    genugabstand = false;
                    break;
                }
            }

            boolean isInStart = sLB.contains(o.getX(), o.getY());
            boolean isInCheck = cLB.contains(o.getX(), o.getY());

            if (!isInCheck && !isInStart && ell.contains(o.getX(), o.getY()) && !(ell2.contains(o.getX(), o.getY())) && genugabstand) {
                obstaclesList.add(o);
            }

        } while (obstaclesList.size() < Obstacle.MAXOBS);
    }


    public static void update(double time) {
        roundTime += roundStarted ? time : 0;
       if(!isPaused) car.update(time);
    }


    public static Car getCar() {

        return car;
    }

    public static ArrayList<Obstacle> getObstacles() {
        return obstaclesList;
    }


    public static void resetTime() {
        roundStarted = false;
        roundTime = 0;
    }

    public static void startRound() {
        roundStarted = true;
    }

    public static void stopRound() {
        roundStarted = false;
    }

}
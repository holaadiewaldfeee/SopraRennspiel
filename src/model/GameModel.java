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


    /**
     * The car that is driven on the racetrack
     */
    private static Car car;
    private static ArrayList<Obstacle> obstaclesList = new ArrayList<>(Obstacle.MAXOBS);
    static Ellipse ell = GameView.getEllipse();
    static Ellipse ell2 = GameView.getEllipse2();
    static Rectangle sLB = GameView.getStartLineBounds();
    static Rectangle cLB = GameView.getCheckLineBounds();
    public static long roundTime = 0;

    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {

        //initialize Car, default data in GameView
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
        return car;
    }

    public static void initializeObstacles() {
        obstaclesList.clear();
        //create obstacles
        do {
            Obstacle o = new Obstacle();
            System.out.println(o);
            //todo: abstand zu anderen obstacles
            boolean genugabstand = true;
            for (Obstacle o_t : obstaclesList) {
                double abstand = Math.sqrt(Math.pow(o.getX() - o_t.getX(), 2) + Math.pow(o.getY() - o_t.getY(), 2));
                if (abstand < 100) {
                    genugabstand = false;
                    break;
                }
            }

            boolean isInStart = sLB.contains(o.getX(), o.getY());
            boolean isInCheck = cLB.contains(o.getX(), o.getY());
            //
            if (!isInCheck && !isInStart && ell.contains(o.getX(), o.getY()) && !(ell2.contains(o.getX(), o.getY())) && genugabstand) {
                obstaclesList.add(o);
                //System.out.println("-------------------");
            }


        } while (obstaclesList.size() < Obstacle.MAXOBS);
        //System.out.println("new ostacles");
    }


    public static void update(double time) {
        roundTime += time;
    }


    public static Car getCar() {

        return car;
    }

    public static ArrayList<Obstacle> getObstacles() {

        return obstaclesList;
    }
}

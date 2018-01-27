package model;

import javafx.scene.shape.Ellipse;
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
    private Car car;
    private static ArrayList<Obstacle> obstaclesList = new ArrayList<>(Obstacle.MAXOBS);
    Ellipse ell = GameView.getEllipse();
    Ellipse ell2 = GameView.getEllipse2();

    /**
     * Creates a gameModel, that handles most of the actions
     */
    public GameModel() {

        //initialize Car, default data in GameView
        car = initializeCar();
        initializeObstacles();
        //System.out.println(obstaclesList);
    }

    /**
     * Initializes a car with the initial values
     *
     * @return the initialized car
     */
    private Car initializeCar() {

        //initialize a new car and give it the init values set in the static variables
        car = new Car();
        return car;
    }

    private void initializeObstacles(){

        //create obstacles
        do {
            Obstacle o = new Obstacle();
            System.out.println(o);
            if (ell.contains(o.getX(), o.getY()) && !(ell2.contains(o.getX(), o.getY()))){
                obstaclesList.add(o);
                System.out.println("-------------------_");
            }


        }while(obstaclesList.size() < Obstacle.MAXOBS);

    }


    public void update(){
        /*for (Obstacle o : obstaclesList) {
            o.update();
        }
        this.car.update();*/
    }


    public Car getCar() {

        return car;
    }

    public static ArrayList<Obstacle> getObstacles() {

        return obstaclesList;
    }
}

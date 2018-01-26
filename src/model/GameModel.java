package model;

import java.util.ArrayList;

/**
 * The GameModel saves data about the game, including the racecar.
 * It handles most of the calculations for the racegame.
 */
public class GameModel {

    //max occurences obstacles
    public final static int MAXOBS = 10;

    /**
     * The car that is driven on the racetrack
     */
    private Car car;
    private ArrayList<Obstacle> obstaclesList = new ArrayList<>();

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


        do {
            Obstacle o = new Obstacle();
            System.out.println(o);
            obstaclesList.add(o);

        }while(obstaclesList.size() <= MAXOBS);

    }


    public void update(){
        for (Obstacle o : obstaclesList) {
            o.update();
        }
        this.car.update();
    }


    public Car getCar() {

        return car;
    }

    public ArrayList<Obstacle> getObstacles() {

        return obstaclesList;
    }
}

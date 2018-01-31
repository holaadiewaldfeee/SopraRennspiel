package model;

import junit.framework.TestCase;

import java.util.Random;

public class ObstacleTest extends TestCase {

    public void testSize() {

        Obstacle testObs = new Obstacle();
        assertEquals(30.0, testObs.getWidth());
        assertEquals(30.0, testObs.getHeight());
    }

    public void testPosition(){
        Obstacle testObs = new Obstacle();
        //for (int i = 0; i <= 1300 ; i++){
            assertEquals((new Random()).nextDouble() * 1300, testObs.getX());
        //}
    }
}
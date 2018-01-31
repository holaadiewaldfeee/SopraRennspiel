package model;

import javafx.scene.image.Image;
import junit.framework.TestCase;

public class CarTest extends TestCase {
    public void testForward() {
        Car testCar = new Car();
        testCar.isAccelerating(-1);
        //for (float f = 0.005f; f <= 1; f += 0.005) {
            testCar.accelerate(0.005);
            assertEquals(-0.017600000262260437, testCar.getSpeed());
        //}
    }

    public void testBackwards() {
        Car testCar = new Car();
        testCar.isAccelerating(1);
        testCar.accelerate(0.005);
        assertEquals(0.017600000262260437, testCar.getSpeed());
    }

    public void testResistance() {
        Car testCar = new Car();
        testCar.isAccelerating(-1);
        testCar.update(0.5);
        assertEquals(-1.5719460818108728, testCar.getSpeed());
    }

    public void testRotation() {
        Car testCar = new Car();
        testCar.isAccelerating(-1);
        testCar.accelerate(0.005);
        double j = 90;
        for (int i = 0; i <= 360; i++) {
            testCar.rotate(i);
            assertEquals(j+=i, testCar.getDirection());
        }
    }

    public void testCrash(){
        Car testCar = new Car();
        testCar.isAccelerating(-1);
        testCar.accelerate(0.005);
        testCar.crash();
        assertEquals(0.0d, testCar.getSpeed());
    }

    /*public void testLook(){
        Car testCar = new Car();
        testCar.setUpLookAndFeel();
        assertSame(new Image("resources/car/car_yellow_1.png"), testCar.getLook());
    }*/

    public void testMidPoint(){
        Car testCar = new Car();
        assertEquals(new Vector(615.0d + 2.027 * 10.0d /2, 100.0d+4.255 * 10.0d/2), testCar.getMidPoint());
    }
}
package model;


import javafx.scene.image.Image;

/**
 * Class car represents the race-car in the race game.
 */
public class Car {

    private int mass;
    private float speed;
    private float direction;
    private Vector position;
    private Vector size;

    private Image look;

    public Car() {
        mass = 1000;
        speed = 0.0f;
        direction = 0;
        position = new Vector(500.0d, 300.0d);
        size = new Vector(5.0d, 5.0d);
        look = new Image("resources/car/car_black_1.png");

    }

    public void update() {
        if (Math.abs(speed) > 0.005f) {
            speed *= 0.99f;
        }
        this.position.x += this.speed;
    }

    public void setSpeed(float x) {
        this.speed = x;
    }

    public void rotate(float x) {
        this.direction += x;
    }

    public double getSpeed() {
        return speed;
    }

    public Vector getPosition() {
        return position;
    }

    public double getX(){
        return this.position.getX();
    }

    public double getY(){
        return this.position.getY();
    }

    public double getWidth(){
        return this.size.getX();
    }

    public double getHeight(){
        return this.size.getY();
    }

    public Image getLook() {
        return look;
    }

    public double getDirection() {
        return this.direction;
    }
}

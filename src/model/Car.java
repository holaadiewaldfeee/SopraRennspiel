package model;


import javafx.scene.image.Image;

/**
 * Class car represents the race-car in the race game.
 */
public class Car {

    private int mass;
    private Vector2 speed;
    private Vector2 position;

    private javafx.scene.image.Image look;

    public Car() {
        mass = 1000;
        speed = new Vector2();
        position = new Vector2(500, 300);
        look = new javafx.scene.image.Image("resources/car/car_black_1.png");

    }

    void update() {
        if (speed.length() > 0.005f) {
            speed.scale(0.99f);
        }
        this.position.x += this.speed.x;
        this.position.y += this.speed.y;
    }

    public void setSpeed(float x, float y) {
        this.speed.x = x;
        this.speed.y = y;
    }

    public String getSpeed() {
        return speed.x + " " + speed.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX(){
        return this.position.getX();
    }

    public float getY(){
        return this.position.getY();
    }

    public Image getLook() {
        return look;
    }
}

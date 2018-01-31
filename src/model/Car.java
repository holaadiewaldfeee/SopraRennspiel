package model;


import javafx.scene.image.Image;

/**
 * Class car represents the race-car in the race game.
 */
public class Car {

    /// Private final fields
    private final float MASS = 1200.0f;
    private final Vector size;
    private final double ROLLINGRESISTANCESTREET = (0.015f * 9.81f); //Rollwiederstand straße
    private final double ROLLINGRESISTANCEGRAS = (0.05f * 9.81f); // Rollwiderstand gras
    private final double MAXSPEEDFORDAMAGE = 100000.0f;


    /// private car parameters
    private double speed;
    private int isAccelerating;
    private float direction;
    private Vector position;
    private Image look;
    //v soll speed sein aber sonst wird null
    //private float v = 1.0f;

    public static Sound sound;
    public static boolean damage = false;
    public static boolean onAsphalt = true;

    Car() {
        speed = 0.0f;
        direction = 90;
        position = new Vector(615.0d, 100.0d);
        size = new Vector(2.027 * 10.0d, 4.255 * 10.0d);
        //        if (!damage) {
        look = new Image("resources/car/car_yellow_1.png");
        //        } else {
        //todo: ein damage car basteln png aber nicht hier das image changen das wäre doof sondern wos upgedated wird
        //   look = new Image("resources/car/car_black_1.png");
        //        }

        sound = new Sound("src/resources/sound/345925__1histori__car-engine.wav");
        //sound = new Sound("src/resources/sound/Game_Over.wav");
    }

    public void update(double deltaTime) {
        accelerate(deltaTime);
        calculateResistance(deltaTime);
        playSound();

        this.position.x += Math.cos(Math.toRadians(this.direction) + Math.PI / 2) * this.speed;
        this.position.y += Math.sin(Math.toRadians(this.direction) + Math.PI / 2) * this.speed;

        //not out of stage
        if (this.position.x >= 1300) {
            this.position.x = 1299;
        }
        if (this.position.x <= 0) {
            this.position.x = 0;
        }
        if (this.position.y >= 800) {
            this.position.y = 799;
        }
        if (this.position.y <= 0) {
            this.position.y = 0;
        }
    }

    private void accelerate(double deltaTime) {
        // The acceleration of a golf 7 is ~3.2 m/s²
        if (speed >= -10.0 && this.isAccelerating != 0) {
            speed = speed + isAccelerating * 3.2f * 20 * deltaTime;
        }
    }

    public void isAccelerating(int isIt) {
        this.isAccelerating = isIt;
    }

    private void calculateResistance(double deltaTime) {
        if (onAsphalt) {
            speed = speed * (1 - ROLLINGRESISTANCESTREET * deltaTime);
        } else {
            speed = speed * (1 - ROLLINGRESISTANCEGRAS * deltaTime);
        }
        double f_resist = 0.28f * 2.19f * (0.5f * 1.2041f) * (Math.pow((float) speed, 2.0f));
        speed = speed * (1 - f_resist * deltaTime);
    }

    private void playSound() {
        if (Math.abs(speed) >= 0.05) {
            sound.playSound();
        } else {
            sound.pauseSound();
        }
    }

    public void rotate(float x) {
        if (Math.abs(getSpeed()) > 0.005) {
            this.direction += x;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
    }

    public double getWidth() {
        return this.size.getX();
    }

    public double getHeight() {
        return this.size.getY();
    }

    public Image getLook() {
        return look;
    }

    public double getDirection() {
        return this.direction;
    }

    public Sound getSound() {
        return sound;
    }

    public Vector getMidPoint() {
        return new Vector(position.x + size.x / 2, position.y + size.y / 2);
    }
}

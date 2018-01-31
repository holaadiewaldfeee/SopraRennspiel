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
        look = new Image("resources/car/car_yellow_1.png");

        sound = new Sound("src/resources/sound/345925__1histori__car-engine.wav");
        //sound = new Sound("src/resources/sound/Game_Over.wav");
    }

    public void update(double deltaTime) {
        accelerate(deltaTime);
        calculateResistance(deltaTime);
        if (Math.abs(speed) <= 0.05) speed = 0;
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
        if (this.isAccelerating != 0) {
            speed = speed + isAccelerating * 3.2f * 1.1 * deltaTime;

            // clamp the speed to [-10: 10]
            speed = Math.max(-8, Math.min(8, speed));
        }

    }

    public void isAccelerating(int isIt) {
        this.isAccelerating = isIt;
    }

    private void calculateResistance(double deltaTime) {
        double verlangsamung = 1.0d;
        if (onAsphalt) {
            verlangsamung *= (1 - ROLLINGRESISTANCESTREET * deltaTime);
        } else {
            verlangsamung *= (1 - ROLLINGRESISTANCEGRAS * deltaTime);
        }
        double s_temp = Math.abs(speed);
        double f_resist = 0.28f * 219 * (0.5f * 1.2041f) * (Math.pow(s_temp, 1.5f)) / MASS;
        verlangsamung *= (1 - f_resist * deltaTime);

        speed *= verlangsamung;
    }

    private void playSound() {
        // System.out.println("sound is " + (Math.abs(speed) >= 0.05 ? "playing" : "not Playing"));
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

    public void setSpeed(double speed) {
        this.speed = speed;
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

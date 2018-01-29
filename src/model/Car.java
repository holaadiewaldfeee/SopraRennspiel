package model;


import application.Sound;
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
    //v soll speed sien aber sonst wird null
    private float v = 1.0f;
    public static boolean damage = false;

    private final float ROLLINGRESISTANCESTREET = (0.015f * 981.0f) * 1200.0f; //Rollwiederstand straße
    private final float ROLLINGRESISTANCEGRAS = (0.05f * 981.0f) * 1200.0f; // Rollwiderstand gras
    private final float MAXSPEEDFORDAMAGE = 100000.0f;
    // private final float FLOWRESISTANCE = 0.28f * 2.19f * (0.5f*1.2041f) * (Math.pow( (float) v , (float) 2.0f));


    //mal das gewicht beim rollwiderstand und wenn luft dabei dann alles geteilt durch masse
    public static Sound sound;

    public Car() {
        mass = 1200;
        speed = 0.0f;
        direction = 90;
        position = new Vector(615.0d, 100.0d);
        size = new Vector(2.027 * 10.0d, 4.255 * 10.0d);
        if (!damage) {
            look = new Image("resources/car/car_yellow_1.png");
        } else {
            //todo: ein damage car basteln png
            look = new Image("resources/car/car_black_1.png");
        }

        sound = new Sound("src/resources/sound/345925__1histori__car-engine.wav");
    }

    public void update() {
        if (Math.abs(speed) > 0.005f) {
            speed *= 0.99f;
            sound.playSound();
        } else {
            speed = 0;
            sound.pauseSound();
        }
        this.position.x += Math.cos(Math.toRadians(this.direction) + Math.PI / 2) * this.speed;
        this.position.y += Math.sin(Math.toRadians(this.direction) + Math.PI / 2) * this.speed;
    }

    public void setSpeed(float x) {
        this.speed = x;
    }

    public void rotate(float x) {
        if (Math.abs(getSpeed()) > 0.005) {
            this.direction += x;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public Vector getPosition() {
        return position;
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

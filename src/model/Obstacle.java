package model;

import javafx.scene.image.Image;
import java.util.Random;

/**
 * Class obstacle represents the obstacles in the race game.
 */
public class Obstacle {


    public final static int MAXOBS = 10;
    private Vector position;
    private Vector size;
    private Image look;

    public Obstacle() {

        Random ran = new Random();
        double x = ran.nextDouble() * 1300;
        double y = ran.nextDouble() * 800;

        //Ellipse ell = GameView.getEllipse();

        /*Random rand = new Random();

        // Very random, such coincidence, wow
        int maxRadius = 450;
        int minRadius = 350;
        int a = rand.nextInt((maxRadius - minRadius) + 1) + minRadius;
        int b = rand.nextInt((maxRadius - minRadius) + 1) + minRadius;

        // Just an arbitrary half circle, for testing
        double maxAngle = Math.PI * 2;
        double minAngle = 0;
        double randomAngle = minAngle + (maxAngle - minAngle) * rand.nextDouble();

        // Calculate a random x value on a ellipse with given radius
        // a = the horizontal radius of the ellipse
        // b = the vertical radius of the ellipse
        int sing = -Math.PI / 2 < randomAngle && randomAngle < Math.PI / 2 ? -1 : 1;
        //double x = sing * a * b / Math.sqrt(Math.pow(b, 2) + Math.pow(a, 2) * Math.pow(Math.tan(randomAngle), 2)); // sign must be positive if −PI/2 < θ < PI/2; negative if otherwise
        //double y = sing * a * b / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) / Math.pow(Math.tan(randomAngle), 2)); // sign must be positive if −PI/2 < θ < PI/2; negative if otherwise

        double x = b * Math.cos(randomAngle) + 650;
        double y = a * Math.sin(randomAngle) + 400;*/

        this.position = new Vector(x, y);
        size = new Vector(30, 30);
        look = new Image("resources/obstacle/cone_straight.png");
    }

    public void update() {}

    public void setPosition(Vector position) {
        this.position = position;
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

}

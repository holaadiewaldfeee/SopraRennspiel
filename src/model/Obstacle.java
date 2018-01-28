package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import view.GameView;

import java.util.Random;

/**
 * Class obstacle represents the obstacles in the race game.
 */
public class Obstacle {


    public final static int MAXOBS = 20;


    private Vector position;
    private Vector size;
    private Image look;

    public Obstacle() {

        Random ran = new Random();
        double x = ran.nextDouble()*1300;
        double y = ran.nextDouble()*800;

        Vector pos = new Vector(x,y);
        //Ellipse ell = GameView.getEllipse();


        this.position = pos;
        size = new Vector(30,30);
        look = new Image("resources/obstacle/cone_straight.png");
    }

    public void update() {

    }

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

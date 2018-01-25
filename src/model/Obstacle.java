package model;

import javafx.scene.image.Image;

/**
 * Class obstacle represents the obstacles in the race game.
 */
public class Obstacle {




    private Vector position;
    private Vector size;
    private Image look;

    public Obstacle(Vector vector) {
            this.position = vector;
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

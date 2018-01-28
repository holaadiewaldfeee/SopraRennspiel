package model;

public class Vector {
    double x;
    double y;

    Vector(){
    }

    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    void scale(double s){
        this.x *= s;
        this.y *= s;
    }
}

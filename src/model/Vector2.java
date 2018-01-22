package model;

class Vector2 {
    float x;
    float y;

    Vector2() {
        this.x = 0;
        this.y = 0;
    }

    Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    void scale(float s){
        this.x *= s;
        this.y *= s;
    }
}

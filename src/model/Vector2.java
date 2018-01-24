package model;

class Vector2<T extends Double, V extends Double> {
    T x;
    V y;

    Vector2(){
    }

    Vector2(T x, V y) {
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return this.x;
    }

    public V getY(){
        return this.y;
    }

    double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /*void scale(float s){
        this.x *= s;
        this.y *= s;
    }*/
}

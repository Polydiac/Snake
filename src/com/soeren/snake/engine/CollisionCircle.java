package com.soeren.snake.engine;

import com.soeren.snake.engine.util.Vector2D;

public class CollisionCircle implements CollisionObject {
    double radius;
    Vector2D pos;

    public CollisionCircle(double radius, Vector2D pos) {
        this.radius = radius;
        this.pos = pos;
    }

    public void move(Vector2D pos){
        this.pos = pos;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    @Override
    public boolean didCollide(CollisionObject obj) {
        if(obj instanceof CollisionCircle){
            double distance = Math.sqrt(Math.pow(((CollisionCircle) obj).pos.x - pos.x, 2) + Math.pow(((CollisionCircle) obj).pos.y - pos.y, 2));
            return (((CollisionCircle) obj).radius + radius >= distance);
        } else {
            return false;
        }
    }
}

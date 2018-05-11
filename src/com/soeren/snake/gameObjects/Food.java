package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.CollisionHandler;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.Buntstift;

import java.awt.*;

public class Food implements Updatable, Drawable, Collidable {

    Vector2D pos;
    double radius;
    Buntstift st;
    Color color;
    boolean eaten = false;

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public Food(Vector2D pos) {
        this(pos, 10, Color.RED);
    }

    public Food(Vector2D pos, double radius, Color color) {

        this.pos = pos;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(long frame) {
        st.hoch();
        st.bewegeBis(pos.x, pos.y);
        st.zeichneKreis(radius);
    }

    @Override
    public void delete(long frame) {
        st.radiere();
        draw(frame);
        st.normal();
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public void init() {
        if(!GameThread.isServer){
            st = new Buntstift(DrawingHandler.bs);
            st.setzeFuellMuster(1);
            st.setzeFarbe(color);
        }
        CollisionHandler.registerCollidable(this);
    }

    @Override
    public void update(long frame) {

    }

    @Override
    public CollisionObject getCollisionObject() {
        return new CollisionCircle(radius, pos);
    }

    @Override
    public void collidedWith(Collidable obj) {
        if(obj instanceof SnakeNode){
            eaten = true;
            if(!GameThread.isServer) {
                delete(GameThread.getFrame());
                GameThread.removeObject(this);
            }
        }
    }
}

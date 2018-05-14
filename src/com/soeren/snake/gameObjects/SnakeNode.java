package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.CollisionHandler;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author 
 * @version 
 */
public class SnakeNode implements Updatable, Drawable, Animatable, Collidable
{
    Vector2D pos;
    Vector2D direction;
    Buntstift st;
    double radius;
    Color color;
    ArrayList<Vector2D> movesLeft;
    SnakeNode nextNode = null;
    Snake parent;
    long offset;
    boolean isFirst;
    CollisionCircle ccircle;

    public SnakeNode(Vector2D startPos, double pRadius, Color pcolor, double offset, Snake pparent, boolean isFirst)
    {
        this.isFirst = isFirst;
        parent = pparent;
        radius = pRadius;
        pos = startPos;
        color = pcolor;
        movesLeft = new ArrayList<>();
        this.offset = Math.round(offset);
        movesLeft.add(startPos);
        /*for (int i = 0; i < Math.round(offset); i++) {
            movesLeft.add(startPos);
        }*/

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
        return 2;
    }

    @Override
    public void init() {
        if(!GameThread.isServer){
            st = new Buntstift();
            st.setzeFarbe(color);
            st.setzeFuellMuster(1);
        }
        ccircle = new CollisionCircle(radius, pos);
        CollisionHandler.registerCollidable(this);
    }

    @Override
    public void update(long frame) {
        pos = movesLeft.remove(0);
        ccircle.move(pos);
    }


    @Override
    public void resize(float scalar) {

    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void move(Vector2D pos) {

    }

    public void propagateMove(Vector2D newPos){
        movesLeft.add(newPos);
        if(offset > 0){
            movesLeft.add(newPos);
            offset--;
        }
        Vector2D oldPos = new Vector2D(pos);
        if(nextNode != null) {
            nextNode.propagateMove(oldPos);
        }
    }

    public SnakeNode add(double offset, double radius, Color color, Snake parent){
        if(nextNode == null){
            nextNode = new SnakeNode(pos, radius, color, offset, parent, false);
            GameThread.registerObject(nextNode);
            return nextNode;
        } else {
            return nextNode.add(offset, radius, color, parent);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SnakeNode){
            return ((SnakeNode) obj).pos == pos && ((SnakeNode) obj).offset == offset &&
                    ((SnakeNode) obj).radius == radius && ((SnakeNode) obj).parent == parent &&
                    ((SnakeNode) obj).direction == direction;
        } else {
            return false;
        }
    }

    @Override
    public CollisionObject getCollisionObject() {
        return ccircle;
    }

    @Override
    public void collidedWith(Collidable obj) {
        if(obj instanceof Food && !((Food) obj).isEaten()){

            parent.addNode();
        }
        if(nextNode != null && nextNode.nextNode != null) {
            if (obj instanceof SnakeNode && !nextNode.equals(obj) && !nextNode.nextNode.equals(obj) && isFirst) {
                GameThread.stop();
            }
        }
    }
}

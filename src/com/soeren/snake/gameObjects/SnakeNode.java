package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.Animatable;
import com.soeren.snake.engine.Drawable;
import com.soeren.snake.engine.GameThread;
import com.soeren.snake.engine.Updatable;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author 
 * @version 
 */
public class SnakeNode implements Updatable, Drawable, Animatable
{
    Vector2D pos;
    Vector2D direction;
    Buntstift st;
    double radius;
    Color color;
    ArrayList<Vector2D> movesLeft;
    SnakeNode nextNode = null;
    long offset;

    public SnakeNode(Vector2D startPos, double pRadius, Color pcolor, double offset)
    {
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
    public void init() {
        if(!GameThread.isServer){
            st = new Buntstift();
            st.setzeFarbe(color);
            st.setzeFuellMuster(1);
        }
    }

    @Override
    public void update(long frame) {
        pos = movesLeft.remove(0);
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

    public SnakeNode add(double offset, double radius, Color color){
        if(nextNode == null){
            nextNode = new SnakeNode(pos.plus(pos.normalize().scalarMult(radius*4)), radius, color, offset);
            GameThread.registerObject(nextNode);
            return nextNode;
        } else {
            return nextNode.add(offset, radius, color);
        }
    }
}

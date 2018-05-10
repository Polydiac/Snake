package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.Animatable;
import com.soeren.snake.engine.Drawable;
import com.soeren.snake.engine.GameThread;
import com.soeren.snake.engine.Updatable;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.*;

import java.awt.*;

/**
 * @author 
 * @version 
 */
public class SnakeNode implements Updatable, Drawable, Animatable
{
    Vector2D pos;
    Buntstift st;
    double radius;
    Color color;

    public SnakeNode(Vector2D startPos, double pRadius, Color pcolor)
    {
        radius = pRadius;
        pos = startPos;
        color = pcolor;


    }

    @Override
    public void draw(long frame) {
        st.hoch();
        st.bewegeBis(pos.x, pos.y);
        st.zeichneKreis(radius);
    }

    @Override
    public void delete(long frame) {

    }

    @Override
    public void init() {
        if(!GameThread.isServer){
            st = new Buntstift();
            st.setzeFarbe(color);
        }
    }

    @Override
    public void update(long frame) {

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
}

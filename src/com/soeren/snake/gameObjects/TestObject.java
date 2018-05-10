package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.Buntstift;

public class TestObject implements Updatable, Drawable {
    Buntstift st;
    Vector2D pos;

    public TestObject(){

    }


    @Override
    public void draw(long frame) {
        st.bewegeBis(pos.x, pos.y);
        st.zeichneKreis(20);
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
            st = new Buntstift(DrawingHandler.bs);
        }
        pos = new Vector2D(50,50);
    }

    @Override
    public void update(long frame) {
        pos = pos.plus(new Vector2D(10, 0));
    }
}

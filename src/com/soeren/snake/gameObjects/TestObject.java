package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.UniqueID;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.Buntstift;

public class TestObject implements Updatable, Drawable<TestObject> {
    Buntstift st;
    Vector2D pos;
    String objectId;

    public TestObject(){
        objectId = UniqueID.generateID();
    }

    public String getId(){
        return objectId;
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
    public int getLayer() {
        return 0;
    }

    @Override
    public void updateData(Drawable object) {
        pos = ((TestObject)(object)).pos;
    }

    @Override
    public void init() {
        if(GameThread.isClient){
            st = new Buntstift(DrawingHandler.bs);
        }
        pos = new Vector2D(50,50);
    }

    @Override
    public void update(long frame) {
        pos = pos.plus(new Vector2D(10, 0));
    }
}

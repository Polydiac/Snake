package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.Player;

import java.util.ArrayList;
/**
 * @author 
 * @version 
 */
public class Snake implements Updatable, Drawable
{
    ArrayList<SnakeNode> snake = new ArrayList<SnakeNode>();
    int length;
    float richtung;
    Player parent;
    
    
    public Snake()
    {
        
    }

    @Override
    public void init() {

        if(!GameThread.isServer) {
            DrawingHandler.registerDrawable(this);
        }
    }

    @Override
    public void update(long frame) {

    }

    @Override
    public void draw(long frame) {

    }

    @Override
    public void delete(long frame) {

    }

}

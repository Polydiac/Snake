package com.soeren.snake.engine;

/**
 * @author 
 * @version 
 */

public interface Drawable extends Updatable
{
    void draw(long frame);
    void delete(long frame);
    int getLayer();
    /**
     *  Layers -1-4
     *  -1  Hidden
     *  0   Background
     *  1   Behind Player
     *  2   Player
     *  3   Above Player
     */
}

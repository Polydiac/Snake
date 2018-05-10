package com.soeren.snake.engine;

/**
 * @author 
 * @version 
 */

public interface Drawable extends Updatable
{
    void draw(long frame);
    void delete(long frame);
}

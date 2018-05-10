package com.soeren.snake.engine;

import com.soeren.snake.engine.util.Vector2D;

import java.awt.*;

public interface Animatable extends Updatable, Drawable {
    public void resize(float scalar);
    public void setColor(Color color);
    public void move(Vector2D pos);

}

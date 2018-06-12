package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.util.Player;

import java.util.ArrayList;

public interface InputDestination {
    public void sendMovement(ArrayList<Movement> movements);
}

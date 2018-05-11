package com.soeren.snake.engine.util;

import com.soeren.snake.engine.Game;
import com.soeren.snake.engine.GameThread;
import com.soeren.snake.engine.Updatable;
import com.soeren.snake.gameObjects.Food;

import java.util.ArrayList;
import java.util.Random;

public class FoodManager implements Updatable {
    ArrayList<Food> food = new ArrayList<>();

    private void addFood(Vector2D pos) {
        Food obj = new Food(pos);
        GameThread.registerObject(obj);
        food.add(obj);
    }

    private Vector2D generateNewLocation(){
        Random random = new Random();
        return new Vector2D(random.nextInt(GameThread.width), random.nextInt(GameThread.height));
    }

    @Override
    public void init() {

    }

    @Override
    public void update(long frame) {
        for (int i = 0; i < food.size(); i++) {
            if(food.get(i).isEaten()){
                food.remove(i);

            }
        }
        if(food.size() < PlayerHandler.getPlayers().size()){
            addFood(generateNewLocation());
        }

    }
}

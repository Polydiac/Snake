package com.soeren.snake.engine.util;

import com.soeren.snake.engine.Collidable;
import com.soeren.snake.engine.Updatable;

import java.util.ArrayList;

public class CollisionHandler implements Updatable {
    private static ArrayList<Collidable> collidables = new ArrayList<>();

    public CollisionHandler() {
    }

    public static void registerCollidable(Collidable obj) {
        collidables.add(obj);
    }

    public static void removeCollidable(Collidable obj) {
        collidables.remove(obj);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(long frame) {
        int size = collidables.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(collidables.get(i).getCollisionObject()
                        .didCollide(collidables.get(j).getCollisionObject()) &&
                        !collidables.get(i).equals(collidables.get(j))){
                    collidables.get(i).collidedWith(collidables.get(j));

                }
            }
        }
    }
}

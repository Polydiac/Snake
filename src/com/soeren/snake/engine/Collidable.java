package com.soeren.snake.engine;

public interface Collidable {

    CollisionObject getCollisionObject();
    void collidedWith(Collidable obj);

}

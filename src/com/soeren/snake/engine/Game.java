package com.soeren.snake.engine;

import com.soeren.snake.engine.util.movement.KeyboardSource;
import com.soeren.snake.engine.util.movement.MovementHandler;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.gameObjects.TestObject;

import java.util.ArrayList;

/**
 * @author 
 * @version 
 */
public class Game implements Updatable
{
    private static ArrayList<Updatable> objects = new ArrayList<Updatable>();

    public void init(){
        objects.add(new MovementHandler());
        if(!GameThread.isServer) {
            MovementHandler.registerInputSource(new KeyboardSource("wasd", Player.getLocalPlayer()));
        }

        GameThread.registerObject(new TestObject());

    }

    @Override
    public void update(long frame) {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).update(frame);
        }
    }

    public static void registerUpdatable(Updatable obj){
        obj.init();
        objects.add(obj);
    }

}

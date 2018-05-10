package com.soeren.snake.engine;

import com.soeren.snake.engine.util.PlayerHandler;
import com.soeren.snake.engine.util.Vector2D;
import com.soeren.snake.engine.util.movement.KeyboardSource;
import com.soeren.snake.engine.util.movement.MovementHandler;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.gameObjects.Snake;

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
            MovementHandler.registerInputSource(new KeyboardSource("wasd", PlayerHandler.getLocalPlayer()));
        }


        GameThread.registerObject(new Snake(PlayerHandler.getLocalPlayer(), 20, new Vector2D(100,1000), 2.5, 2));

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

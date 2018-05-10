package com.soeren.snake.engine;

import java.util.ArrayList;

/**
 * @author 
 * @version 
 */
public class Game implements Updateable
{
    private static ArrayList<Updateable> objects = new ArrayList<Updateable>();

    public void init(){
        objects.add(new MovementHandler());
        if(!GameThread.isServer) {
            MovementHandler.registerInputSource(new KeyboardSource("wasd"));
        }

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).init();
        }
    }

    @Override
    public void update(long frame) {
        for(int i = 0; i < objects.size(); i++) {
            objects.get(i).update(frame);
        }
    }

    public static void registerUpdateable(Updateable obj){
        obj.init();
        objects.add(obj);
    }

}

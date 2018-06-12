package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.DrawingHandler;
import com.soeren.snake.engine.Game;
import com.soeren.snake.engine.GameThread;

public class KeyboardManager{
    private static KeyboardListener kb = new KeyboardListener();
    private static boolean isRegistered = false;

    public static KeyboardListener getListener(){
        if(!isRegistered && GameThread.isClient){
            DrawingHandler.bs.addKeyListener(kb);
            isRegistered = true;
        }
        if(GameThread.isClient){
            return kb;
        } else {
            return null;
        }

    }

    //private KeyboardManager(){}
}

package com.soeren.snake.engine.util.movement;

public class KeyboardManager{
    private static KeyboardListener kb = new KeyboardListener();

    public static KeyboardListener getListener(){
        return kb;
    }

    //private KeyboardManager(){}
}

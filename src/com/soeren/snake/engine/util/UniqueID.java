package com.soeren.snake.engine.util;

import java.io.Serializable;
import java.util.Random;

public class UniqueID {
    public static String generateID(){
        Random rand = new Random(System.currentTimeMillis());
        String alphaNum = "abcdefghijklmnopqrstuvwxyz1234567890";
        String string = "";
        for (int i = 0; i < 4; i++) {
            string += alphaNum.charAt(rand.nextInt(36));
        }
        System.out.println(string);
        return string;
    }
}

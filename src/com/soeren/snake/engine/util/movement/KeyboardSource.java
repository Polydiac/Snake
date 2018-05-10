package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.DrawingHandler;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.engine.util.Vector2D;

import java.util.ArrayList;

public class KeyboardSource implements InputSource {
    char UP, DOWN, LEFT, RIGHT;
    Player associatedPlayer;


    public KeyboardSource(String keymap, Player player){
        this.associatedPlayer = player;
        DrawingHandler.bs.addKeyListener(KeyboardManager.getListener());
        UP = keymap.charAt(0);
        LEFT = keymap.charAt(1);
        DOWN = keymap.charAt(2);
        RIGHT = keymap.charAt(3);
    }

    @Override
    public ArrayList<Movement> getMovements() {
        KeyboardListener kb = KeyboardManager.getListener();
        ArrayList<Movement> returnList = new ArrayList<>();
        Vector2D dir = new Vector2D(0,0);
        if(kb.isKeyPressed(UP)){
            dir.plus(new Vector2D(0,1));
        }
        if(kb.isKeyPressed(RIGHT)){
            dir.plus(new Vector2D(1,0));
        }
        if(kb.isKeyPressed(LEFT)){
            dir.plus(new Vector2D(-1,0));
        }
        if(kb.isKeyPressed(DOWN)){
            dir.plus(new Vector2D(0,0));
        }

        if(dir.equals(new Vector2D(-1,0))){
            returnList.add(new Movement(Direction.LEFT));
        } else if(dir.equals(new Vector2D(-1,1))){
            returnList.add(new Movement(Direction.UP));
        } else if(dir.equals(new Vector2D(0,1))){
            returnList.add(new Movement(Direction.UP));
        } else if(dir.equals(new Vector2D(1,1))){
            returnList.add(new Movement(Direction.UP));
        } else if(dir.equals(new Vector2D(1,0))){
            returnList.add(new Movement(Direction.RIGHT));
        } else {
            returnList.add(new Movement(Direction.UP));
        }
        return returnList;
    }
}

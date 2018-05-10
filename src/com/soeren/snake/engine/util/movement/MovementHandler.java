package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.Updatable;
import com.soeren.snake.engine.util.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//TODO: Player specific offload
public class MovementHandler implements Updatable {
    private static Map<String, ArrayList<Movement>> movements = new HashMap<>();
    private static ArrayList<InputSource> sources = new ArrayList<>();

    public MovementHandler(){

    }

    @Override
    public void init() {

    }

    @Override
    public void update(long frame) {
        for (int i = 0; i < sources.size(); i++) {
            ArrayList<Movement> sourceDump = sources.get(i).getMovements();
            for (int j = 0; j < sourceDump.size(); j++) {
                Player player = sourceDump.get(j).getPlayer();
                if(movements.get(player)==null){
                    movements.put(player.getName(), new ArrayList<Movement>());
                }
                movements.get(player.getName()).add(sourceDump.get(j));
            }
        }
    }

    public static void registerInputSource(InputSource source){
        sources.add(source);
    }

    public static ArrayList<Movement> getMovements(Player player){
        ArrayList mov =  movements.get(player.getName());
        movements.put(player.getName(), new ArrayList<>());
        return mov;
    }


}

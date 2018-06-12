package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.multiplayer.Client;
import com.soeren.snake.engine.multiplayer.Server;
import com.soeren.snake.engine.util.Player;

import java.net.ServerSocket;
import java.util.ArrayList;

public class NetworkSource implements InputSource, InputDestination{

    ArrayList<Movement> movements = new ArrayList<>();


    @Override
    public ArrayList<Movement> getMovements() {
        System.out.println("HEYYYYYYYYYOOOOOOOO");
        return Server.getServer().getMovements();
    }


    @Override
    public void sendMovement(ArrayList<Movement> movements) {
        for(Movement movement: movements){
            Client.getClient().sendMovement(movement);
        }
    }
}

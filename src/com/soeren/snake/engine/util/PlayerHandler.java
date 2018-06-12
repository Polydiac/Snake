package com.soeren.snake.engine.util;

import com.soeren.snake.engine.GameThread;

import java.util.ArrayList;

public class PlayerHandler {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player localPlayer;


    public static ArrayList<Player> getPlayers(){
        getLocalPlayer();
        return players;
    }

    public static Player getLocalPlayer(){
        if(GameThread.isClient) {
            if(localPlayer == null){
                localPlayer = Player.generateLocalPlayer();
                players.add(localPlayer);
            }
            return localPlayer;
        } else {
            return null;
        }

    }

    public static void registerPlayer(Player player){
        System.out.println(player.name);
        players.add(player);
    }

    public static void removePlayer(Player player){
        for (int i = 0; i < players.size(); i++) {
            if(player.equals(players.get(i))){
                players.remove(player);
            }
        }
    }

}

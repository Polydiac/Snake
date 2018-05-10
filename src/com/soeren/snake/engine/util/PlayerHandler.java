package com.soeren.snake.engine.util;

import java.util.ArrayList;

public class PlayerHandler {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Player localPlayer;


    public static ArrayList<Player> getPlayers(){
        return players;
    }

    public static Player getLocalPlayer(){
        if(localPlayer == null){
            localPlayer = Player.generateLocalPlayer();
            players.add(localPlayer);
        }
        return localPlayer;
    }

    public static void registerPlayer(Player player){
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

package com.soeren.snake.engine;

import com.soeren.snake.engine.multiplayer.Client;
import com.soeren.snake.engine.multiplayer.Server;
import com.soeren.snake.engine.util.*;
import com.soeren.snake.engine.util.movement.KeyboardSource;
import com.soeren.snake.engine.util.movement.MovementHandler;
import com.soeren.snake.engine.util.movement.NetworkSource;
import com.soeren.snake.gameObjects.Snake;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;


/**
 * @author 
 * @version 
 */
public class Game implements Updatable
{
    private static ArrayList<Updatable> objects = new ArrayList<Updatable>();

    public void init(){

        //Player player2 = Player.generateLocalPlayer();
        //PlayerHandler.registerPlayer(player2);
        if(GameThread.isServer&&!GameThread.isClient){
            Server.startServer();
            try {
                System.out.println(InetAddress.getLocalHost());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            Server.getServer().waitForConnections(30000);
            System.out.println("Connected " + PlayerHandler.getPlayers().size() + " players");
            MovementHandler.registerInputSource(new NetworkSource());
        }
        if(GameThread.isClient&&!GameThread.isServer){
            String ip = JOptionPane.showInputDialog("Please enter IP Address of Server");
            try {
                Client.startClient(InetAddress.getByName(ip));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            MovementHandler.registerDestination(new NetworkSource());
        }


        if(GameThread.isServer){
            for(Player player: PlayerHandler.getPlayers()){
                GameThread.registerObject(new Snake(player, 20, new Vector2D(500,500), 2.5, 2));
            }
            GameThread.registerObject(new FoodManager());
            GameThread.registerObject(new CollisionHandler());
        }

        if(GameThread.isClient) {
            MovementHandler.registerInputSource(new KeyboardSource("wasd", PlayerHandler.getLocalPlayer()));
            //MovementHandler.registerInputSource(new KeyboardSource("ijkl", player2));
        }

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

    public static void removeUpdatable(Updatable obj) {
        objects.remove(obj);
    }

}

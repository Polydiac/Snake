package com.soeren.snake.engine.multiplayer;

import com.soeren.snake.engine.Drawable;
import com.soeren.snake.engine.Updatable;
import com.soeren.snake.engine.util.movement.Movement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
    private static Client client;
    Connection connection;
    int port = 14887;
    static InetAddress addr;

    public Client(InetAddress serverAddr){
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            Socket socket = new Socket(serverAddr, port);
            connection = new Connection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<Drawable> getGameState(){
        Object obj = connection.read();
        System.out.println(obj);
        if(obj instanceof ArrayList){
            return (ArrayList<Drawable>) obj;
        }
        return null;
    }

    public void sendMovement(Movement movement){
        System.out.println(movement);
        connection.write(movement);
    }

    public static void startClient(InetAddress addr){
        client = new Client(addr);
    }

    public static Client getClient(){
        return client;
    }

    public void stopClient() {
        connection.terminate();
    }
}

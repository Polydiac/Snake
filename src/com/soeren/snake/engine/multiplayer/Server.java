package com.soeren.snake.engine.multiplayer;

import com.soeren.snake.engine.Drawable;
import com.soeren.snake.engine.Updatable;
import com.soeren.snake.engine.util.movement.Movement;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private static Server server;
    ArrayList<Connection> connections = new ArrayList<>();
    int port = 14887;
    static InetAddress addr;

    public Server(){
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void waitForConnections(long timeout) {
        long startTime = System.currentTimeMillis();

        try {
            ServerSocket socket = new ServerSocket(port);

            while (startTime + timeout > System.currentTimeMillis()) {

                socket.setSoTimeout((int) timeout);
                connections.add(new Connection(socket.accept()));

                System.out.println("next");
            }
        } catch (SocketTimeoutException e){

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Movement> getMovements(){
        ArrayList<Movement> moves = new ArrayList<>();
        for (Connection connection: connections) {
            Object obj = connection.read();
            if (obj instanceof Movement){
                moves.add((Movement) obj);
            }
        }
        return moves;
    }

    public void sendGameState(ArrayList<Drawable> objs){
        System.out.println(objs);
        for (Connection connection: connections){
            connection.write(objs);
        }
    }

    public static void startServer(){
        server = new Server();
    }

    public static Server getServer(){
        return server;
    }

    public void stopServer() {
        for (Connection connection :
                connections) {
            connection.terminate();
        }
    }
}

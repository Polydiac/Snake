package com.soeren.snake.engine.multiplayer;

import com.soeren.snake.engine.GameThread;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.engine.util.PlayerHandler;

import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private ObjectInputStream streamIn;
    private ObjectOutputStream streamOut;

    public Connection(Socket socket){
        System.out.println("HOOHOOOHOOOHOOO");
        this.socket = socket;
        try {
            if(GameThread.isServer){
                streamOut = new ObjectOutputStream(socket.getOutputStream());
                streamIn = new ObjectInputStream(socket.getInputStream());
            } else {
                streamIn = new ObjectInputStream(socket.getInputStream());
                streamOut = new ObjectOutputStream(socket.getOutputStream());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HOOHOOOHOOOHOOO");
        establishConnection();
    }

    public void write(Object obj){
        try {
            streamOut.writeObject(obj);
            streamOut.flush();
            System.out.println("HEYO");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object read(){
        try {
            Object res = streamIn.readObject();
            System.out.println("HOYO " + res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void establishConnection(){
        if(GameThread.isClient){
            System.out.println("Menno");
            write(PlayerHandler.getLocalPlayer());
        } else if(GameThread.isServer){
            System.out.println("Manno");
            PlayerHandler.registerPlayer((Player) read());

        }
        System.out.println("Foo");
    }

    public void terminate(){

        try {
            streamIn.close();
            streamOut.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

package com.soeren.snake.engine.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Random;

public class Player {
    String name;
    InetAddress address;

    {
        try {
            address = InetAddress.getByName("0.0.0.0");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(address, player.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, address);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String pName, InetAddress addr)  {
        name = pName;
        address = addr;

    }

    private static String generateID(){
        Random rand = new Random(System.currentTimeMillis());
        String alphaNum = "abcdefghijklmnopqrstuvwxyz1234567890";
        String string = "";
        for (int i = 0; i < 32; i++) {
            string += alphaNum.charAt(rand.nextInt(36));
        }
        return string;
    }

    public static Player getLocalPlayer(){
        try {
            return new Player(generateID(), InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}


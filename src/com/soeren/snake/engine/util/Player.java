package com.soeren.snake.engine.util;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Random;

public class Player implements Serializable {
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


    public static Player generateLocalPlayer(){
        try {
            return new Player(UniqueID.generateID(), InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}


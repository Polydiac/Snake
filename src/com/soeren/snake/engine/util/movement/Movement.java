package com.soeren.snake.engine.util.movement;

import com.soeren.snake.engine.GameThread;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.engine.util.PlayerHandler;

import java.util.Objects;

public class Movement {
    Direction direction;
    Player player;
    long time;

    public Movement(Direction dir){
        direction = dir;
        player = PlayerHandler.getLocalPlayer();
        time = GameThread.getFrame();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return time == movement.time &&
                direction == movement.direction &&
                Objects.equals(player, movement.player);
    }

    @Override
    public int hashCode() {

        return Objects.hash(direction, player, time);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}

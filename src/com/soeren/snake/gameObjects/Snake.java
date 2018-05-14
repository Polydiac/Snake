package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.Player;
import com.soeren.snake.engine.util.Vector2D;
import com.soeren.snake.engine.util.movement.Direction;
import com.soeren.snake.engine.util.movement.Movement;
import com.soeren.snake.engine.util.movement.MovementHandler;

import java.awt.*;
import java.util.ArrayList;
/**
 * @author 
 * @version 
 */
public class Snake implements Updatable
{
    ArrayList<SnakeNode> snake = new ArrayList<SnakeNode>();
    SnakeNode parentNode = null;
    int length;
    Vector2D richtung;
    Player parent;
    double radius;
    Vector2D pos;
    double speed;
    double turnSpeed;


    public Snake( Player parent, double radius, Vector2D pos, double speed, double turnSpeed) {
        this.richtung = new Vector2D(0,-1);
        this.parent = parent;
        this.radius = radius;
        this.pos = pos;
        this.speed = speed;
        this.turnSpeed = turnSpeed;
    }

    @Override
    public void init() {
        addNode();
    }

    @Override
    public void update(long frame) {
        ArrayList<Movement> moves = MovementHandler.getMovements(parent);
        double oldDeg = Math.toDegrees(richtung.getTheta());
        double change = 0d;
        if(moves != null) {
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getDirection() == Direction.LEFT) {
                    change -= turnSpeed;
                } else if (moves.get(i).getDirection() == Direction.RIGHT) {
                    change += turnSpeed;
                }
            }
        }
        richtung.setTheta(Math.toRadians(oldDeg + change));
        richtung = richtung.normalize();

        pos = pos.plus(richtung.scalarMult(speed));
        parentNode.propagateMove(pos);

        /*if((frame % 100) == 0){
            System.out.println(frame);
            //addNode();
        }*/
    }


    public void addNode() {
        if(parentNode == null) {
            parentNode = new SnakeNode(pos, radius, Color.GREEN, 0, this, true);
            snake.add(parentNode);
            GameThread.registerObject(parentNode);
        } else {
            snake.add(parentNode.add(radius*1.8/speed, radius, Color.GREEN, this));
        }

        length++;
    }

}

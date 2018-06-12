package com.soeren.snake.gameObjects;

import com.soeren.snake.engine.*;
import com.soeren.snake.engine.util.CollisionHandler;
import com.soeren.snake.engine.util.UniqueID;
import com.soeren.snake.engine.util.Vector2D;
import sum.kern.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 
 * @version 
 */
public class SnakeNode implements Updatable, Drawable<SnakeNode>, Animatable<SnakeNode>, Collidable, Serializable
{
    Vector2D pos;
    Vector2D direction;
    transient Buntstift st;
    double radius;
    Color color;
    ArrayList<Vector2D> movesLeft;
    SnakeNode nextNode = null;
    Snake parent;
    long offset;
    boolean isFirst;
    CollisionCircle ccircle;
    String objectID;

    public SnakeNode(Vector2D startPos, double pRadius, Color pcolor, double offset, Snake pparent, boolean isFirst)
    {
        this.isFirst = isFirst;
        objectID = UniqueID.generateID();
        parent = pparent;
        radius = pRadius;
        pos = startPos;
        color = pcolor;
        movesLeft = new ArrayList<>();
        this.offset = Math.round(offset);
        movesLeft.add(startPos);
        /*for (int i = 0; i < Math.round(offset); i++) {
            movesLeft.add(startPos);
        }*/

    }

    @Override
    public String getId(){
        return objectID;
    }

    @Override
    public void draw(long frame) {
        System.out.println("Great... Just great");
        st.hoch();
        st.bewegeBis(pos.x, pos.y);
        st.zeichneKreis(radius);
        st.setzeFarbe(color.darker());
        st.zeichneKreis(radius*0.7);

        if(isFirst) {
            st.setzeFarbe(Color.BLACK);
            st.zeichneKreis(radius*0.1);
        }
        st.setzeFarbe(color);
    }

    @Override
    public void delete(long frame) {
        st.radiere();
        draw(frame);
        st.normal();
    }

    @Override
    public int getLayer() {
        return 2;
    }

    @Override
    public void updateData(Drawable object) {
        if(object.getId().equals(objectID) && object instanceof SnakeNode){
            System.out.println("This should be good");
            SnakeNode nodeObj = (SnakeNode) object;
            pos = nodeObj.pos;
            direction = nodeObj.direction;
            radius = nodeObj.radius;
            color = nodeObj.color;
            movesLeft = nodeObj.movesLeft;
            nextNode = nodeObj.nextNode;
            offset = nodeObj.offset;
            isFirst = nodeObj.isFirst;
            ccircle = nodeObj.ccircle;
        }
    }

    @Override
    public void init() {
        if(GameThread.isClient){
            st = new Buntstift();
            st.setzeFarbe(color);
            st.setzeFuellMuster(1);
        }
        if(ccircle == null){
            ccircle = new CollisionCircle(radius, pos);
        }

        if(GameThread.isServer){
            CollisionHandler.registerCollidable(this);
        }
    }

    @Override
    public void update(long frame) {
        pos = movesLeft.remove(0);
        ccircle.move(pos);
    }


    @Override
    public void resize(float scalar) {

    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void move(Vector2D pos) {

    }

    public void propagateMove(Vector2D newPos){
        movesLeft.add(newPos);
        if(offset > 0){
            movesLeft.add(newPos);
            offset--;
        }
        Vector2D oldPos = new Vector2D(pos);
        if(nextNode != null) {
            nextNode.propagateMove(oldPos);
        }
    }

    public SnakeNode add(double offset, double radius, Color color, Snake parent){
        if(nextNode == null){
            nextNode = new SnakeNode(pos, radius, color, offset, parent, false);
            GameThread.registerObject(nextNode);
            return nextNode;
        } else {
            return nextNode.add(offset, radius, color, parent);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SnakeNode){
            return ((SnakeNode) obj).pos == pos && ((SnakeNode) obj).offset == offset &&
                    ((SnakeNode) obj).radius == radius && ((SnakeNode) obj).parent == parent &&
                    ((SnakeNode) obj).direction == direction;
        } else {
            return false;
        }
    }

    @Override
    public CollisionObject getCollisionObject() {
        return ccircle;
    }

    @Override
    public void collidedWith(Collidable obj) {
        if(obj instanceof Food && !((Food) obj).isEaten()){

            parent.addNode();
        }
        if(nextNode != null && nextNode.nextNode != null) {
            if (obj instanceof SnakeNode && !nextNode.equals(obj) && !nextNode.nextNode.equals(obj) && isFirst) {
                GameThread.stop();
            }
        }
    }
}

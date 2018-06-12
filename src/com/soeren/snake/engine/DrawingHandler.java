package com.soeren.snake.engine;

import java.util.ArrayList;

import com.soeren.snake.engine.multiplayer.Client;
import com.soeren.snake.engine.multiplayer.Server;
import sum.kern.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author 
 * @version 
 */
public class DrawingHandler implements Updatable
{
    public static Fenster bs;

    private static ArrayList<Drawable> drawable = new ArrayList<Drawable>();
    private int width = 1000;
    private int height = 1000;
    
    public DrawingHandler(){
        
    }
    
    public void init(){
        if(GameThread.isClient){
            bs = new Fenster(width, height, true);

            bs.setFocusable(true);

            bs.requestFocus();

            bs.setIgnoreRepaint(true);
            bs.createBufferStrategy(2);

            bs.setBackground(new Color(255, 255, 255));
        }
    }
    
    public void update(long frame){

        if(GameThread.isClient&&!GameThread.isServer){
            System.out.println("I did an Update!");
            if(drawable.size() == 0){
                drawable = Client.getClient().getGameState();
                for (Drawable obj :
                        drawable) {
                    obj.init();
                }
            } else {
                for (Drawable newObj :
                        Client.getClient().getGameState()) {
                    for (Drawable oldObj :
                            drawable) {
                        oldObj.updateData(newObj);
                    }
                }
            }
            //drawable = Client.getClient().getGameState();
        }
        if(GameThread.isClient){

            for(int i = 0; i < drawable.size();i++){
                drawable.get(i).draw(frame);
            }

            //bs.getBufferStrategy().show();
            if(bs != null){
                bs.zeichneDich();
                System.out.println("This is great");
            }

            for(int i = 0; i < drawable.size();i++){
                drawable.get(i).delete(frame);
            }
        }
        if(GameThread.isServer&&!GameThread.isClient){
            Server.getServer().sendGameState(drawable);
        }

    }
    
    public void stop(){
        if(GameThread.isClient){
            bs.gibFrei();
        }

    }
    
    public static void registerDrawable(Drawable obj){
        drawable.add(obj);
        Collections.sort(drawable,new Comparator<Drawable>(){
            @Override
            public int compare(final Drawable lhs,Drawable rhs) {
                return (Integer.compare(lhs.getLayer(), rhs.getLayer()));
            }
        });
    }
    public static void removeDrawable(Drawable obj) {
        drawable.remove(obj);
    }
}

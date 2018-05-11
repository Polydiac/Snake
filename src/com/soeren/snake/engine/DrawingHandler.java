package com.soeren.snake.engine;

import java.util.ArrayList;

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
        bs = new Fenster(width, height, true);

        bs.setFocusable(true);

        bs.requestFocus();

        bs.setIgnoreRepaint(true);
        bs.createBufferStrategy(2);
        
        bs.setBackground(new Color(255, 255, 255));

    }
    
    public void update(long frame){


        for(int i = 0; i < drawable.size();i++){
            drawable.get(i).draw(frame);    
        }

        //bs.getBufferStrategy().show();
        bs.zeichneDich();

        for(int i = 0; i < drawable.size();i++){
            drawable.get(i).delete(frame);
        }
    }
    
    public void stop(){
        bs.gibFrei();
    }
    
    public static void registerDrawable(Drawable obj){
        drawable.add(obj);
        /*Collections.sort(drawable,new Comparator<Drawable>(){
            @Override
            public int compare(final Drawable lhs,Drawable rhs) {
                return (Integer.compare(lhs.getLayer(), rhs.getLayer()));
            }
        });*/
    }
    public static void removeDrawable(Drawable obj) {
        drawable.remove(obj);
    }
}

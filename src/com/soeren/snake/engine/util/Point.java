package com.soeren.snake.engine.util;

import java.lang.Math;

/**
 * @author 
 * @version 
 */
public class Point
{
    // Bezugsobjekte
    double x;
    double y;
    // Attribute

    // Konstruktor
    public Point(double px, double py)
    {
        x = px;
        y = py;
    }
    
    public double getX(){
        return x;    
    }
    
    public double getY(){
        return y;    
    }
    
    public double distanceTo(Point ppoint){
        return Math.sqrt(Math.pow(Math.abs(this.x-ppoint.x),2)+Math.pow(Math.abs(this.y-ppoint.y),2));  
    }

    // Dienste

}

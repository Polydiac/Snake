package com.soeren.snake.engine.util;


/**
 *  Processing-penner-easing
 */


public class Easings {


    /**
     *
     * @param t Current Time
     * @param b Beginning Value
     * @param c Wanted Change of the given Value
     * @param d Duration of the supposed Aninmation
     * @return
     */

    public static double  sineEaseIn(double t,double b , double c, double d) {
        return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
    }

    public static double  sineEaseOut(double t,double b , double c, double d) {
        return c * Math.sin(t/d * (Math.PI/2)) + b;
    }

    public static double  sineEaseInOut(double t,double b , double c, double d) {
        return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
    }

    public static double quintEaseIn (double t,double b , double c, double d) {
        return c*(t/=d)*t*t*t*t + b;
    }

    public static double quintEaseOut (double t,double b , double c, double d) {
        return c*((t=t/d-1)*t*t*t*t + 1) + b;
    }

    public static double quintEaseInOut (double t,double b , double c, double d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
        return c/2*((t-=2)*t*t*t*t + 2) + b;
    }

    public static double  quartEaseIn(double t,double b , double c, double d) {
        return c*(t/=d)*t*t*t + b;
    }

    public static double  quartEaseOut(double t,double b , double c, double d) {
        return -c * ((t=t/d-1)*t*t*t - 1) + b;
    }

    public static double  quartEaseInOut(double t,double b , double c, double d) {
        if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
        return -c/2 * ((t-=2)*t*t*t - 2) + b;
    }

    public static double  quadEaseIn(double t,double b , double c, double d) {
        return c*(t/=d)*t + b;
    }

    public static double  quadEaseOut(double t,double b , double c, double d) {
        return -c *(t/=d)*(t-2) + b;
    }

    public static double  quadEaseInOut(double t,double b , double c, double d) {
        if ((t/=d/2) < 1) return c/2*t*t + b;
        return -c/2 * ((--t)*(t-2) - 1) + b;
    }

    public static double linearEaseNone (double t,double b , double c, double d) {
        return c*t/d + b;
    }

    public static double linearEaseIn (double t,double b , double c, double d) {
        return c*t/d + b;
    }

    public static double linearEaseOut (double t,double b , double c, double d) {
        return c*t/d + b;
    }

    public static double linearEaseInOut (double t,double b , double c, double d) {
        return c*t/d + b;
    }

    public static double  expoEaseIn(double t,double b , double c, double d) {
        return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
    }

    public static double  expoEaseOut(double t,double b , double c, double d) {
        return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
    }

    public static double  expoEaseInOut(double t,double b , double c, double d) {
        if (t==0) return b;
        if (t==d) return b+c;
        if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
        return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
    }

    public static double  elasticEaseIn(double t,double b , double c, double d ) {
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        double p=d*.3f;
        double a=c;
        double s=p/4;
        return -(a* Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2* Math.PI)/p )) + b;
    }

    public static double  elasticEaseIn(double t,double b , double c, double d, double a, double p) {
        double s;
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        if (a < Math.abs(c)) { a=c;  s=p/4; }
        else { s = p/(2* Math.PI) * Math.asin (c/a);}
        return -(a* Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
    }

    public static double  elasticEaseOut(double t,double b , double c, double d) {
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        double p=d*.3f;
        double a=c;
        double s=p/4;
        return (a* Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2* Math.PI)/p ) + c + b);
    }

    public static double  elasticEaseOut(double t,double b , double c, double d, double a, double p) {
        double s;
        if (t==0) return b;  if ((t/=d)==1) return b+c;
        if (a < Math.abs(c)) { a=c;  s=p/4; }
        else { s = p/(2* Math.PI) * Math.asin (c/a);}
        return (a* Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2* Math.PI)/p ) + c + b);
    }

    public static double  elasticEaseInOut(double t,double b , double c, double d) {
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;
        double p=d*(.3f*1.5f);
        double a=c;
        double s=p/4;
        if (t < 1) return -.5f*(a* Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2* Math.PI)/p )) + b;
        return a* Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2* Math.PI)/p ) *.5f + c + b;
    }

    public static double  elasticEaseInOut(double t,double b , double c, double d, double a, double p) {
        double s;
        if (t==0) return b;  if ((t/=d/2)==2) return b+c;
        if (a < Math.abs(c)) { a=c; s=p/4; }
        else { s = p/(2* Math.PI) * Math.asin (c/a);}
        if (t < 1) return -.5f*(a* Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2* Math.PI)/p )) + b;
        return a* Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2* Math.PI)/p ) *.5f + c + b;
    }

    public static double cubicEaseIn (double t,double b , double c, double d) {
        return c*(t/=d)*t*t + b;
    }

    public static double cubicEaseOut (double t,double b , double c, double d) {
        return c*((t=t/d-1)*t*t + 1) + b;
    }

    public static double cubicEaseInOut (double t,double b , double c, double d) {
        if ((t/=d/2) < 1) return c/2*t*t*t + b;
        return c/2*((t-=2)*t*t + 2) + b;
    }

    public static double  circEaseIn(double t,double b , double c, double d) {
        return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
    }

    public static double  circEaseOut(double t,double b , double c, double d) {
        return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
    }

    public static double  circEaseInOut(double t,double b , double c, double d) {
        if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
        return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
    }

    public static double  bounceEaseIn(double t,double b , double c, double d) {
        return c - bounceEaseOut (d-t, 0, c, d) + b;
    }

    public static double  bounceEaseOut(double t,double b , double c, double d) {
        if ((t/=d) < (1/2.75f)) {
            return c*(7.5625f*t*t) + b;
        } else if (t < (2/2.75f)) {
            return c*(7.5625f*(t-=(1.5f/2.75f))*t + .75f) + b;
        } else if (t < (2.5/2.75)) {
            return c*(7.5625f*(t-=(2.25f/2.75f))*t + .9375f) + b;
        } else {
            return c*(7.5625f*(t-=(2.625f/2.75f))*t + .984375f) + b;
        }
    }

    public static double  bounceEaseInOut(double t,double b , double c, double d) {
        if (t < d/2) return bounceEaseIn (t*2, 0, c, d) * .5f + b;
        else return bounceEaseOut (t*2-d, 0, c, d) * .5f + c*.5f + b;
    }

    public static double  backEaseIn(double t,double b , double c, double d) {
        double s = 1.70158f;
        return c*(t/=d)*t*((s+1)*t - s) + b;
    }

    public static double  backEaseIn(double t,double b , double c, double d, double s) {
        return c*(t/=d)*t*((s+1)*t - s) + b;
    }

    public static double  backEaseOut(double t,double b , double c, double d) {
        double s = 1.70158f;
        return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
    }

    public static double  backEaseOut(double t,double b , double c, double d, double s) {
        return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
    }

    public static double  backEaseInOut(double t,double b , double c, double d) {
        double s = 1.70158f;
        if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
        return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
    }

    public static double  backEaseInOut(double t,double b , double c, double d, double s) {
        if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525f))+1)*t - s)) + b;
        return c/2*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2) + b;
    }
}

package com.soeren.snake.engine; /**
 * @author 
 * @version 
 */

import com.soeren.snake.engine.DrawingHandler;
import com.soeren.snake.engine.Game;

public class GameThread {
    private static long frame = System.nanoTime();
    int framerate = 25;
    long interval;
    static boolean isRunning = true;
    static boolean isServer = false;
    static DrawingHandler draw;
    static Game game;
    
    public GameThread(){
        this(1000, 1000, false);
    }

    public GameThread(int width, int height, boolean pIsServer){
        this(width, height, 25, pIsServer);
    }

    public GameThread(int width, int height, int pframerate, boolean pIsServer){
        framerate = pframerate;
        interval = 1000000 / framerate;

        this.init();
        this.run();
    }
    
    public void stop(){
        isRunning = false;    
    }

    public void init(){
        if (!isServer){
            draw = new DrawingHandler();
        }
        game = new Game();

        if(!isServer){
            draw.init();
        }
        game.init();
    }

    private void update(long frame){
        if(!isServer) {
            draw.update(frame);
        }
        game.update(frame);
    }
    

    protected void run(){
        
        while(isRunning){
            long startTime = System.nanoTime();
            this.update(frame);

            long stopTime = System.nanoTime() - startTime;
            if(interval - (stopTime / 1000000) > 0){
                try {
                    Thread.sleep(interval - (stopTime / 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            frame = System.nanoTime();
        }
    }

    public static long getFrame(){
        return frame;
    }

    public static void main(String args[]){
        new GameThread();
    }

}

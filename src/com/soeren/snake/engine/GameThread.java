package com.soeren.snake.engine; /**
 * @author 
 * @version 
 */

import com.soeren.snake.engine.util.movement.KeyboardManager;

public class GameThread {
    private static long frame = System.currentTimeMillis();
    int framerate = 60;
    long interval;
    public static boolean isRunning = true;
    public static boolean isServer = false;
    private static DrawingHandler draw;
    private static Game game;
    public static int width = 1000;
    public static int height = 1000;

    
    public GameThread(){
        this(1000, 1000, false);
    }

    public GameThread(boolean pIsServer) {
        this(1000,1000,pIsServer);
    }

    public GameThread(int width, int height, boolean pIsServer){
        this(width, height, 60, pIsServer);
    }

    public GameThread(int pwidth, int pheight, int pframerate, boolean pIsServer){
        isServer = pIsServer;

        width = pwidth;
        height = pheight;

        framerate = pframerate;
        interval = 1000/framerate;

        this.init();
        this.run();
    }
    
    public static void stop(){
        isRunning = false;
        draw.stop();
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

            long stopTime = Math.abs(System.nanoTime() - startTime);
            if(interval - (stopTime / 1000000) > 0){
                try {
                    Thread.sleep(interval - (stopTime / 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("OHNO");
            }

            frame = System.currentTimeMillis();
            if(KeyboardManager.getListener().isKeyPressed('\u001B')){
                stop();
            }
        }
    }

    public static long getFrame(){
        return frame;
    }

    public static void main(String args[]){
        if(args.length > 0) {
            if (args[0].contains("server")) {
                new GameThread(true);
            }
        } else {
            new GameThread();
        }
    }

    public static void registerObject(Drawable obj) {
        Game.registerUpdatable(obj);
        if(!isServer) {
            DrawingHandler.registerDrawable(obj);
        }
    }

    public static void registerObject(Updatable obj) {
        Game.registerUpdatable(obj);
    }

    public static void removeObject(Drawable obj) {
        Game.removeUpdatable(obj);
        if(!isServer) {
            DrawingHandler.removeDrawable(obj);
        }
    }

    public static void removeObject(Updatable obj) {
        Game.registerUpdatable(obj);
    }



}

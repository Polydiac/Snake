package com.soeren.snake.engine; /**
 * @author 
 * @version 
 */

import com.soeren.snake.engine.multiplayer.Client;
import com.soeren.snake.engine.multiplayer.Server;
import com.soeren.snake.engine.util.movement.KeyboardManager;
import com.soeren.snake.engine.util.movement.MovementHandler;

public class GameThread {
    private static long frame = System.currentTimeMillis();
    int framerate = 60;
    long interval;
    public static boolean isRunning = true;
    public static boolean isServer = false;
    public static boolean isClient = false;
    private static DrawingHandler draw;
    private static Game game;
    public static int width = 1000;
    public static int height = 1000;

    
    public GameThread(){
        this(1000, 1000, false, true);
    }

    public GameThread(boolean pIsServer, boolean pIsClient) {
        this(1000,1000,pIsServer, pIsClient);
    }

    public GameThread(int width, int height, boolean pIsServer, boolean pIsClient){
        this(width, height, 60, pIsServer, pIsClient);
    }

    public GameThread(int pwidth, int pheight, int pframerate, boolean pIsServer, boolean pIsClient){
        isServer = pIsServer;
        isClient = pIsClient;

        width = pwidth;
        height = pheight;

        framerate = pframerate;
        interval = 1000/framerate;

        this.init();
        this.run();
    }
    
    public static void stop(){
        isRunning = false;
        if(isClient){
            draw.stop();
        }
        if(isClient && !isServer){
            Client.getClient().stopClient();
        }
        if(!isClient && isServer){
            Server.getServer().stopServer();
        }

    }

    public void init(){

        draw = new DrawingHandler();

        game = new Game();

        draw.init();

        game.init();
    }

    protected void update(long frame){
        MovementHandler.handler.update(frame);
        draw.update(frame);
        if(isServer){
            game.update(frame);
        }

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
            if(GameThread.isClient){
                if(KeyboardManager.getListener().isKeyPressed('\u001B')){
                    stop();
                }
            }
        }
    }

    public static long getFrame(){
        return frame;
    }

    public static void main(String args[]){
        if(args.length > 0) {
            if (args[0].contains("server")) {
                new GameThread(true, false);
            }
            if (args[0].contains("client")){
                new GameThread(false, true);
            }
        } else {
            new GameThread(true, true);
        }
    }

    public static void registerObject(Drawable obj) {
        Game.registerUpdatable(obj);

        DrawingHandler.registerDrawable(obj);

    }

    public static void registerObject(Updatable obj) {
        Game.registerUpdatable(obj);
    }

    public static void removeObject(Drawable obj) {
        Game.removeUpdatable(obj);

        DrawingHandler.removeDrawable(obj);

    }

    public static void removeObject(Updatable obj) {
        Game.removeUpdatable(obj);
    }



}

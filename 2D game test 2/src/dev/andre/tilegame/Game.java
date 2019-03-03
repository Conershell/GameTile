package dev.andre.tilegame;

import dev.andre.tilegame.display.Display;
import dev.andre.tilegame.gfx.Assets;
import dev.andre.tilegame.gfx.GameCamera;
import dev.andre.tilegame.gfx.ImageLoader;
import dev.andre.tilegame.gfx.SpriteSheet;
import dev.andre.tilegame.input.KeyManager;
import dev.andre.tilegame.states.GameState;
import dev.andre.tilegame.states.MenuState;
import dev.andre.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;

public class Game implements Runnable{ //implementa el Runnable para poder correrlo con threads

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    public Thread thread; //Creamos el objeto thread

    private BufferStrategy bs; //buffer de canvas
    private Graphics g; //lo q usamos para dibujar

    //STATES
    private State gameState;
    private State menuState;


    //INPUT
    private KeyManager keyManager;


    //CAMERA
    private GameCamera gameCamera;


    //HANDLER
    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        //Assets.init(); //carga manualmente las fotos
        Assets.initAuto(500,200); //carga automaticamente en el arraylist y se le pasa las dimensiones de la imagen merged

        gameCamera = new GameCamera(this,0,0);

        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }



    private void tick(){
        keyManager.tick();
        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){ //si es que no hay buffers creados, lo creamos :P
            display.getCanvas().createBufferStrategy(3); //aqui estamos creando 3
            return;
        }
        g = bs.getDrawGraphics();
        //CLEAR THE SCREEN FIRST
        g.clearRect(0,0, width, height);

        //DIBUJA AQUI
        if(State.getState() != null){
            State.getState().render(g);
        }

        //DEJA DE DIBUJAR
        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 60; //cantidad de veces que queremos llamar las funciones tick() y render() en cada segundo
        double timePerTick = 1000000000 / fps; //1 segundo son 1000000000 nanosegundos, timepo maximo en nanosegundos que tenemos para ejecutar los tick() y render()
        double delta = 0; //tiempo faltante para llamar el tick() y render() de nuevo
        long now;
        long lastTime = System.nanoTime(); //retorna el timepo actual del pc en nanosegundos
        long timer = 0; //tiene que llegar a 1 segundo, ahi se ve cuantos ticks tuvimos en un segundo
        int ticks = 0;

        while(running){ //cicla el juego, modifica valores-variables-etc, luego renderiza canvas-etc
            now = System.nanoTime(); //seteamos el timepo actual del pc en nanosegundos
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks ++;
                delta --;
            }

            //cada segundo imprimimos los fps, opcional
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0; //los reiniciamos para que nos muestre cada frame actualizado
                timer = 0;
            }
        }
        stop(); //para terminar el thread en caso que no lo termino
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start(){
        if(running){ //verificar que el juego no esta corriendo cuando se ejecuta
            return;
        }
        running = true;
        thread = new Thread(this); //inicilizamos el thread con esta clase; Game
        thread.start(); //Esto llama a la funcion run de arriba
    }
    public synchronized void stop(){
        if(!running){
            return; //verifica que el juego ya esta cerrado, para no cerrarlo de nuevo y evitar erroes
        }
        running = false;
        try {
            thread.join(); //tiene que ir en un try/catch
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

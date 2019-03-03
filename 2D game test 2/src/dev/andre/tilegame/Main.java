package dev.andre.tilegame;

import dev.andre.tilegame.display.Display;

public class Main {

    public static void main(String[] args) {
        Game game = new Game("Titulo Prueba del juego", 1000, 900); //guardamos el objeto Game en una variable de tipo Game llamada game
        game.start();
        System.out.println("FUNCIONA!");
    }
}

package dev.andre.tilegame.states;

import dev.andre.tilegame.Game;
import dev.andre.tilegame.Handler;
import dev.andre.tilegame.entities.creatures.Player;
import dev.andre.tilegame.gfx.Assets;
import dev.andre.tilegame.tiles.Tile;
import dev.andre.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State{

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/worldtest1.txt");
        handler.setWorld(world);
        player = new Player(handler,100,100);

    }

    public void tick(){
        world.tick();
        player.tick();
    }

    public void render(Graphics g){
        world.render(g);
        player.render(g);
    }
}

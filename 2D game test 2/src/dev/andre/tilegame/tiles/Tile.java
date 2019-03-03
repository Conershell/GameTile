package dev.andre.tilegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //STATIC STUFF HERE

    //Aqui hay que agregar cada tile para tener una instancia guardada en el array 'tiles'
    public static Tile[] tiles = new Tile[256];
    public static Tile cobblestoneTile = new CobblestoneTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile grassTile = new GrassTile(2);
    public static Tile redstoneOreTile = new RedstoneOreTile(3);
    public static Tile stoneTile = new StoneTile(4);


    //CLASS

    public static final int TILEWIDTH = 50, TILEHEIGHT = 50;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){


    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
    }

    public boolean isWalkable(){
        return true;
    }

    public int getId(){
        return id;
    }
}

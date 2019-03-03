package dev.andre.tilegame.tiles;

import dev.andre.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class RedstoneOreTile extends Tile {
    public RedstoneOreTile(int id) {
        super(Assets.fondo.get(5), id);
    }

    @Override
    public boolean isWalkable(){
        return false;
    }
}

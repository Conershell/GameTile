package dev.andre.tilegame.tiles;

import dev.andre.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class StoneTile extends Tile{

    public StoneTile(int id) {
        super(Assets.fondo.get(6), id);
    }

    @Override
    public boolean isWalkable(){
        return false;
    }
}

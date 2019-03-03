package dev.andre.tilegame.tiles;

import dev.andre.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class GrassTile extends Tile{

    public GrassTile(int id) {
        super(Assets.fondo.get(4), id);
    }
}

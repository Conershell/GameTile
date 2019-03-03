package dev.andre.tilegame.tiles;

import dev.andre.tilegame.gfx.Assets;

import java.awt.image.BufferedImage;

public class DirtTile extends Tile {

    public DirtTile(int id) {
        super(Assets.fondo.get(2), id);
    }
}

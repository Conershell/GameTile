package dev.andre.tilegame.worlds;

import dev.andre.tilegame.Handler;
import dev.andre.tilegame.tiles.Tile;
import dev.andre.tilegame.utils.Utils;


import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    public World(Handler handler, String path){
        this.handler = handler;
        loadWorld(path);
    }

    public void tick(){}

    public void render(Graphics g){
        //AQUI RENDERIZAMOS SOLO LO QUE VEMOS EN PANTALLA, Y ESO LO DEFINE LOS MOVIMIENTOS DE LA CAMARA
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        //FIJA DESDE QUE TILE RENDERIZAR, PARA ESO AGARRA EL TOTAL DE PIXELES QUE SE VE EN LA CAMARA Y LA DIVIDE EN EL TAMANO DE LAS TILES PARA OBTENER LA CANTIDAD DE TILES.
        //SI ES NEGATIVO EL RESULTADO SE USA LA POSICION 0, YA QUE TO DO EL JUEGO EMPIEZA A RENDERIZARSE DESDE EL 0,0
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        //FUNCIONA IGUAL QUE EL DEL MAXIMO, PERO ESTE DEFINE LA ULTIME TILE A RENDERIZAR DE LA ZONA DERECHA DE LA PANTALLA
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        //AQUI RENDERIZAMOS BLOQUE POR BLOQUE, PERO SOLO DE LO Q SE VE EN LA CAMARA
        for(int y = yStart; y < yEnd; y++){
            for (int x = xStart; x < xEnd; x++){
                getTile(x,y).render(g,(int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y){
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null){
            return Tile.stoneTile;
        }
        return t;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y =0; y < height; y++){
            for (int x = 0; x < width; x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }
}

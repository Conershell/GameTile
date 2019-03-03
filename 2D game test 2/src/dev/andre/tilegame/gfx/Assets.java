package dev.andre.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {

    private static final int width = 100, height = 100; //dimensiones en pixeles de cada imagen

    public static BufferedImage cobblestone, stone, dirt, grass, diamond, emerald, redstoneOre, player, enemy, robot;
    public static ArrayList<BufferedImage> fondo = new ArrayList<BufferedImage>();
    private static String path = "/textures/texturegrip1.jpg";


    //la funcion init() te corta a mano las fotos del merged
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path)); //hay q especificar la foto merged
        cobblestone = sheet.crop(0,0,width,height);
        diamond = sheet.crop(width,0,width,height);
        dirt = sheet.crop(2*width,0,width,height);
        emerald = sheet.crop(3*width,0,width,height);
        grass = sheet.crop(4*width,0,width,height);
        redstoneOre = sheet.crop(0,height,width,height);
        stone = sheet.crop(width,height,width,height);
        player = sheet.crop(2*width,height,width,height);

    }

    //la funcion initImages te corta automaticamente todas las fotos recibiendo el tamano de las fotos merged y las guarda en un arraylist publico
    public static void initAuto (int ancho, int altura){ //los parametros son las dimensiones de las fotos juntas (merged)
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path)); //hay q especificar la foto merged
        for (int b = 0; b < altura; b += height) { //height es la altura de cada imagen
            for (int a = 0; a < ancho; a += width) { //width es el ancho de cada imagen
                fondo.add(sheet.crop(a,b,width,height));
            }
        }
    }
}

/*
 * Universidad del Valle de Guatemala 
 * Francisco Molina Jimenez - 17050
 * Proyecto final Graficas
 * DISCLAIMER: Este proyecto fue realizado gracias a la instruccion de: https://www.instructables.com/id/Making-a-Basic-3D-Engine-in-Java/
 */

/**
 * Codigo typeado y modificado por 
 * @author: Francisco Molina
 * Idea original
 * @author: sheeptheelectric
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureManager {
    public int[] pixels;
    private final String path;
    public final int SIZE;
    
    
    //constructor..
    public TextureManager(String pathParam, int size){
        path = pathParam;
        SIZE = size;
        //asumiendo textura completamente cuadrada..
        pixels = new int[SIZE * SIZE];
        
        load();
    }
    /*
    Cargamos imagen de textura.
    */
    private void load(){
        try{
            
            //Clase ImageIO fue actualizada, ahora se accesa asi, segun: https://stackoverflow.com/questions/9864267/loading-image-resource/9866659#9866659
            BufferedImage textura = ImageIO.read(getClass().getResource(path));
            int ancho = textura.getWidth();
            int alto = textura.getHeight();
            textura.getRGB(0, 0, ancho, alto, pixels, 0, ancho);
        } catch(IOException e){
            System.out.println("Error: El path proveido no es una imagen o no existe.");
            e.printStackTrace();
        }
    }
    
    /**
     * Aqui se declaran las texturas a utilizar, tienen que ser PUBLIC.
     */
    
    public static TextureManager wall1 = new TextureManager("assets/wall1A.png", 64);
    public static TextureManager wall2 = new TextureManager("assets/wall2A.png",64);
    public static TextureManager wall3 = new TextureManager("assets/wall3A.png",64);
    public static TextureManager wallDoor = new TextureManager("assets/wallDoor.png",64);
    public static TextureManager imperial = new TextureManager("assets/imperial.png",64);
    public static TextureManager imperial2 = new TextureManager("assets/imperial2.png",64);
    public static TextureManager imperial3 = new TextureManager("assets/imperial3.png",64);
    
    public static TextureManager wall1Sith = new TextureManager("assets/wall1Sith.png",64);
    public static TextureManager wallSith2 = new TextureManager("assets/wall2Sith.png",64);
    public static TextureManager action = new TextureManager("assets/action1.png",64);
    public static TextureManager action2 = new TextureManager("assets/action2.png",64);
    public static TextureManager action3 = new TextureManager("assets/action3.png",64);


    

    
    //enemy
    public static TextureManager emperor = new TextureManager("assets/emperor.png",64);
    public static TextureManager vader = new TextureManager("assets/vader.png",64);
    
    public static TextureManager revan = new TextureManager("assets/revan.png",64);
    public static TextureManager logo = new TextureManager("assets/sw.png",64);
}

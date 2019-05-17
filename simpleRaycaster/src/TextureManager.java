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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureManager {
    public int[] pixels;
    private String path;
    public final int SIZE;
    
    
    //constructor..
    public TextureManager(String path, int size){
        this.path = path;
        this.SIZE = size;
        //asumiendo textura completamente cuadrada..
        this.pixels = new int[this.SIZE * this.SIZE];
        
        load();
    }
    /*
    Cargamos imagen de textura.
    */
    private void load(){
        try{
            BufferedImage textura = ImageIO.read(new File(path));
            int ancho = textura.getWidth();
            int alto = textura.getHeight();
        } catch(IOException e){
            System.out.println("Error: El path proveido no es una imagen o no existe.");
            e.printStackTrace();
        }
    }
    
    /**
     * Aqui se declaran las texturas a utilizar, tienen que ser PUBLIC.
     */
    
    
    
    
}

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

import java.util.ArrayList;
import java.awt.Color;

public class RendererManager {
    public int[][] mapa;
    public int AnchoMapa, AltoMapa, ancho, alto;
    public ArrayList texturas;
    
    public RendererManager(int[][] mapa, ArrayList textura, int ancho, int alto){
        this.mapa = mapa;
        this.texturas = textura;
        this.ancho = ancho;
        this.alto = alto;
    }
    public int[] update(Camara camara, int[] pixeles){
        for(int n=0; n<pixeles.length/2; n++){
            if(pixeles[n] != Color.DARK_GRAY.getRGB()){
                pixeles[n] = Color.DARK_GRAY.getRGB();
            }
            for(int i = pixeles.length/2; i<pixeles.length; i++){
                if(pixeles[i] != Color.gray.getRGB()){
                    pixeles[i] = Color.gray.getRGB();
                }
            }
        }
    }
}

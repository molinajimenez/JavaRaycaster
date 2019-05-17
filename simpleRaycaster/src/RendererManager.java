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
        
        for(int x=0; x<ancho; ++x){
            double camaraX = 2 * x / (double)(ancho) - 1;
            double raycastDir_X = camara.xAxis + camara.xPlano * camaraX;
            double raycastDir_Y = camara.yAxis + camara.yPlano * camaraX;
            
            //Donde estamos parados..
            int mapX = (int)camara.xPos;
            int mapY = (int)camara.yPos;
            
            /**
             * Obtenemos magnitud del vector de raycast
             * distancia hacia los lados..
             */
            double distX;
            double distY;
            double deltaX = Math.sqrt(1 + (raycastDir_Y*raycastDir_Y)/ (raycastDir_X*raycastDir_X));
            double deltaY = Math.sqrt(1 + (raycastDir_X*raycastDir_X)/ (raycastDir_Y*raycastDir_Y));
            double lengthWall;
            
            int pasoX, pasoY;
            //colisionador
            boolean golpe = false;
            int lado = 0; 
            
            if(raycastDir_X<0){
                pasoX= -1;
                distX = (camara.xPos - mapX)*deltaX;
            } else{
                pasoX = 1;
                distX = (mapX + 1.0 - camara.xPos)*deltaX;
            }
            if(raycastDir_Y<0){
                pasoY= -1;
                distY = (camara.yPos - mapY)*deltaY;
            } else{
                pasoY = 1;
                distY = (mapY + 1.0 - camara.yPos)*deltaY;
            }
            
            //chequeamos si el raycast choca con una pared..
            while(!golpe){
                if(distX<distY){
                    distX += deltaX;
                    mapX += pasoX;
                    lado = 0;
                }else{
                    distY = deltaY;
                    mapY += pasoY;
                    lado = 1;
                }
                //chequeamos si le pega a una pared..
                if(mapa[mapX][mapY]>0){
                    golpe = true;
                }
            }
            //distancia en JUGADOR-impacto..
            if(lado==0){
                lengthWall = Math.abs((mapX - camara.xPos + (1 - pasoX) /2 ) / raycastDir_X);
                
            } else{
                lengthWall = Math.abs((mapY - camara.yPos + (1 - pasoY) /2 ) / raycastDir_Y);
            }
            
            //algoritmo de raycast calcula altura en base a distancia frente a pared..
            int alturaP;
            if (lengthWall >0){
                alturaP =  Math.abs((int)(alto / lengthWall));
             
            } else{
                alturaP = alto;
            }
            
            int start = -alturaP/2 + alto/2;
            if(start<0){
                start = 0;
            }
            int end = alturaP/2 + alto/2;
            if(end>=alto){
                end = alto -1;
            }
            
        }
    }
}

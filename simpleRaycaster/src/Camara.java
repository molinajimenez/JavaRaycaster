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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Camara implements KeyListener {
    public double xPos, yPos, xAxis, yAxis, xPlano, yPlano;
    public boolean izq, derecha, adelante, atras;
    public final double VELOCIDAD = .08;
    public final double ROTACION = .045;
    
    public Camara(double x, double y, double xDir, double yDir, double xP, double yP ){
        this.xPos = x;
        this.yPos = y;
        this.xAxis = xDir;
        this.yAxis = yDir;
        this.xPlano = xP;
        this.yPlano = yP;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if((e.getKeyCode()== KeyEvent.VK_LEFT)){
            izq = true;
        }
        if((e.getKeyCode()== KeyEvent.VK_RIGHT)){
            derecha = true;
        }
        if((e.getKeyCode()== KeyEvent.VK_UP)){
            adelante = true;
        }
        if((e.getKeyCode()== KeyEvent.VK_DOWN)){
            atras = true;
        }   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if((e.getKeyCode()== KeyEvent.VK_LEFT)){
            izq = false;
        }
        if((e.getKeyCode()== KeyEvent.VK_RIGHT)){
            derecha = false;
        }
        if((e.getKeyCode()== KeyEvent.VK_UP)){
            adelante = false;
        }
        if((e.getKeyCode()== KeyEvent.VK_DOWN)){
            atras = false;
        }
    }
    
    
}
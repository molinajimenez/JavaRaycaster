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


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;
public class Main extends JFrame implements Runnable {
    
    private static final long serialVersionUID = 1L;
    public int anchoMapa = 15;
    public int altoMapa = 15;
    //pasemonos de caqueros..
    private Thread thread;
    private boolean jugando;
    private BufferedImage imagen;
    public int[] pixeles;
    public ArrayList<TextureManager> texturas;
    public Camara camara;
    public RendererManager rm;
    public static int[][] mapa=
        {
            {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
            {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
            {1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
            {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
            {1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
            {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
            {1,0,3,3,0,3,3,0,2,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
            {1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
            {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
            {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
            {1,0,0,2,0,0,1,4,0,3,3,3,3,0,4},
            {1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
            {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
	};
    
    public Main(){
        
        //instancia de variables 
        thread = new Thread(this);
        imagen = new BufferedImage(640,480, BufferedImage.TYPE_INT_RGB);
        pixeles = ((DataBufferInt)imagen.getRaster().getDataBuffer()).getData();
        //Resolucion chafa para mas performance..
        setSize(640,480);
        setResizable(false);
        setTitle("Proyecto - Graficas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.MAGENTA);
        setLocationRelativeTo(null);
        
        //texturas..
        texturas = new ArrayList<>();
        
        
        camara = new Camara(4.5,4.5,1,0,0,-0.66);
        rm = new RendererManager(mapa, anchoMapa, altoMapa,texturas,640, 480);
        setVisible(true);
        
        start();
    }
    
    private synchronized void start(){
        jugando = true;
        thread.start();
    }
    
    private synchronized void stop(){
        jugando = false;
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void render(){
        //esto hace el pygame.flip() cambia escena mientras se renderiza algo..
        BufferStrategy bs = getBufferStrategy();
        
        if (bs == null){
            //usaremos triple buffering..
            createBufferStrategy(3);
            return;
        }
        //proceso de renderizado.. no es tan automatico como flip
        Graphics processed = bs.getDrawGraphics();
        processed.drawImage(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), null);
	bs.show();
    }
    @Override
    public void run() {
        long ultimoRefresh = System.nanoTime();
        //pensemos que es un bonito Vsync jaja
        final double refreshRate = 1000000000.0 / 60.0; //target -> 60fps, no mas.
        double delta = 0;
        // solo para que la pantalla este activa.
        requestFocus();
        while(jugando){
            long ahora = System.nanoTime();
            delta = delta + ((ahora-ultimoRefresh)) / refreshRate;
            ultimoRefresh = ahora;
            while (delta >= 1){ // no hacemos nada mas que esperar..
                rm.update(camara, pixeles);
                camara.update(mapa);
                //mas optimizado..
                --delta;
            }
            
            render();
        }
    }
    
    public static void main(String [] args){
        Main main = new Main();
        
    }
    
}

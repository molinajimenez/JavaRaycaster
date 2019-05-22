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
	public int[][] map;
	public int mapWidth, mapHeight, width, height;
	public ArrayList<TextureManager> textures;
	
	public RendererManager(int[][] m, int mapW, int mapH, ArrayList<TextureManager> tex, int w, int h) {
		map = m;
		mapWidth = mapW;
		mapHeight = mapH;
		textures = tex;
		width = w;
		height = h;
	}
	
	public int[] update(Camara camera, int[] pixels) {
		for(int n=0; n<pixels.length/2; n++) {
			if(pixels[n] != Color.DARK_GRAY.getRGB()) pixels[n] = Color.DARK_GRAY.getRGB();
		}
		for(int i=pixels.length/2; i<pixels.length; i++){
			if(pixels[i] != Color.gray.getRGB()) pixels[i] = Color.gray.getRGB();
		}
	    
	    for(int x=0; x<width; x=x+1) {
                    double cameraX = 2 * x / (double)(width) -1;
		    double rayDirX = camera.xAxis + camera.xPlano * cameraX;
		    double rayDirY = camera.yAxis + camera.yPlano * cameraX;
		    //Map position
		    int mapX = (int)camera.xPos;
		    int mapY = (int)camera.yPos;
		    //length of ray from current position to next x or y-side
		    double sideDistX;
		    double sideDistY;
		    //Length of ray from one side to next in map
		    double deltaDistX = Math.sqrt(1 + (rayDirY*rayDirY) / (rayDirX*rayDirX));
		    double deltaDistY = Math.sqrt(1 + (rayDirX*rayDirX) / (rayDirY*rayDirY));
		    double perpWallDist;
		    //Direction to go in x and y
		    int stepX, stepY;
		    boolean hit = false;//was a wall hit
		    int side=0;//was the wall vertical or horizontal
		    //Figure out the step direction and initial distance to a side
		    if (rayDirX < 0)
		    {
		    	stepX = -1;
		    	sideDistX = (camera.xPos - mapX) * deltaDistX;
		    }
		    else
		    {
		    	stepX = 1;
		    	sideDistX = (mapX + 1.0 - camera.xPos) * deltaDistX;
		    }
		    if (rayDirY < 0)
		    {
		    	stepY = -1;
		        sideDistY = (camera.yPos - mapY) * deltaDistY;
		    }
		    else
		    {
		    	stepY = 1;
		        sideDistY = (mapY + 1.0 - camera.yPos) * deltaDistY;
		    }
		    //Loop to find where the ray hits a wall
		    while(!hit) {
		    	//Jump to next square
		    	if (sideDistX < sideDistY)
		        {
		    		sideDistX += deltaDistX;
		    		mapX += stepX;
		    		side = 0;
		        }
		        else
		        {
		        	sideDistY += deltaDistY;
		        	mapY += stepY;
		        	side = 1;
		        }
		    	
		    	//System.out.println(mapX + ", " + mapY + ", " + map[mapX][mapY]);
                        
		    	if(map[mapX][mapY] > 0) hit = true;
		    }
		    //Calculo de distancia entre golpes
		    if(side==0)
		    	perpWallDist = Math.abs((mapX - camera.xPos + (1 - stepX) / 2) / rayDirX);
		    else
		    	perpWallDist = Math.abs((mapY - camera.yPos + (1 - stepY) / 2) / rayDirY);	
		    //calculo de altura de pared
		    int lineHeight;
		    if(perpWallDist > 0) lineHeight = Math.abs((int)(height / perpWallDist));
		    else lineHeight = height;
		    
		    int drawStart = -lineHeight/2+ height/2;
		    if(drawStart < 0)
		    	drawStart = 0;
		    int drawEnd = lineHeight/2 + height/2;
		    if(drawEnd >= height) 
		    	drawEnd = height - 1;
		    //add a texture
		    int texNum = map[mapX][mapY] - 1;
		    double wallX;//donde golpeo el ray
		    if(side==1) {
		    	wallX = (camera.xPos + ((mapY - camera.yPos + (1 - stepY) / 2) / rayDirY) * rayDirX);
		    } else {
		    	wallX = (camera.yPos + ((mapX - camera.xPos + (1 - stepX) / 2) / rayDirX) * rayDirY);
		    }
		    wallX-=Math.floor(wallX);
		    //coord
		    int texX = (int)(wallX * (textures.get(texNum).SIZE));
		    if(side == 0 && rayDirX > 0) texX = textures.get(texNum).SIZE - texX - 1;
		    if(side == 1 && rayDirY < 0) texX = textures.get(texNum).SIZE - texX - 1;
		    //coord
		    for(int y=drawStart; y<drawEnd; y++) {
		    	int texY = (((y*2 - height + lineHeight) << 6) / lineHeight) / 2;
		    	int color;
		    	if(side==0) color = textures.get(texNum).pixels[texX + (texY * textures.get(texNum).SIZE)];
		    	else color = (textures.get(texNum).pixels[texX + (texY * textures.get(texNum).SIZE)]>>1) & 8355711;//truco de shader.. segun autor..
		    	pixels[x + y*(width)] = color;
		    }
		}
		return pixels;
	}
}
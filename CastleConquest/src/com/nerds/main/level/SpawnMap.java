package com.nerds.main.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.nerds.main.level.tiles.GrassTile;
import com.nerds.main.level.tiles.Tile;
import com.nerds.main.level.tiles.VoidTile;
import com.nerds.main.level.tiles.WallTile;

public class SpawnMap extends Map{

	public SpawnMap(String path){
		super(path);
		loadLevel(path);
	}
	
	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(SpawnMap.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tilesInt = new int[w*h];
			tiles = new Tile[w*h];
			image.getRGB(0, 0, w, h, tilesInt, 0, w);
			toTile();
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("ERROR COULD NOT LOAD LEVEL: " + path);
		}
	}
	
	protected void toTile(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int i = x + y * width;
				int col = tilesInt[i];
				
				if(col == GrassTile.colorCode)
					tiles[i] = new GrassTile(x, y);
				else if(col == WallTile.colorCode)
					tiles[i] = new WallTile(x, y);
				else
					tiles[i] = new WallTile(x,y);
			}
		}
	}
}

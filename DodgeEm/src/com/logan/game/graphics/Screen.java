package com.logan.game.graphics;

import java.util.Random;

public class Screen {
	
	private int width, height;
	public int[] pixels;
	
	public final int MAP_SIZE = 64;
	public final int TILE_SIZE = 32;
	public int[] tiles;
	private Random rand;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		tiles = new int[MAP_SIZE * MAP_SIZE];
		
		rand = new Random();
		for(int i = 0; i < tiles.length; i++){
			tiles[i] = rand.nextInt(0x111111) + 0xeeeeee;
		}
		
	}
	
	public void fillRect(int x, int y, int width, int height, int col){
		for(int yy = 0; yy < height; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >=this.height) continue;
			for(int xx = 0; xx < width; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= this.width) continue;
				if(col != 0xff00ff)
					pixels[xp + yp * this.width] = col;
			}
		}
	}
	
	public void renderSprite(int x, int y, Sprite sprite){
		for(int yy = 0; yy < sprite.SIZE; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >=height) continue;
			for(int xx = 0; xx < sprite.SIZE; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= width) continue;
					int col = sprite.pixels[xx + yy * sprite.SIZE];
					if(col != 0xffff00ff && col != 0xffaa00aa)
						pixels[xp + yp *width] = col;
			}
		}
	}
	
	public void renderTiles(int xOffset, int yOffset){
		for(int y = 0; y < MAP_SIZE; y++){
			for(int x = 0; x < MAP_SIZE; x++){
				fillRect(x*TILE_SIZE - xOffset, y*TILE_SIZE - yOffset, TILE_SIZE, TILE_SIZE, tiles[x + y * MAP_SIZE]);
			}
		}
	}
	
	public void drawRect(int x, int y, int width, int height, int col){
		for(int yy = 0; yy < height; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >=this.height) continue;
			for(int xx = 0; xx < width; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= this.width) continue;
					
				if(xx == 0 || xx== width-1 || yy==0 || yy == height-1)
					pixels[xp + yp * this.width] = col;
			}
		}
	}
	
	public void drawOffsetRect(int x, int y, int width, int height, int col, int xOff, int yOff){
		for(int yy = 0; yy < height; yy++){
			int yp = yy + y - yOff;
			if(yp < 0 || yp >=this.height) continue;
			for(int xx = 0; xx < width; xx++){
				int xp = xx + x - xOff;
				if(xp < 0 || xp >= this.width) continue;
					
				if(xx == 0 || xx== width-1 || yy==0 || yy == height-1)
					pixels[xp + yp * this.width] = col;
			}
		}
	}
	
	public void clear(int col){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = col;
		}
	}

}

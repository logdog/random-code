package com.logan.game.graphics;

//import java.awt.Color;
//import java.util.Random;

public class Screen {

	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	//private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];

		//for (int i = 0; i < tiles.length; i++) {
		//	tiles[i] = random.nextInt(0xffffff);
			//System.out.println(tiles[i]);
		//}
	}
	

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff00ff00;
		}
	}

	public void renderCard(int xPos, int yPos, Sprite sprite) { //for 32x sprites
		for(int y = 0; y < sprite.SIZE; y++){
			int yp = y + yPos;
			int ys = sprite.SIZE - 1 - y;
			if(yp < 0 || yp >= height) continue;
			for(int x = 0; x < sprite.SIZE; x++){
				int xp = x + xPos;
				int xs = sprite.SIZE - 1 - x;
				if(xp < 0 || xp >= width) continue;
				if(sprite.pixels[x + y * sprite.SIZE] != 0xffff00ff && sprite.pixels[x + y * sprite.SIZE] != 0xffaa00aa){
				pixels[xp + yp * width] = sprite.pixels[x + y * sprite.SIZE];
				if(yp + sprite.SIZE >= height) continue;
				pixels[xp + (yp + 32) * width] = sprite.pixels[xs + ys * sprite.SIZE];
				}
			}
		}
	}
	
	public void renderLetter(int xPos, int yPos, Sprite sprite, int color){
		for(int y = 0; y < sprite.SIZE; y++){ 
			int yp = y + yPos;
			if(yp < 0 || yp >= height) continue;
			for(int x = 0; x < sprite.SIZE; x++){
				int xp = x + xPos;
				if(xp < 0 || xp >=width)continue;
				if(sprite.pixels[x + y * sprite.SIZE] != 0xffff00ff && sprite.pixels[x + y * sprite.SIZE] != 0xffaa00aa){
					sprite.pixels[x + y * sprite.SIZE] = color;
					pixels[xp + yp*width] = sprite.pixels[x + y * sprite.SIZE];
				}
				
			}
		}
	}
	

	
}

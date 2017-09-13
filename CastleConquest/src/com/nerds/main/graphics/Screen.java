package com.nerds.main.graphics;

import java.util.Random;

import com.nerds.main.level.SpawnMap;

public class Screen {

	private int width, height, tileSize = 16;

	public int[] pixels;

	Random rand = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void fillRect(int x, int y, int w, int h, int col) {
		for (int yy = 0; yy < h; yy++) {
			int yp = yy + y;
			if (yp < 0 || yp >= height)
				continue;
			for (int xx = 0; xx < w; xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width)
					continue;

				if (col != 0xff00ff)
					pixels[xp + yp * width] = col;
			}
		}
	}

	
	public void renderMap(int camX, int camY, SpawnMap spawnMap){
		for(int yy = 0; yy < spawnMap.height; yy++){
			int yp = (yy * tileSize - camY);
			if(yp < -tileSize || yp >= height) continue;
			for(int xx = 0; xx < spawnMap.width; xx++){
				int xp = (xx * tileSize - camX);
				if(xp < -tileSize || xp >= width) continue;
			
				renderSprite(xp, yp, spawnMap.tiles[xx + yy * spawnMap.width].getSprite());
			}
		}
	}
	
	public void drawRect(int x, int y, int w, int h, int col){
		for (int yy = 0; yy < h; yy++) {
			int yp = yy + y;
			if (yp < 0 || yp >= height)
				continue;
			for (int xx = 0; xx < w; xx++) {
				int xp = xx + x;
				if (xp < 0 || xp >= width)
					continue;
				
				if(xx == 0 || yy == 0 || xx == w-1 || yy == h-1)
					pixels[xp + yp * width] = col;
			}
		}
	}
	
	public void renderSprite(int x, int y, Sprite sprite){
		for(int yy = 0; yy < sprite.SIZE; yy++){
			int yp = yy + y;
			if(yp < 0 || yp >= height) continue;
			for(int xx = 0; xx < sprite.SIZE; xx++){
				int xp = xx + x;
				if(xp < 0 || xp >= width) continue;
				
				
				int col = sprite.pixels[xx + yy * sprite.SIZE];
				
				if(col == 0xffff00ff || col == 0xffaa00aa)
					;else
					pixels[xp + yp * width] = col;
			}
		}
	}
	
	public void renderText(String message, int x, int y) {

		String str = message.toUpperCase();
		x -= str.length() * 3;
		y -= 3;

		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'A':
				renderSprite(x + i * 6, y, Sprite.smallA);
				break;
			case 'B':
				renderSprite(x + i * 6, y, Sprite.smallB);
				break;
			case 'C':
				renderSprite(x + i * 6, y, Sprite.smallC);
				break;
			case 'D':
				renderSprite(x + i * 6, y, Sprite.smallD);
				break;
			case 'E':
				renderSprite(x + i * 6, y, Sprite.smallE);
				break;
			case 'F':
				renderSprite(x + i * 6, y, Sprite.smallF);
				break;
			case 'G':
				renderSprite(x + i * 6, y, Sprite.smallG);
				break;
			case 'H':
				renderSprite(x + i * 6, y, Sprite.smallH);
				break;
			case 'I':
				renderSprite(x + i * 6, y, Sprite.smallI);
				break;
			case 'J':
				renderSprite(x + i * 6, y, Sprite.smallJ);
				break;
			case 'K':
				renderSprite(x + i * 6, y, Sprite.smallK);
				break;
			case 'L':
				renderSprite(x + i * 6, y, Sprite.smallL);
				break;
			case 'M':
				renderSprite(x + i * 6, y, Sprite.smallM);
				break;
			case 'N':
				renderSprite(x + i * 6, y, Sprite.smallN);
				break;
			case 'O':
				renderSprite(x + i * 6, y, Sprite.smallO);
				break;
			case 'P':
				renderSprite(x + i * 6, y, Sprite.smallP);
				break;
			case 'Q':
				renderSprite(x + i * 6, y, Sprite.smallQ);
				break;
			case 'R':
				renderSprite(x + i * 6, y, Sprite.smallR);
				break;
			case 'S':
				renderSprite(x + i * 6, y, Sprite.smallS);
				break;
			case 'T':
				renderSprite(x + i * 6, y, Sprite.smallT);
				break;
			case 'U':
				renderSprite(x + i * 6, y, Sprite.smallU);
				break;
			case 'V':
				renderSprite(x + i * 6, y, Sprite.smallV);
				break;
			case 'W':
				renderSprite(x + i * 6, y, Sprite.smallW);
				break;
			case 'X':
				renderSprite(x + i * 6, y, Sprite.smallX);
				break;
			case 'Y':
				renderSprite(x + i * 6, y, Sprite.smallY);
				break;
			case 'Z':
				renderSprite(x + i * 6, y, Sprite.smallZ);
				break;
			case '0':
				renderSprite(x + i * 6, y, Sprite.smallOne);
				break;
			case '2':
				renderSprite(x + i * 6, y, Sprite.smallTwo);
				break;
			case '3':
				renderSprite(x + i * 6, y, Sprite.smallThree);
				break;
			case '4':
				renderSprite(x + i * 6, y, Sprite.smallFour);
				break;
			case '5':
				renderSprite(x + i * 6, y, Sprite.smallFive);
				break;
			case '6':
				renderSprite(x + i * 6, y, Sprite.smallSix);
				break;
			case '7':
				renderSprite(x + i * 6, y, Sprite.smallSeven);
				break;
			case '8':
				renderSprite(x + i * 6, y, Sprite.smallEight);
				break;
			case '9':
				renderSprite(x + i * 6, y, Sprite.smallNine);
				break;

			default:
				break;
			}
		}

	}
	
	public void renderVerticalText(String message, int x, int y) {

		String str = message.toUpperCase();
		x -= 3;
		y -= str.length() * 3;

		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case 'A':
				renderSprite(x, y + i * 6, Sprite.smallA);
				break;
			case 'B':
				renderSprite(x , y + i * 6, Sprite.smallB);
				break;
			case 'C':
				renderSprite(x, y + i * 6, Sprite.smallC);
				break;
			case 'D':
				renderSprite(x, y  + i * 6, Sprite.smallD);
				break;
			case 'E':
				renderSprite(x, y + i * 6, Sprite.smallE);
				break;
			case 'F':
				renderSprite(x , y + i * 6, Sprite.smallF);
				break;
			case 'G':
				renderSprite(x , y + i * 6, Sprite.smallG);
				break;
			case 'H':
				renderSprite(x , y + i * 6, Sprite.smallH);
				break;
			case 'I':
				renderSprite(x, y + i * 6, Sprite.smallI);
				break;
			case 'J':
				renderSprite(x, y + i * 6, Sprite.smallJ);
				break;
			case 'K':
				renderSprite(x , y + i * 6, Sprite.smallK);
				break;
			case 'L':
				renderSprite(x , y + i * 6, Sprite.smallL);
				break;
			case 'M':
				renderSprite(x , y + i * 6, Sprite.smallM);
				break;
			case 'N':
				renderSprite(x , y + i * 6, Sprite.smallN);
				break;
			case 'O':
				renderSprite(x , y + i * 6, Sprite.smallO);
				break;
			case 'P':
				renderSprite(x , y + i * 6, Sprite.smallP);
				break;
			case 'Q':
				renderSprite(x , y + i * 6, Sprite.smallQ);
				break;
			case 'R':
				renderSprite(x, y + i * 6, Sprite.smallR);
				break;
			case 'S':
				renderSprite(x , y + i * 6, Sprite.smallS);
				break;
			case 'T':
				renderSprite(x , y + i * 6, Sprite.smallT);
				break;
			case 'U':
				renderSprite(x , y + i * 6, Sprite.smallU);
				break;
			case 'V':
				renderSprite(x , y + i * 6, Sprite.smallV);
				break;
			case 'W':
				renderSprite(x, y + i * 6, Sprite.smallW);
				break;
			case 'X':
				renderSprite(x , y+ i * 6, Sprite.smallX);
				break;
			case 'Y':
				renderSprite(x, y+ i * 6, Sprite.smallY);
				break;
			case 'Z':
				renderSprite(x , y + i * 6, Sprite.smallZ);
				break;
			case '0':
				renderSprite(x , y+ i * 6, Sprite.smallOne);
				break;
			case '2':
				renderSprite(x , y+ i * 6, Sprite.smallTwo);
				break;
			case '3':
				renderSprite(x, y+ i * 6, Sprite.smallThree);
				break;
			case '4':
				renderSprite(x, y+ i * 6, Sprite.smallFour);
				break;
			case '5':
				renderSprite(x , y+ i * 6, Sprite.smallFive);
				break;
			case '6':
				renderSprite(x, y+ i * 6, Sprite.smallSix);
				break;
			case '7':
				renderSprite(x , y+ i * 6, Sprite.smallSeven);
				break;
			case '8':
				renderSprite(x , y+ i * 6, Sprite.smallEight);
				break;
			case '9':
				renderSprite(x , y+ i * 6, Sprite.smallNine);
				break;

			default:
				break;
			}
		}

	}


}

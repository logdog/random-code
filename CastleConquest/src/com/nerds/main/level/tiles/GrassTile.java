package com.nerds.main.level.tiles;

import com.nerds.main.graphics.Sprite;

public class GrassTile extends Tile{
	
	public static int colorCode = 0xffffff00; //yellow

	public GrassTile(int x, int y){
		super(16, x, y, Sprite.overGrassDay); //change
	}
	
}

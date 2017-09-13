package com.nerds.main.level.tiles;

import com.nerds.main.graphics.Sprite;

public class WoodTile extends Tile{
	
	public static int colorCode = 0xffffff00;

	public WoodTile(int x, int y) {
		super(16, x, y, Sprite.overWoodDay);
	}
	
	public boolean isSolid(){
		return true;
	}
}

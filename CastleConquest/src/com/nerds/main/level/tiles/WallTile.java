package com.nerds.main.level.tiles;

import com.nerds.main.graphics.Sprite;

public class WallTile extends Tile{
	
	public static int colorCode = 0xff0000ff;

	public WallTile(int x, int y) {
		super(16, x, y,Sprite.overWoodDay);
	}
	
	public boolean isSolid(){
		return true;
	}

}

package com.nerds.main.level.tiles;

import com.nerds.main.graphics.Sprite;

public class Tile {

	private Sprite sprite;
	public final int SIZE;
	public int x, y;
	
	public Tile(int size, int x, int y, Sprite sprite){
		SIZE = size;
		this.sprite = sprite;
		this.x=x;
		this.y=y;
	}

	public Sprite getSprite(){
		return sprite;
	}
	
	public boolean isSolid(){
		return false;
	}
	
}

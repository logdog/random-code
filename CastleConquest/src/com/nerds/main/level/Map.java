package com.nerds.main.level;


import com.nerds.main.level.tiles.Tile;

public class Map {
	
	public int[] tilesInt;
	public Tile[] tiles;
	public int width;
	public int height;
	
	public static SpawnMap overworld = new SpawnMap("/maps/Overworld.png");
	public static SpawnMap testAI = new SpawnMap("/maps/TestAI.png");

	public Map(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public Map(String path){
		
	}
	
	
}

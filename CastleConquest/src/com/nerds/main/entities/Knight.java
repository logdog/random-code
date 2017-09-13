package com.nerds.main.entities;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Screen;
import com.nerds.main.graphics.Sprite;

public class Knight extends Mob {

	public Knight(){
		super(70, 60, 40, 60, 3, new AnimatedMobSprite(Sprite.pink));
	}
	
	public Knight(int x, int y){
		super(x, y, 70, 60, 40, 60, 3, new AnimatedMobSprite(Sprite.pink));
	}
	
	public void move(int x, int y){
		
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
}

package com.nerds.main.entities;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Screen;
import com.nerds.main.graphics.Sprite;

public class Archer extends Mob{
	
	public Archer(){
		super(40, 50, 70, 40, 6, new AnimatedMobSprite(Sprite.pink));
	}
	
	public Archer(int x, int y){
		super(x, y, 40, 50, 70, 40, 6, new AnimatedMobSprite(Sprite.pink));
	}
	
	
	
}

package com.nerds.main.entities;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Sprite;

public class Wizard extends Mob{

	public Wizard(){
		super(60, 40, 60, 30, 1, new AnimatedMobSprite(Sprite.pink));
	}
	
	public Wizard(int x, int y){
		super(x, y, 60, 40, 60, 30, 1, new AnimatedMobSprite(Sprite.pink));
	}
	
	
	
}

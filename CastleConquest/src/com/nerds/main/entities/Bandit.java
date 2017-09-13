package com.nerds.main.entities;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Sprite;
import com.nerds.main.graphics.SpriteSheet;
import com.nerds.main.level.Level;

public class Bandit extends Mob{

	static int xShift = 4;
	
	public Bandit(){
		super(30, 70, 0, 30, 1, new AnimatedMobSprite(Sprite.pink));
	}
	
	public Bandit(int x, int y){
		super(x, y, 30, 70, 0, 30, 1, new AnimatedMobSprite(

				
				
				new Sprite(16, 2 + xShift, 0, SpriteSheet.mobs), 
				new Sprite(16, 0 + xShift, 0, SpriteSheet.mobs),	
				new Sprite(16, 1 + xShift, 0, SpriteSheet.mobs),

				new Sprite(16, 0 + xShift, 1, SpriteSheet.mobs), 
				new Sprite(16, 1 + xShift, 1, SpriteSheet.mobs),		
				new Sprite(16, 2 + xShift, 1, SpriteSheet.mobs),

				new Sprite(16, 0 + xShift, 2, SpriteSheet.mobs), 
				new Sprite(16, 1 + xShift, 2, SpriteSheet.mobs),
				new Sprite(16, 2 + xShift, 2, SpriteSheet.mobs),
				
				new Sprite(16, 0 + xShift, 3, SpriteSheet.mobs), 
				new Sprite(16, 1 + xShift, 3, SpriteSheet.mobs),
				new Sprite(16, 2 + xShift, 3, SpriteSheet.mobs)
				
				
				));
	}
	
	/*
	public void update(){ 
		if(Level.player.xPos > xPos)
			move(1,0);
		if(Level.player.xPos < xPos)
			move(-1,0);
		if(Level.player.yPos > yPos)
			move(0,1);
		if(Level.player.yPos < yPos)
			move(0,-1);
		
		xRel = xPos - Level.cam.x;
		yRel = yPos - Level.cam.y;
		
		animSprite.update(dir, moving);
	}
	*/
	
}

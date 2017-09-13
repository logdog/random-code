package com.nerds.main.entities;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Sprite;
import com.nerds.main.graphics.SpriteSheet;
import com.nerds.main.level.Level;

public class Player extends Mob {
	
	public int lastMeele = 0;
	public double stamina = 100, maxStamina = 100;
	public int magicPoints = 50, maxMagic = 50;

	public Player() {
		super(50, 50, 50, 50, 3, new AnimatedMobSprite(

				new Sprite(16, 2, 0, SpriteSheet.mobs), 
				new Sprite(16, 0, 0, SpriteSheet.mobs),	
				new Sprite(16, 1, 0, SpriteSheet.mobs),

				new Sprite(16, 0, 1, SpriteSheet.mobs), 
				new Sprite(16, 1, 1, SpriteSheet.mobs),		
				new Sprite(16, 2, 1, SpriteSheet.mobs),

				new Sprite(16, 0, 2, SpriteSheet.mobs), 
				new Sprite(16, 1, 2, SpriteSheet.mobs),
				new Sprite(16, 2, 2, SpriteSheet.mobs),
				
				new Sprite(16, 0, 3, SpriteSheet.mobs), 
				new Sprite(16, 1, 3, SpriteSheet.mobs),
				new Sprite(16, 2, 3, SpriteSheet.mobs)

		));
	}

	public Player(int x, int y) {
		super(x, y, 50, 50, 50, 50, 3, new AnimatedMobSprite(

		new Sprite(16, 2, 0, SpriteSheet.mobs), 
		new Sprite(16, 0, 0, SpriteSheet.mobs),	
		new Sprite(16, 1, 0, SpriteSheet.mobs),

		new Sprite(16, 0, 1, SpriteSheet.mobs), 
		new Sprite(16, 1, 1, SpriteSheet.mobs),		
		new Sprite(16, 2, 1, SpriteSheet.mobs),

		new Sprite(16, 0, 2, SpriteSheet.mobs), 
		new Sprite(16, 1, 2, SpriteSheet.mobs),
		new Sprite(16, 2, 2, SpriteSheet.mobs),
		
		new Sprite(16, 0, 3, SpriteSheet.mobs), 
		new Sprite(16, 1, 3, SpriteSheet.mobs),
		new Sprite(16, 2, 3, SpriteSheet.mobs)
		
		));
	}
	
	public void update() {
		xRel = xPos - Level.cam.x;
		yRel = yPos - Level.cam.y;

		if(stamina < 100 - 0.5)
			stamina += 0.5;
		
		animSprite.update(dir, moving);
		if(isDead())
			Level.currentState = Level.death;
		
		lastMeele++;
	}
	
	

	public void move(int x, int y) {
		int startX = x;
		int startY = y;
		
		while(x!= 0 || y!= 0 && stamina > 0){
			if(x > 0){  //right
				dir = 0;
				if (!Level.selectedMap.tiles[((xPos) / 16 + 1) + (yPos / 16) * 32].isSolid()
						&& !Level.selectedMap.tiles[((xPos) / 16 + 1) + ((yPos + 15) / 16) * 32].isSolid()){
					boolean canMove = true;
					for(int i = 0; i < Level.mobs.size(); i++){
						if(!(Level.mobs.get(i) instanceof Player)){
							//working cases
							// can move if xP > xM 
							// can move if abs(y) > 16
							// can move if xp - xm is more than +- 16
							
							//can't move if mob is 16 or less to the right AND 
							//the abs(y) is < 16
							if(xPos > Level.mobs.get(i).xPos)
								continue;
							
							if(Level.mobs.get(i).xPos - xPos <= 16 && Math.abs(Level.mobs.get(i).yPos - yPos) < 16){
								canMove = false;
								break;
							}
						}
					}
					if(canMove){
						xPos++;
						Level.cam.move(1,0);
					}
				}
				x--;
			}
	
			if(x < 0) { //left
				dir = 2;
				if (!Level.selectedMap.tiles[((xPos - 1) / 16) + (yPos / 16) * 32].isSolid() 
						&& !Level.selectedMap.tiles[((xPos - 1) / 16) + ((yPos + 15) / 16) * 32].isSolid()){
					boolean canMove = true;
					for(int i = 0; i < Level.mobs.size(); i++){
						if(!(Level.mobs.get(i) instanceof Player)){
							if(xPos < Level.mobs.get(i).xPos) 
								continue;
							
							if(Level.mobs.get(i).xPos - xPos >= -16 && Math.abs(yPos - Level.mobs.get(i).yPos) < 16){
								canMove = false;
								break;
							}
						}
					}
					if(canMove){
						xPos--;
						Level.cam.move(-1,0);
					}
				}
				x++;
			}
		
		
			if(y > 0) {  //down
				dir = 1;
				if (!Level.selectedMap.tiles[(xPos / 16) + (yPos / 16 + 1) * 32].isSolid()
						&& !Level.selectedMap.tiles[((xPos+15) / 16) + (yPos / 16 + 1) * 32].isSolid()){
					boolean canMove = true;
					for(int i = 0; i < Level.mobs.size(); i++){
						if(!(Level.mobs.get(i) instanceof Player)){
							if(yPos > Level.mobs.get(i).yPos)
								continue;
							if(Level.mobs.get(i).yPos - yPos <= 16 && Math.abs(xPos - Level.mobs.get(i).xPos) < 16){
								canMove = false;
								break;
							}
						}
					}
					if(canMove){
						yPos++;
						Level.cam.move(0,1);
					}
				}
				y--;
			}
	
			if(y < 0) {  //up
				dir = 3;
				if(!Level.selectedMap.tiles[(xPos/16) + ((yPos-1)/16) * 32].isSolid() && 
						!Level.selectedMap.tiles[((xPos + 15)/16) + ((yPos-1)/16) * 32].isSolid()){
					boolean canMove = true;
					for(int i = 0; i < Level.mobs.size(); i++){
						if(!(Level.mobs.get(i) instanceof Player)){
							if(yPos < Level.mobs.get(i).yPos)
								continue;
							if(Level.mobs.get(i).yPos - yPos >= -16 && Math.abs(xPos - Level.mobs.get(i).xPos) < 16){
								canMove = false;
								break;
							}
						}
					}
					if(canMove){
						yPos--;
						Level.cam.move(0,-1);
					}
				}
				y++;
			}
		}
		stamina -= 0.25 * Math.max(Math.abs(startX - x), Math.abs(startY - y));
	}
}

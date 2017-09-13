package com.logan.game.level;

import com.logan.game.Game;
import java.util.Random;
import com.logan.game.entities.Block;
import com.logan.game.graphics.Screen;
import com.logan.game.screens.Button;

public class Level {
	
private Random rand;
	
	public Level() {
		rand = new Random();
	}
	
	public void update(){
		for(int i = 0 ; i < Block.blocks.size(); i++){
			Block.blocks.get(i).update();
		}
		
		Game.player.update();
	
	}
	
	public void render(Screen screen){
		screen.drawOffsetRect(0,0,Game.boundrySize,Game.boundrySize,0xffffff,Game.xOffset,Game.yOffset);
		
		Game.player.render(screen);
		for(int i = 0 ; i < Block.blocks.size(); i++){
			Block.blocks.get(i).render(screen);
		}
	}
	
	public void initLevel(int level){
		if(level == 1){
			for (int i = 0; i < 100; i++) {
				Block.blocks.add(new Block(rand.nextInt(Game.boundrySize), rand.nextInt(Game.boundrySize), 10, 0xff, false));
				Block.blocks.add(new Block(rand.nextInt(Game.boundrySize), rand.nextInt(Game.boundrySize), 10, 0xff0000, true));
			}
		}
			
	}
	
	/*
	 * Levels:
	 * 
	 * 1. 100 bad 100 good 1 wave
	 * 
	 * 
	 * 
	 * 
	 */

}

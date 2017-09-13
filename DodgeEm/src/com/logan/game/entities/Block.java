package com.logan.game.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.logan.game.Game;
import com.logan.game.graphics.Screen;

public class Block {

	protected int x, y, size, col, dir, speed;
	protected boolean harmful;
	private Random rand = new Random();

	private int[][] lastPos = { { -100, -100 }, { -100, -100 }, { -100, -100 }, { -100, -100 } };

	public static List<Block> blocks = new ArrayList<Block>();

	public Block(int x, int y, int size, int col, boolean harmful) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.col = col;
		this.harmful = harmful;

		dir = rand.nextInt(4) + 1;
		speed = rand.nextInt(4) + 3;

	}

	public void update() {
		
		
		x -= Game.deltaX;
		y -= Game.deltaY;

		for (int i = lastPos.length - 1; i > 0; i--) {
			for (int j = 0; j < 2; j++) {
				lastPos[i][j] = lastPos[i - 1][j];
			}
		}
		lastPos[0][0] = x;
		lastPos[0][1] = y;
		
		if(x+Game.xOffset<0)
			dir = 1;
		if(x+Game.xOffset>Game.boundrySize-size)
			dir = 3;
		if(y+Game.yOffset<0)
			dir = 2;
		if(y+Game.yOffset>Game.boundrySize-size)
			dir = 4;

		if (dir == 1)
			x += speed;
		if (dir == 2)
			y += speed;
		if (dir == 3)
			x -= speed;
		if (dir == 4)
			y -= speed;
		
		

		if ((x - Game.player.x < size) && (x - Game.player.x > -size) && (y - Game.player.y < size)
				&& (y - Game.player.y > -size)) {
			if (harmful) {
				if (Game.player.health > 0)
					Game.player.health-=(5-Game.player.sheilds);
			} else {
				if (Game.player.health < Game.player.max_health)
					Game.player.health++;
			}
			//System.out.println(Game.player.health + "\t" + Game.player.max_health);
		}

	}

	public void render(Screen screen) {
		screen.fillRect(x, y, size, size, col);
		//for (int i = 0; i < lastPos.length; i++) {
		//	screen.fillRect(lastPos[i][0], lastPos[i][1], size, size, col);
		//}
		
	}

}

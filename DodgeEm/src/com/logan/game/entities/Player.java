package com.logan.game.entities;

import com.logan.game.graphics.Screen;
import com.logan.game.graphics.Sprite;

public class Player {

	protected Sprite sprite;
	public int x;
	public int y;
	public int  size = 10;
	public int max_health = 100, health = max_health;
	
	protected int sheilds = 0;
	protected int movementBoost = 0;

	public Player(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public Player(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void update() {
		if(health<=0)
			System.out.println("Game over");
		
		System.out.println(health);
	}

	public void render(Screen screen) {
		if(sprite!=null)
			screen.renderSprite(x, y, sprite);
		else
			screen.fillRect(x, y, size, size, 0xffffff);
	}

}

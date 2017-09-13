package com.nerds.main.entities;

import java.util.Random;

import com.nerds.main.graphics.Screen;
import com.nerds.main.level.Level;

public abstract class Entity {

	public int xPos, yPos, xRel, yRel;
	protected static Random rand = new Random();

	public Entity(int x, int y) {
		this.xPos = x;
		this.yPos = y;

		Level.entities.add(this);
	}

	public void update() {

	}
	

	public void render(Screen screen) {

	}

}

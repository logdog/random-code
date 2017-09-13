package main.entities;

import main.Graphics.Screen;
import main.levels.Level;

public abstract class Entity {

	protected int x, y;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		
		Level.entities.add(this);
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}

}

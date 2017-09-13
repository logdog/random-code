package main.entities;

import main.Graphics.Screen;

public class Enemy extends Entity{

	private int col = 0xff0000, size = 20;

	public Enemy(int x, int y, int col) {
		super(x, y);
		this.col = col;
	}
	
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		screen.fillRect(x, y, size, size, col);
	}

}

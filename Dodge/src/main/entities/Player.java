package main.entities;

import main.Graphics.Screen;

public class Player extends Entity{
	private int col = 0xff, size = 20;

	public Player(int x, int y, int col) {
		super(x, y);
		this.col = col;
	}
	
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		screen.fillRect(x,y,size,size,col);
	}

}

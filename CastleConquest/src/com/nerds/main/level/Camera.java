package com.nerds.main.level;

import com.nerds.main.Game;

public class Camera {
	
	public int x, y, deltaX, deltaY; //for shifting

	public Camera(int x, int y){
		this.x= x - Game.WIDTH / 2;
		this.y= y - Game.HEIGHT / 2;
	}
	
	public void move(int x, int y){
		this.x+=x;
		this.y+=y;
	}
	
	public void set(int x, int y){
		this.x=x;
		this.y=y;
	}
	
}

package com.logan.game.screens.UI;

import java.util.ArrayList;
import java.util.List;

import com.logan.game.Game;
import com.logan.game.screens.UI.Component;
import com.logan.game.graphics.Screen;
import com.logan.game.input.Mouse;

public class Panel {

	protected int backgroundColor;
	protected int x,y, width, height;
	
	protected List<Component> comps = new ArrayList<Component>();

	public Panel(int x, int y, int width, int height, int col) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.backgroundColor = col;
	}
	
	public void movePanel(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void update(){
		for(int i = 0; i < comps.size(); i++){
			comps.get(i).update();
		}
	}
	
	public void render(Screen screen){
		screen.fillRect(x, y, width, height, backgroundColor);
		screen.drawRect(x, y, width, height, 0);
		
		for(int i = 0; i < comps.size(); i++){
			comps.get(i).render(screen);
		}
	}

}

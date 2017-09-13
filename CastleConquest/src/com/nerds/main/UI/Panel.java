package com.nerds.main.UI;

import java.util.ArrayList;
import java.util.List;

import com.nerds.main.graphics.Screen;

/*
 * so panels are different
 * panels are the little mini screens that appear
 * panels contain components, like mini maps, text boxes, etc.
 * panels can update, and render
 * 
 * 
 */
public class Panel {
	
	public List<Component> components = new ArrayList<Component>();
	public int xPos, yPos, width, height;
	
	private int col = 0xeeeeee;
	
	public Panel(int x, int y, int w, int h, UIManager ui){
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		ui.panels.add(this);
	}
	
	public void setBackground(int col){
		this.col = col;
	}
	
	public void update(){
		
		//components
		for(int i = 0; i < components.size(); i++){
			components.get(i).update();
		}
	}
	
	public void render(Screen screen){
		
		
		screen.fillRect(xPos, yPos, width, height, col);
		
		//components
		for(int i = 0; i < components.size(); i++){
			components.get(i).render(screen);
		}
	}
	
}

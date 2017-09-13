package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;

/*
 * 
 * so components are the things inside of panels
 * they consist of mini maps, buttons, text boxes, etc...
 * they can update, and render
 * 
 * the button and text boxes and other classes will take care of specific functions
 * 
 * Component is the parent class of all text boxes and such
 */

public class Component {
	
	protected int xRel, yRel, width, height;
	
	public Component(int xRel, int yRel, int width, int height, Panel panel){ //called by panel
		this.xRel = xRel + panel.xPos;
		this.yRel = yRel + panel.yPos;
		this.width = width;
		this.height = height;
		panel.components.add(this);
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}

	
}

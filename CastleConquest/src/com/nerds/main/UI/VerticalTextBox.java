package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;

public class VerticalTextBox extends TextBox{

	public VerticalTextBox(int x, int y, String msg, Panel panel){
		super(x,y,msg, panel);
		message = msg;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		screen.renderVerticalText(message, xRel, yRel);
	}
	
}

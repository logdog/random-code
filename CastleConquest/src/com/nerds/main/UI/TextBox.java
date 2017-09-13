package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;

/*
 * 
 * this is a special type of component
 * it is just a text box and is filled with text
 * 
 */

public class TextBox extends Component{

	protected String message;
	
	public TextBox(int x, int y, String msg, Panel panel){
		super(x,y,0,0, panel);
		message = msg;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		screen.renderText(message, xRel, yRel);
	}
	
}

package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;
import com.nerds.main.level.Level;

public class StatusBar extends Component{

	protected int barColor, bgColor;
	protected VerticalTextBox text;
	
	public StatusBar(int xRel, int yRel, int width, int height, String str, Panel panel){
		super(xRel, yRel, width, height, panel);
		text = new VerticalTextBox(xRel + width / 2, yRel + height/2, str, panel);
		
		bgColor = 0xeeeeee;
	}
	
	public void setBackground(int col){
		bgColor = col;
	}
	
	public void setBarColor(int col){
		barColor = col;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
	
	
}

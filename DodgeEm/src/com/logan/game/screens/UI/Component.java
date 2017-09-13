package com.logan.game.screens.UI;

import java.util.ArrayList;
import java.util.List;

import com.logan.game.screens.Button;
import com.logan.game.screens.UI.Panel;
import com.logan.game.graphics.Screen;
import com.logan.game.graphics.Sprite;

public class Component {

	protected int x, y, xOffset, yOffset;
	protected String title;
	protected Panel panel;
	
	
	protected List<Button> buttons = new ArrayList<Button>();

	public Component(Panel panel, int x, int y, String title) {
		this.panel = panel;
		this.xOffset = x;
		this.yOffset = y;
		this.x = xOffset + panel.x;
		this.y = yOffset + panel.y;
		this.title = title;
	}
	
	protected void addButton(int x, int y, int width, int height, int col, String title){
		buttons.add(0,new Button(this.x+ x, this.y + y, width, height, col, title));
		
	}
	
	protected void addButtonText(Sprite sprite, String title){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).text.equals(title)){
				buttons.get(i).letter.add(sprite);
			}
		}
	}
	
	public void update(){
		x = panel.x + xOffset;
		y = panel.y + yOffset;

		for(int i = 0; i < buttons.size(); i++){
			
			buttons.get(i).update();
		}
	}
	
	public void render(Screen screen){
		for(int i = 0; i < buttons.size(); i++){
			buttons.get(i).render(screen);
		}
	}

}

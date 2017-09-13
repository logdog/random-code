package com.nerds.main.UI;

import java.util.ArrayList;
import java.util.List;

import com.nerds.main.graphics.Screen;

/*
	each game state will have its own UIManager, which contains all of the panels
	an example would be the main menu, the pause screen, the inventory, etc.
	this class will contain an update method, and a render method
	the components will deal with the individual things
		
	let's do this!
*/


public class UIManager {

	public List<Panel> panels = new ArrayList<Panel>();
	
	public UIManager(){
		
	}
	
	public void update(){
		for(int i = 0; i < panels.size();i++){
			panels.get(i).update();
		}
	}
	
	public void render(Screen screen){
		for(int i = 0; i < panels.size();i++){
			panels.get(i).render(screen);
		}
	}
	
	
}

package com.nerds.main.level.State;

import com.nerds.main.Game;
import com.nerds.main.UI.Panel;
import com.nerds.main.UI.TextBox;
import com.nerds.main.UI.UIManager;

import logdog.UI.Button;

public class Menu extends State{

	public Menu(){
		super();
		ui = new UIManager();
		
		Panel center = new Panel(20, 20, Game.WIDTH - 40, Game.HEIGHT - 40, ui);
		TextBox title = new TextBox(center.width / 2, 10, "THIS IS THE TITLE NERD", center);
		
		Panel lower = new Panel(60, 80, Game.WIDTH - 60 * 2, Game.HEIGHT - 60 * 2, ui);
		lower.setBackground(0xff);
		
		//Button play = new Button(lower.width / 2, lower.height / 2, 50, 50, "play", "play", lower);
	}
	
}

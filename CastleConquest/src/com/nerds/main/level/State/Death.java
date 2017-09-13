package com.nerds.main.level.State;

import com.nerds.main.Game;
import com.nerds.main.UI.Panel;
import com.nerds.main.UI.TextBox;
import com.nerds.main.UI.UIManager;

import logdog.UI.Button;

public class Death extends State{

	public Death(){
		super();
		ui = new UIManager();
		
		Panel center = new Panel(30, 30, Game.WIDTH - 60, Game.HEIGHT - 60, ui);
		//Button respawn = new Button(center.width/2 - 30, center.height/2 - 30, 60, 60, "respawn", "respawn", center);
	}
	
}

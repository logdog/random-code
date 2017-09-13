package com.nerds.main.level.State;

import com.nerds.main.Game;
import com.nerds.main.UI.HealthBar;
import com.nerds.main.UI.MagicBar;
import com.nerds.main.UI.Panel;
import com.nerds.main.UI.StaminaBar;
import com.nerds.main.UI.UIManager;

import logdog.UI.Button;

public class Playing extends State{

	public Playing(){
		super();
		ui = new UIManager();
		Panel right = new Panel(Game.WIDTH - 50, 0, 50, Game.HEIGHT, ui);
		Button magic = new Button(0, 0, 50, 50, "Magic", "magic", right);
		
		HealthBar health = new HealthBar(0, right.height - 50, 10, 50, "HEALTH", right);
		MagicBar mb = new MagicBar(20, right.height - 50, 10, 50, "MAGIC", right);
		StaminaBar sb = new StaminaBar(40, right.height - 50, 10, 50, "STAMINA", right);
		
	}
	
}

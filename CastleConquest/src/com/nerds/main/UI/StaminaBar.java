package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;
import com.nerds.main.level.Level;

public class StaminaBar extends StatusBar{

	public StaminaBar(int xRel, int yRel, int width, int height, String str, Panel panel){
		super(xRel, yRel, width, height, str, panel);
		text = new VerticalTextBox(xRel + width / 2, yRel + height/2, str, panel);
		
		barColor = 0xff00;
	}
	
	public void render(Screen screen){
		screen.drawRect(xRel, yRel, width, height, 0);
		screen.fillRect(xRel, yRel, width, height, bgColor);

		screen.fillRect(xRel, yRel + height - (int) (height *  Level.player.stamina / Level.player.maxStamina), width, (int) (height * Level.player.stamina / Level.player.maxStamina), barColor);
		
		
	}
	
}

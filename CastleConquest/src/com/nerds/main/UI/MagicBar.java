package com.nerds.main.UI;

import com.nerds.main.graphics.Screen;
import com.nerds.main.level.Level;

public class MagicBar extends StatusBar{
	
	public MagicBar(int xRel, int yRel, int width, int height, String str, Panel panel){
		super(xRel, yRel, width, height, str, panel);
		text = new VerticalTextBox(xRel + width / 2, yRel + height/2, str, panel);
		
		barColor = 0xff;
	}
	
	public void render(Screen screen){
		screen.drawRect(xRel, yRel, width, height, 0);
		screen.fillRect(xRel, yRel, width, height, bgColor);

		screen.fillRect(xRel, yRel + height - height * Level.player.health / Level.player.maxHealth, width, height * Level.player.health / Level.player.maxHealth, barColor);
		
		
	}

}

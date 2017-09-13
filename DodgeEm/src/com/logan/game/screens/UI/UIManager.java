package com.logan.game.screens.UI;

import java.util.ArrayList;
import java.util.List;

import com.logan.game.Game;
import com.logan.game.screens.UI.Component;
import com.logan.game.screens.UI.Panel;
import com.logan.game.graphics.Screen;
import com.logan.game.graphics.Sprite;

public class UIManager {

protected static List<Panel> panels = new ArrayList<Panel>();
	
	protected Panel pausePanel = new Panel(60, 60, Game.WIDTH-120, Game.HEIGHT-120, 0xaaaaaa);
	
	public UIManager() {
		panels.add(pausePanel);
		pausePanel.comps.add(new Component(pausePanel, 5, 5, "shop"));
		pausePanel.comps.get(0).addButton(0, 0, 50, 20, 0, "shop");
		pausePanel.comps.get(0).addButtonText(Sprite.S, "shop");
		pausePanel.comps.get(0).addButtonText(Sprite.H, "shop");
		pausePanel.comps.get(0).addButtonText(Sprite.O, "shop");
		pausePanel.comps.get(0).addButtonText(Sprite.P, "shop");
	}
	
	public void update(){
		for(int i = 0; i < panels.size(); i++){
			panels.get(i).update();
		}
	}
	
	public void render(Screen screen){
		for(int i = 0; i < panels.size(); i++){
			panels.get(i).render(screen);
		}
		
	}

}

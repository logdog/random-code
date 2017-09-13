package com.logan.game.screens;

import java.util.ArrayList;
import java.util.List;

import com.logan.game.graphics.Screen;
import com.logan.game.graphics.Sprite;

public class Button {

	public static List<Button> buttons = new ArrayList<Button>();
	public List<Sprite> letter = new ArrayList<Sprite>();
	private int x, y, width, height, col;
	public String text;
	public boolean visible = false;

	public Button(int x, int y, int width, int height, int col, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.col = col;
		this.text = text;

		buttons.add(this);
	}

	public void update() {
		if(visible){
			
		}
	}

	public void render(Screen screen) {
		if (visible) {
			screen.drawRect(x, y, width, height, col);
			for (int i = 0; i < letter.size(); i++) {
				screen.renderSprite(x + width / 2 - letter.size() * letter.get(i).SIZE / 2 + i * letter.get(i).SIZE,
						y + (height - letter.get(i).SIZE) / 2, letter.get(i));
			}
		}
	}

}

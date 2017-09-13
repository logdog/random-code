package com.nerds.main.UI;

import com.nerds.main.Game;
import com.nerds.main.UI.Component;
import com.nerds.main.UI.Panel;
import com.nerds.main.UI.TextBox;
import com.nerds.main.entities.Player;
import com.nerds.main.graphics.Screen;
import com.nerds.main.input.Mouse;
import com.nerds.main.level.Level;
import com.nerds.main.sounds.Music;

/*
 * 
 * buttons are rectangles that do something when the mouse is clicked on them
 * that is it. they can update and render and also do stuff when clicked
 * this will probably be an boolean isClicked() method
 * 
 */

public class Button extends Component {

	public int col = 0xcccccc;
	public final String ID;
	private TextBox text = null;

	public Button(int x, int y, int w, int h, String id, Panel panel) {
		super(x, y, w, h, panel);
		ID = id;
	}

	public Button(int x, int y, int w, int h, String msg, String id, Panel panel) {
		super(x, y, w, h, panel);
		text = new TextBox(x + w / 2, y + h / 2, msg, panel);
		ID = id;
	}
	
	public void setBackground(int col){
		this.col = col;
	}

	public void update() {
		if (ID.equalsIgnoreCase("magic")) {
			if (Mouse.xPos >= xRel && Mouse.xPos < xRel + width && Mouse.yPos >= yRel && Mouse.yPos < yRel + height
					&& Mouse.leftPressed) {
				Mouse.leftPressed = false;
				action();
			}
		}
		if(ID.equalsIgnoreCase("play")){
			if (Mouse.xPos >= xRel && Mouse.xPos < xRel + width && Mouse.yPos >= yRel && Mouse.yPos < yRel + height
					&& Mouse.leftPressed) {
				Mouse.leftPressed = false;
				Level.currentState = Level.playing;
			}
		}
		if(ID.equalsIgnoreCase("respawn")){
			if (Mouse.xPos >= xRel && Mouse.xPos < xRel + width && Mouse.yPos >= yRel && Mouse.yPos < yRel + height
					&& Mouse.leftPressed) {
				Mouse.leftPressed = false;
				Level.player.health = Level.player.maxHealth;
				Level.mobs.clear();
				Level.entities.clear();
				Level.player = new Player(15 * 16, 6 * 16);
				Level.cam.set(Level.player.xPos - Game.WIDTH/2, Level.player.yPos - Game.HEIGHT/2);
				Level.currentState = Level.playing;
			}
		}
	}

	public void action() {
		new Music("res/sounds/music/Valor.wav").play();
	}

	public void render(Screen screen) {
		screen.fillRect(xRel, yRel, width, height, col);
		screen.drawRect(xRel, yRel, width, height, 0);

		if (text != null)
			text.render(screen);
	}

}

package com.nerds.main.level;

import java.util.ArrayList;
import java.util.List;

import com.nerds.main.entities.Bandit;
import com.nerds.main.entities.Entity;
import com.nerds.main.entities.Mob;
import com.nerds.main.entities.Player;
import com.nerds.main.graphics.Screen;
import com.nerds.main.input.Keyboard;
import com.nerds.main.input.Mouse;
import com.nerds.main.level.State.Death;
import com.nerds.main.level.State.Menu;
import com.nerds.main.level.State.Playing;
import com.nerds.main.level.State.State;

public class Level {

	public static List<Entity> entities = new ArrayList<Entity>();
	public static List<Mob> mobs = new ArrayList<Mob>();

	//states
	public static State menu = new Menu();
	public static State playing = new Playing();
	public static State death = new Death();
	public static State currentState = menu;
	
	//end states
	public static Camera cam;
	public static Player player;
	public static SpawnMap selectedMap;
	public static boolean canMove = true;

	public Level(int width, int height) {
		player = new Player(15 * 16, 6 * 16);
		cam = new Camera(player.xPos, player.yPos);
		new Bandit(16, 6 * 16);
		selectedMap = Map.overworld;
		
		//uis go here
		
		
	}

	public void update() {

		if(currentState == playing){
			playing.ui.update();
			for (int i = 0; i < mobs.size(); i++) {
				mobs.get(i).update();
				if (mobs.get(i).isDead() && !(mobs.get(i) instanceof Player)) {
					mobs.remove(mobs.get(i));
					entities.remove(entities.get(i));
					break;
				}
				if (!(mobs.get(i) instanceof Player)) {
					mobs.get(i).simplePath();
				}
			}
		}
		else if(currentState == menu){
			menu.ui.update();
		}
		else if(currentState == death){
			death.ui.update();
		}
	}

	public void changeMap(SpawnMap newMap) {
		selectedMap = newMap;
	}

	public void render(Screen screen) {
		screen.clear();
		if(currentState == playing){
			screen.renderMap(cam.x, cam.y, selectedMap);
	
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).render(screen);
			}
			
			playing.ui.render(screen);
		}
		else if(currentState== menu){
			menu.ui.render(screen);
		}
		else if(currentState == death){
			death.ui.render(screen);
		}
	}

	public void keyUpdate(Keyboard key) {
		if (key.shift) {
			new Bandit(((int) (Math.random() * 16 * 32)), (int) (Math.random() * 16 * 32));
		}

		int speed = player.speed;
		int x = 0, y = 0;

		if (key.up || key.down || key.left || key.right)
			player.moving = true;
		else
			player.moving = false;

		if (canMove) {
			if (key.shift) {
				speed /= speed;
			}
			if (key.left) {
				x -= speed;
			}
			if (key.right) {
				x += speed;
			}
			if (key.up) {
				y -= speed;
			}
			if (key.down) {
				y += speed;
			}
			player.move(x, y);
		}

	}

	public void meleeAttack() {
		if (player.lastMeele > 20 && player.stamina > 10) {
			player.lastMeele = 0;
			player.stamina -= 10;
			int range = 24;
			int offset = 16;

			if (player.dir == 0) { // right
				for (int i = 0; i < mobs.size(); i++) {
					if (!(mobs.get(i) instanceof Player)) {
						if (mobs.get(i).xPos < player.xPos + range && mobs.get(i).xPos > player.xPos
								&& Math.abs(Level.mobs.get(i).yPos - player.yPos) <= offset) {

							if (player.physical - Level.mobs.get(i).defense > 0) {
								mobs.get(i).health -= ((player.physical - Level.mobs.get(i).defense) / 2
										+ (int) (Math.random() * 5) + 1);
								mobs.get(i).move(16, (int) (Math.random() * 16) - offset);

							}
						}
					}
				}
			}

			if (player.dir == 1) { // down
				for (int i = 0; i < mobs.size(); i++) {
					if (!(mobs.get(i) instanceof Player)) {
						if (mobs.get(i).yPos > player.yPos && mobs.get(i).yPos - player.yPos < range
								&& Math.abs(mobs.get(i).xPos - player.xPos) <= offset) {

							if (player.physical - Level.mobs.get(i).defense > 0) {
								mobs.get(i).health -= ((player.physical - Level.mobs.get(i).defense) / 2
										+ (int) (Math.random() * 5) + 1);
								mobs.get(i).move((int) (Math.random() * 16) - offset, 16);

							}
						}
					}
				}
			}

			if (player.dir == 2) { // left
				for (int i = 0; i < mobs.size(); i++) {
					if (!(mobs.get(i) instanceof Player)) {
						if (player.xPos - mobs.get(i).xPos < range && mobs.get(i).xPos < player.xPos
								&& Math.abs(player.yPos - mobs.get(i).yPos) <= 8) {
							if (player.physical - Level.mobs.get(i).defense > 0) {
								mobs.get(i).health -= ((player.physical - Level.mobs.get(i).defense) / 2
										+ (int) (Math.random() * 5) + 1);
								mobs.get(i).move(-16, (int) (Math.random() * 16) - 8);
							}
						}
					}
				}
			}

			if (player.dir == 3) { // up
				for (int i = 0; i < mobs.size(); i++) {
					if (!(mobs.get(i) instanceof Player)) {
						if (mobs.get(i).yPos < player.yPos && player.yPos - mobs.get(i).yPos < range
								&& Math.abs(mobs.get(i).xPos - player.xPos) <= 8) {

							if (player.physical - Level.mobs.get(i).defense > 0) {
								mobs.get(i).health -= ((player.physical - Level.mobs.get(i).defense) / 2
										+ (int) (Math.random() * 5) + 1);
								mobs.get(i).move((int) (Math.random() * 16) - 8, -16);
							}
						}
					}
				}
			}
		}
	}

	public void mouseUpdate(Mouse mouse) {
		if (mouse.leftPressed) {
			meleeAttack();
		}
	}

}

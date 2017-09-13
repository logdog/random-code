package com.nerds.main.entities;

import java.util.List;
import java.util.ArrayList;

import com.nerds.main.graphics.AnimatedMobSprite;
import com.nerds.main.graphics.Screen;
import com.nerds.main.level.Level;

public abstract class Mob extends Entity {

	public int health, physical, range, defense, speed, dir, maxHealth;
	protected AnimatedMobSprite animSprite;
	public boolean moving = true;

	public List<String> directions = new ArrayList<String>();

	public Mob(int x, int y, int health, int physical, int range, int defense, int speed,
			AnimatedMobSprite animSprite) {
		super(x, y);
		this.health = health;
		this.physical = physical;
		this.range = range;
		this.defense = defense;
		this.speed = speed;
		this.animSprite = animSprite;
		this.maxHealth = health;
		Level.mobs.add(this);
	}

	public Mob(int health, int physical, int range, int defense, int speed, AnimatedMobSprite animSprite) {
		super(rand.nextInt(100), rand.nextInt(100));
		this.health = health;
		this.physical = physical;
		this.range = range;
		this.defense = defense;
		this.speed = speed;
		this.animSprite = animSprite;
		this.maxHealth = health;
		Level.mobs.add(this);
	}

	public void move(int x, int y) {
		while (x != 0 || y != 0) {
			if (x > 0) { // right
				dir = 0;
				if (canMoveRight()) {
					if (xPos > Level.player.xPos)
						xPos++;
					else if (Level.player.xPos - xPos > 16)
						xPos++;
					else if (Math.abs(Level.player.yPos - yPos) > 16)
						xPos++;
				}
				x--;
			}

			if (x < 0) { // left
				dir = 2;
				if (canMoveLeft()) {
					if (xPos < Level.player.xPos)
						xPos--;
					else if (xPos - Level.player.xPos > 16)
						xPos--;
					else if (Math.abs(yPos - Level.player.yPos) > 16)
						xPos--;
				}
				x++;
			}

			if (y > 0) { // down
				dir = 1;
				if (canMoveDown()) {
					if (yPos > Level.player.yPos)
						yPos++;
					else if (Level.player.yPos - yPos > 16)
						yPos++;
					else if (Math.abs(xPos - Level.player.xPos) > 16)
						yPos++;
				}
				y--;
			}

			if (y < 0) { // up
				dir = 3;
				if (canMoveUp()) {
					if (Level.player.yPos > yPos)
						yPos--;
					else if (yPos - Level.player.yPos > 16)
						yPos--;
					else if (Math.abs(xPos - Level.player.xPos) > 16)
						yPos--;
				}
				y++;
			}
		}
	}

	public void update() {
		xRel = xPos - Level.cam.x;
		yRel = yPos - Level.cam.y;
		
		if(Math.abs(xPos - Level.player.xPos) <= 16 && Math.abs(yPos - Level.player.yPos) <= 16){
			Level.player.health -= (physical - Level.player.defense) / 4;
			if(dir == 0)
				Level.player.move(12,  rand.nextInt(8) - 4);
			else if(dir == 1)
				Level.player.move(rand.nextInt(8) - 4, 12);
			else if(dir== 2)
				Level.player.move(-12,  rand.nextInt(8) - 4);
			else
				Level.player.move(rand.nextInt(8) - 4, -12);
		}

		animSprite.update(dir, moving);
	}

	public void render(Screen screen) {
		screen.renderSprite(xRel, yRel, animSprite.currentSprite);
		// screen.drawRect(xRel, yRel, 16, 16, 0xff00ff);

		renderPath(screen);
	}

	public boolean isDead() {
		if (health <= 0)
			return true;
		else
			return false;
	}

	// ============AI=================//

	private boolean canMoveLeft() {
		try {
			return (!Level.selectedMap.tiles[((xPos - 1) / 16) + (yPos / 16) * 32].isSolid()
					&& !Level.selectedMap.tiles[((xPos - 1) / 16) + ((yPos + 15) / 16) * 32].isSolid());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean canMoveRight() {
		try {
			return (!Level.selectedMap.tiles[((xPos) / 16 + 1) + (yPos / 16) * 32].isSolid()
					&& !Level.selectedMap.tiles[((xPos) / 16 + 1) + ((yPos + 15) / 16) * 32].isSolid());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean canMoveUp() {
		try {
			return (!Level.selectedMap.tiles[(xPos / 16) + ((yPos - 1) / 16) * 32].isSolid()
					&& !Level.selectedMap.tiles[((xPos + 15) / 16) + ((yPos - 1) / 16) * 32].isSolid());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	private boolean canMoveDown() {
		try {
			return (!Level.selectedMap.tiles[(xPos / 16) + (yPos / 16 + 1) * 32].isSolid()
					&& !Level.selectedMap.tiles[((xPos + 15) / 16) + (yPos / 16 + 1) * 32].isSolid());
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	public void followPath() {
		if (directions.size() > 0) {

			if (directions.get(0).equals("right")) {
				int nx = xPos;
				move(speed, 0);
				if (nx - xPos == 0)
					move(0, -speed);
			}
			if (directions.get(0).equals("left")) {
				move(-speed, 0);
			}
			if (directions.get(0).equals("up")) {
				move(0, -speed);
			}
			if (directions.get(0).equals("down")) {
				move(0, speed);
			}

		}

	}

	public void simplePath() {
		if (xPos < Level.player.xPos)
			move(speed, 0);
		if (xPos > Level.player.xPos)
			move(-speed, 0);
		if (yPos < Level.player.yPos)
			move(0, speed);
		if (yPos > Level.player.yPos)
			move(0, -speed);
	}

	public void renderPath(Screen screen) {
		int xTile = xPos / 16;
		int yTile = yPos / 16;
		for (int i = 0; i < directions.size(); i++) {
			if (directions.get(i).equals("left"))
				xTile -= 1;
			if (directions.get(i).equals("up"))
				yTile -= 1;
			if (directions.get(i).equals("down"))
				yTile += 1;
			if (directions.get(i).equals("right"))
				xTile += 1;
			screen.drawRect(xTile * 16, yTile * 16, 16, 16, 0xffff00);
		}

	}

	public void pathFind(int goalX, int goalY) {
		// add small tweaks
		/*
		 * if (!(Math.abs(yPos - Level.player.yPos) < 24 && Math.abs(xPos -
		 * Level.player.xPos) < 24)) {
		 * 
		 * int currentX = xPos; int currentY = yPos;
		 * 
		 * int xTile = currentX / 16, yTile = currentY / 16, xgTile = goalX /
		 * 16, ygTile = goalY / 16;
		 * 
		 * boolean pathFound = false; directions.clear(); while (!pathFound) {
		 * if (xTile < xgTile) { if (!Level.selectedMap.tiles[(xTile + 1) +
		 * yTile * 32].isSolid()) { // move xTile++; directions.add("right"); }
		 * else if (!Level.selectedMap.tiles[(xTile) + (yTile - 1) *
		 * 32].isSolid()) { // up
		 * 
		 * List<String> locale = new ArrayList<String>(); int tempXtile = xTile;
		 * int tempYtile = yTile; boolean worked = false;
		 * 
		 * while (true) { if (!Level.selectedMap.tiles[(tempXtile + 1) +
		 * tempYtile * 32].isSolid()) { // if the new right tile is not solid
		 * worked = true; break; } if (!Level.selectedMap.tiles[(tempXtile) +
		 * (tempYtile - 1) * 32].isSolid()) { tempYtile--; locale.add("up");
		 * continue; } if (Level.selectedMap.tiles[(tempXtile) + (tempYtile - 1)
		 * * 32].isSolid()) break; }
		 * 
		 * if (worked) { xTile = tempXtile; yTile = tempYtile; for (int i = 0; i
		 * < locale.size(); i++) { directions.add(locale.get(i)); } }
		 * 
		 * } else if (!Level.selectedMap.tiles[(xTile) + (yTile + 1) *
		 * 32].isSolid()) {
		 * 
		 * yTile++; directions.add("down"); } else { break; } } else if (xTile >
		 * xgTile) { if (!Level.selectedMap.tiles[(xTile - 1) + yTile *
		 * 32].isSolid()) { // move xTile--; directions.add("left"); pathFound =
		 * true; break; } }
		 * 
		 * else if (!Level.selectedMap.tiles[(xTile) + (yTile - 1) *
		 * 32].isSolid()) { // up
		 * 
		 * List<String> locale = new ArrayList<String>(); int tempXtile = xTile;
		 * int tempYtile = yTile; boolean worked = false;
		 * 
		 * while (true) { if (!Level.selectedMap.tiles[(tempXtile - 1) +
		 * tempYtile * 32].isSolid()) { // if the new right tile is not solid
		 * worked = true; break; } if (!Level.selectedMap.tiles[(tempXtile) +
		 * (tempYtile - 1) * 32].isSolid()) { tempYtile--; locale.add("up");
		 * continue; } if (Level.selectedMap.tiles[(tempXtile) + (tempYtile - 1)
		 * * 32].isSolid()) break; }
		 * 
		 * if (worked) { xTile = tempXtile; yTile = tempYtile; for (int i = 0; i
		 * < locale.size(); i++) { directions.add(locale.get(i)); } } }
		 * 
		 * else if (yTile < ygTile) { // move down
		 * 
		 * if (!Level.selectedMap.tiles[(xTile) + (yTile + 1) * 32].isSolid()) {
		 * // move yTile++; directions.add("down"); } else if
		 * (!Level.selectedMap.tiles[(xTile + 1) + (yTile) * 32].isSolid()) { //
		 * try // right
		 * 
		 * List<String> locale = new ArrayList<String>(); int tempXtile = xTile;
		 * int tempYtile = yTile; boolean worked = false;
		 * 
		 * while (true) { if (!Level.selectedMap.tiles[(tempXtile) + (tempYtile
		 * + 1) * 32].isSolid()) { // if down is now non solid success! worked =
		 * true; break; } if (!Level.selectedMap.tiles[(tempXtile + 1) +
		 * (tempYtile) * 32].isSolid()) { tempXtile++; locale.add("right");
		 * continue; } if (Level.selectedMap.tiles[(tempXtile + 1) + (tempYtile)
		 * * 32].isSolid()) break; }
		 * 
		 * if (worked) { xTile = tempXtile; yTile = tempYtile; for (int i = 0; i
		 * < locale.size(); i++) { directions.add(locale.get(i)); } }
		 * 
		 * } else if (!Level.selectedMap.tiles[(xTile) + (yTile + 1) *
		 * 32].isSolid()) {
		 * 
		 * yTile++; directions.add("down"); } else { break; }
		 * 
		 * } else{ System.out.println("error nothing found"); break; } }
		 * followPath(); } else { if (yPos > Level.player.yPos) move(0, -speed);
		 * if (xPos > Level.player.xPos) move(-speed, 0); if (yPos <
		 * Level.player.yPos) move(0, speed); if (xPos < Level.player.xPos)
		 * move(speed, 0); }
		 */}
}

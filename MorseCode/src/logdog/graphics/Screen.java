package logdog.graphics;

import logdog.Game;
import logdog.graphics.morse.Letter;

public class Screen {
	private final int WIDTH;
	private final int HEIGHT;
	public int[] pixels;

	public Screen(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}

	public void drawRect(int xc, int yc, int w, int h, int col) {
		for (int y = 0; y < h; y++) {
			int yp = y + yc;
			if (yp < 0 || yp >= HEIGHT)
				continue;
			for (int x = 0; x < w; x++) {
				int xp = x + xc;
				if (xp < 0 || xp >= WIDTH)
					continue;
				if (x == 0 || y == 0 || x == w - 1 || y == h - 1)
					pixels[xp + yp * WIDTH] = col;
			}
		}
	}

	public void fillRect(int xc, int yc, int w, int h, int col) {
		for (int y = 0; y < h; y++) {
			int yp = y + yc;
			if (yp < 0 || yp >= HEIGHT)
				continue;
			for (int x = 0; x < w; x++) {
				int xp = x + xc;
				if (xp < 0 || xp >= WIDTH)
					continue;

				pixels[xp + yp * WIDTH] = col;
			}
		}
	}

	public void renderMorse(String code, int xc, int yc, int col) {
		code = code.trim();
		for(String let : code.split("\\s")) {
			xc += 25;
			for(int i = 0; i < let.length(); i++) {
				if(let.charAt(i) == '&') {
					xc += 25;
				}
				else if(let.charAt(i) == '-') {
					fillRect(xc, yc, 20, 5, col);
					xc += 25;
				}
				else if(let.charAt(i) == '.') {
					fillRect(xc, yc, 5, 5, col);
					xc += 10;
				}
				if(xc >= Game.WIDTH) {
					clear();
					xc = 0;
				}
			}
		}
	}

	public void renderSprite(int xc, int yc, Sprite sprite) {
		for (int y = 0; y < sprite.height; y++) {
			int yp = y + yc;
			if (yp < 0 || yp >= HEIGHT)
				continue;
			for (int x = 0; x < sprite.width; x++) {
				int xp = x + xc;
				if (xp < 0 || xp >= WIDTH)
					continue;
				// if (sprite.pixels[x + y * sprite.width] != 0xffff00ff &&
				// sprite.pixels[x + y * (sprite.width)] != 0xffaa00aa)
				pixels[xp + yp * WIDTH] = sprite.pixels[x + y * sprite.width];

			}
		}
	}

}

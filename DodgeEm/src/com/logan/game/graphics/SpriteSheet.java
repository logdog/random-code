package com.logan.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	protected final int SIZE;
	private String path;

	public int[] pixels;
	
	public static SpriteSheet letters = new SpriteSheet("/textures/Letters.png", 240);

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
			System.out.println("SpriteSheet " + path + " load successful!");
		} catch (IOException e) {
			System.out.println("Failed to load SpriteSheet: " + path);
			e.printStackTrace();
		}
	}

}

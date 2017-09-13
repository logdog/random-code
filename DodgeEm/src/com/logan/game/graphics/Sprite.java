package com.logan.game.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	protected int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite A = new Sprite(12, 0, 0, SpriteSheet.letters);
	public static Sprite B = new Sprite(12, 1, 0, SpriteSheet.letters);
	public static Sprite C = new Sprite(12, 2, 0, SpriteSheet.letters);
	public static Sprite D = new Sprite(12, 3, 0, SpriteSheet.letters);
	public static Sprite E = new Sprite(12, 4, 0, SpriteSheet.letters);
	public static Sprite F = new Sprite(12, 5, 0, SpriteSheet.letters);
	public static Sprite G = new Sprite(12, 6, 0, SpriteSheet.letters);
	public static Sprite H = new Sprite(12, 7, 0, SpriteSheet.letters);
	public static Sprite I = new Sprite(12, 8, 0, SpriteSheet.letters);
	public static Sprite J = new Sprite(12, 9, 0, SpriteSheet.letters);
	public static Sprite K = new Sprite(12, 10, 0, SpriteSheet.letters);
	public static Sprite L = new Sprite(12, 11, 0, SpriteSheet.letters);
	public static Sprite M = new Sprite(12, 12, 0, SpriteSheet.letters);
	public static Sprite N = new Sprite(12, 13, 0, SpriteSheet.letters);
	public static Sprite O = new Sprite(12, 14, 0, SpriteSheet.letters);
	public static Sprite P = new Sprite(12, 15, 0, SpriteSheet.letters);
	public static Sprite Q = new Sprite(12, 16, 0, SpriteSheet.letters);
	public static Sprite R = new Sprite(12, 17, 0, SpriteSheet.letters);
	public static Sprite S = new Sprite(12, 18, 0, SpriteSheet.letters);
	public static Sprite T = new Sprite(12, 19, 0, SpriteSheet.letters);
	
	public static Sprite U = new Sprite(12, 0, 1, SpriteSheet.letters);
	public static Sprite V = new Sprite(12, 1, 1, SpriteSheet.letters);
	public static Sprite W = new Sprite(12, 2, 1, SpriteSheet.letters);
	public static Sprite X = new Sprite(12, 3, 1, SpriteSheet.letters);
	public static Sprite Y = new Sprite(12, 4, 1, SpriteSheet.letters);
	public static Sprite Z = new Sprite(12, 5, 1, SpriteSheet.letters);
	public static Sprite exclamation = new Sprite(12, 6, 1, SpriteSheet.letters);
	public static Sprite question = new Sprite(12, 7, 1, SpriteSheet.letters);
	public static Sprite period = new Sprite(12, 8, 1, SpriteSheet.letters);
	
	public static Sprite zero = new Sprite(12, 0, 2, SpriteSheet.letters);
	public static Sprite one = new Sprite(12, 1, 2, SpriteSheet.letters);
	public static Sprite two = new Sprite(12, 2, 2, SpriteSheet.letters);
	public static Sprite three = new Sprite(12, 3, 2, SpriteSheet.letters);
	public static Sprite four = new Sprite(12, 4, 2, SpriteSheet.letters);
	public static Sprite five = new Sprite(12, 5, 2, SpriteSheet.letters);
	public static Sprite six = new Sprite(12, 6, 2, SpriteSheet.letters);
	public static Sprite seven = new Sprite(12, 7, 2, SpriteSheet.letters);
	public static Sprite eight = new Sprite(12, 8, 2, SpriteSheet.letters);
	public static Sprite nine = new Sprite(12, 9, 2, SpriteSheet.letters);

	public static Sprite right = new Sprite(12, 10, 2, SpriteSheet.letters);
	public static Sprite left = new Sprite(12, 11, 2, SpriteSheet.letters);
	
	public static Sprite pause = new Sprite(12, 12, 2, SpriteSheet.letters);
	
	public static Sprite locked = new Sprite(12, 13, 2, SpriteSheet.letters);
	
	public static Sprite smallA = new Sprite(6, 0, 6, SpriteSheet.letters);
	public static Sprite smallB = new Sprite(6, 1, 6, SpriteSheet.letters);
	public static Sprite smallC = new Sprite(6, 2, 6, SpriteSheet.letters);
	public static Sprite smallD = new Sprite(6, 3, 6, SpriteSheet.letters);
	public static Sprite smallE = new Sprite(6, 4, 6, SpriteSheet.letters);
	public static Sprite smallF = new Sprite(6, 5, 6, SpriteSheet.letters);
	public static Sprite smallG = new Sprite(6, 6, 6, SpriteSheet.letters);
	public static Sprite smallH = new Sprite(6, 7, 6, SpriteSheet.letters);
	public static Sprite smallI = new Sprite(6, 8, 6, SpriteSheet.letters);
	public static Sprite smallJ = new Sprite(6, 9, 6, SpriteSheet.letters);
	public static Sprite smallK = new Sprite(6, 10, 6, SpriteSheet.letters);
	public static Sprite smallL = new Sprite(6, 11, 6, SpriteSheet.letters);
	public static Sprite smallM = new Sprite(6, 12, 6, SpriteSheet.letters);
	public static Sprite smallN = new Sprite(6, 13, 6, SpriteSheet.letters);
	
	public static Sprite smallO = new Sprite(6, 0, 7, SpriteSheet.letters);
	public static Sprite smallP = new Sprite(6, 1, 7, SpriteSheet.letters);
	public static Sprite smallQ = new Sprite(6, 2, 7, SpriteSheet.letters);
	public static Sprite smallR = new Sprite(6, 3, 7, SpriteSheet.letters);
	public static Sprite smallS = new Sprite(6, 4, 7, SpriteSheet.letters);
	public static Sprite smallT = new Sprite(6, 5, 7, SpriteSheet.letters);
	public static Sprite smallU = new Sprite(6, 6, 7, SpriteSheet.letters);
	public static Sprite smallV = new Sprite(6, 7, 7, SpriteSheet.letters);
	public static Sprite smallW = new Sprite(6, 8, 7, SpriteSheet.letters);
	public static Sprite smallX = new Sprite(6, 9, 7, SpriteSheet.letters);
	public static Sprite smallY = new Sprite(6, 10, 7, SpriteSheet.letters);
	public static Sprite smallZ = new Sprite(6, 11, 7, SpriteSheet.letters);
	
	public static Sprite smallZero = new Sprite(6, 0, 8, SpriteSheet.letters);
	public static Sprite smallOne = new Sprite(6, 1, 8, SpriteSheet.letters);
	public static Sprite smallTwo = new Sprite(6, 2, 8, SpriteSheet.letters);
	public static Sprite smallThree = new Sprite(6, 3, 8, SpriteSheet.letters);
	public static Sprite smallFour = new Sprite(6, 4, 8, SpriteSheet.letters);
	public static Sprite smallFive = new Sprite(6, 5, 8, SpriteSheet.letters);
	
	public static Sprite smallSix = new Sprite(6, 0, 9, SpriteSheet.letters);
	public static Sprite smallSeven = new Sprite(6, 1, 9, SpriteSheet.letters);
	public static Sprite smallEight = new Sprite(6, 2, 9, SpriteSheet.letters);
	public static Sprite smallNine = new Sprite(6, 3, 9, SpriteSheet.letters);
	
	public static Sprite smallSpace = new Sprite(6, 6, 8, SpriteSheet.letters);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.x = x * size;
		this.y = y * size;
		SIZE = size;
		this.sheet = sheet;
		pixels = new int[size*size];
		load();
	}
	
	private void load(){
		for(int y = 0; y < SIZE; y++){
			for(int x = 0 ; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}

}

package com.logan.game.graphics;

public class Sprite {

	public final int SIZE;
	public int[] pixels;
	public int x, y;
	private SpriteSheet sheet;
	
	//images
	public static Sprite heartsAce = new Sprite(32, 0, 0, SpriteSheet.cards);
	public static Sprite heartsTwo = new Sprite(32, 1, 0, SpriteSheet.cards);
	public static Sprite heartsThree = new Sprite(32, 2, 0, SpriteSheet.cards);
	public static Sprite heartsFour = new Sprite(32, 3, 0, SpriteSheet.cards);
	public static Sprite heartsFive = new Sprite(32, 4, 0, SpriteSheet.cards);
	public static Sprite heartsSix= new Sprite(32, 5, 0, SpriteSheet.cards);
	public static Sprite heartsSeven = new Sprite(32, 6, 0, SpriteSheet.cards);
	public static Sprite heartsEight = new Sprite(32, 7, 0, SpriteSheet.cards);
	public static Sprite heartsNine = new Sprite(32, 0, 1, SpriteSheet.cards);
	public static Sprite heartsTen = new Sprite(32, 1, 1, SpriteSheet.cards);
	public static Sprite heartsJack = new Sprite(32, 2, 1, SpriteSheet.cards);
	public static Sprite heartsQueen = new Sprite(32, 3, 1, SpriteSheet.cards);
	public static Sprite heartsKing = new Sprite(32, 4, 1, SpriteSheet.cards);
	
	public static Sprite diamondsAce = new Sprite(32, 0, 2, SpriteSheet.cards);
	public static Sprite diamondsTwo = new Sprite(32, 1, 2, SpriteSheet.cards);
	public static Sprite diamondsThree = new Sprite(32, 2, 2, SpriteSheet.cards);
	public static Sprite diamondsFour = new Sprite(32, 3, 2, SpriteSheet.cards);
	public static Sprite diamondsFive = new Sprite(32, 4, 2, SpriteSheet.cards);
	public static Sprite diamondsSix = new Sprite(32, 5, 2, SpriteSheet.cards);
	public static Sprite diamondsSeven = new Sprite(32, 6, 2, SpriteSheet.cards);
	public static Sprite diamondsEight = new Sprite(32, 7, 2, SpriteSheet.cards);
	public static Sprite diamondsNine = new Sprite(32, 0, 3, SpriteSheet.cards);
	public static Sprite diamondsTen = new Sprite(32, 1, 3, SpriteSheet.cards);
	public static Sprite diamondsJack = new Sprite(32, 2, 3, SpriteSheet.cards);
	public static Sprite diamondsQueen = new Sprite(32, 3, 3, SpriteSheet.cards);
	public static Sprite diamondsKing = new Sprite(32, 4, 3, SpriteSheet.cards);
	
	public static Sprite spadesAce = new Sprite(32, 0, 4, SpriteSheet.cards);
	public static Sprite spadesTwo = new Sprite(32, 1, 4, SpriteSheet.cards);
	public static Sprite spadesThree = new Sprite(32, 2, 4, SpriteSheet.cards);
	public static Sprite spadesFour = new Sprite(32, 3, 4, SpriteSheet.cards);
	public static Sprite spadesFive = new Sprite(32, 4, 4, SpriteSheet.cards);
	public static Sprite spadesSix = new Sprite(32, 5, 4, SpriteSheet.cards);
	public static Sprite spadesSeven = new Sprite(32, 6, 4, SpriteSheet.cards);
	public static Sprite spadesEight = new Sprite(32, 7, 4, SpriteSheet.cards);
	public static Sprite spadesNine = new Sprite(32, 0, 5, SpriteSheet.cards);
	public static Sprite spadesTen = new Sprite(32, 1, 5, SpriteSheet.cards);
	public static Sprite spadesJack = new Sprite(32, 2, 5, SpriteSheet.cards);
	public static Sprite spadesQueen = new Sprite(32, 3, 5, SpriteSheet.cards);
	public static Sprite spadesKing = new Sprite(32, 4, 5, SpriteSheet.cards);
	
	public static Sprite clubsAce = new Sprite(32, 0, 6, SpriteSheet.cards);
	public static Sprite clubsTwo = new Sprite(32, 1, 6, SpriteSheet.cards);
	public static Sprite clubsThree = new Sprite(32, 2, 6, SpriteSheet.cards);
	public static Sprite clubsFour = new Sprite(32, 3, 6, SpriteSheet.cards);
	public static Sprite clubsFive = new Sprite(32, 4, 6, SpriteSheet.cards);
	public static Sprite clubsSix = new Sprite(32, 5, 6, SpriteSheet.cards);
	public static Sprite clubsSeven = new Sprite(32, 6, 6, SpriteSheet.cards);
	public static Sprite clubsEight = new Sprite(32, 7, 6, SpriteSheet.cards);
	public static Sprite clubsNine = new Sprite(32, 0, 7, SpriteSheet.cards);
	public static Sprite clubsTen = new Sprite(32, 1, 7, SpriteSheet.cards);
	public static Sprite clubsJack = new Sprite(32, 2, 7, SpriteSheet.cards);
	public static Sprite clubsQueen = new Sprite(32, 3, 7, SpriteSheet.cards);
	public static Sprite clubsKing = new Sprite(32, 4, 7, SpriteSheet.cards);

	public static Sprite back = new Sprite(32, 7, 7, SpriteSheet.cards);
	public static Sprite voidCard = new Sprite(32, 0xff00ff00);
	public static Sprite outline = new Sprite(32, 5, 1, SpriteSheet.cards);
	
	//start words
	
	
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
	
	
	//end images
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	private void load(){
		for(int y = 0; y < SIZE; y ++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
	
	private void setColor(int color){
		for(int i = 0; i < SIZE * SIZE; i++){
			pixels[i] = color;
		}
	}
	
}

package logdog.graphics.morse;

import java.util.ArrayList;
import java.util.List;

public class Letter {
	private String code, simpleName;
	public static List<Letter> letters = new ArrayList<Letter>();

	public Letter(String code, String simpleName) {
		this.code = code;
		this.simpleName = simpleName;
		letters.add(this);
	}

	public String getCode() {
		return code;
	}

	public static Letter getLetter(String code) {
		code = code.trim();
		for (Letter let : letters) {
			if (code.equals(let.code)) {
				return let;
			}
		}
		return NIL;
	}
	
	public static String getWord(String code) {
		String ans = "";
		code = code.trim();
		for(String let : code.split("\\s")) {
			//System.out.print("\t code.split(\\s): "+ let);
			if(let.length() < 1) continue;
			if(let.charAt(0) == '&') {
				if(let.length() <= 1) continue;
				let = let.substring(1);
				ans += " ";
			}
			if(isValidLetter(let))
				ans += getLetter(let).simpleName;
		}
		return ans;
	}
	
	private static boolean isValidLetter(String s) {
		for (Letter let : letters) {
			if (s.equalsIgnoreCase(let.code)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return simpleName;
	}


	/* characters go here */

	public static Letter A = new Letter(".-", "A");
	public static Letter B = new Letter("-...", "B");
	public static Letter C = new Letter("-.-.", "C");
	public static Letter D = new Letter("-..", "D");
	public static Letter E = new Letter(".", "E");

	public static Letter F = new Letter("..-.", "F");
	public static Letter G = new Letter("--.", "G");
	public static Letter H = new Letter("....", "H");
	public static Letter I = new Letter("..", "I");
	public static Letter J = new Letter(".---", "J");

	public static Letter K = new Letter("-.-", "K");
	public static Letter L = new Letter(".-..", "L");
	public static Letter M = new Letter("--", "M");
	public static Letter N = new Letter("-.", "N");
	public static Letter O = new Letter("---", "O");

	public static Letter P = new Letter(".--.", "P");
	public static Letter Q = new Letter("--.-", "Q");
	public static Letter R = new Letter(".-.", "R");
	public static Letter S = new Letter("...", "S");
	public static Letter T = new Letter("-", "T");

	public static Letter U = new Letter("..-", "U");
	public static Letter V = new Letter("...-", "V");
	public static Letter W = new Letter(".--", "W");
	public static Letter X = new Letter("-..-", "X");
	public static Letter Y = new Letter("-.--", "Y");
	public static Letter Z = new Letter("--..", "Z");

	public static Letter ONE = new Letter(".----", "1");
	public static Letter TWO = new Letter("..---", "2");
	public static Letter THREE = new Letter("...--", "3");
	public static Letter FOUR = new Letter("....-", "4");
	public static Letter FIVE = new Letter(".....", "5");

	public static Letter SIX = new Letter("-....", "6");
	public static Letter SEVEN = new Letter("--...", "7");
	public static Letter EIGHT = new Letter("---..", "8");
	public static Letter NINE = new Letter("----.", "9");
	public static Letter ZERO = new Letter("-----", "0");

	public static Letter NIL = new Letter("", "nil");

	/* Characters End Here */
}

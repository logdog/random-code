package logdog.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import logdog.graphics.morse.Letter;

public class Keyboard implements KeyListener {

	private static boolean[] keys = new boolean[255];
	public static boolean space;

	public static int timeKeyDown, ups;
	public static int timeKeyUp;
	public static String currentCode = "";
	
	public static void update() {
		space = keys[KeyEvent.VK_SPACE];
		if (space) {
			timeKeyDown++;
		} else {
			timeKeyUp++;
		}
		ups++;
		if(ups % 30 == 0)
			System.out.println(currentCode + "\t" + Letter.getWord(currentCode));
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		if(timeKeyUp > 90) {
			currentCode += " &";
			timeKeyUp = 0;
		}
		else if(timeKeyUp > 30) {
			currentCode += " ";
			timeKeyUp = 0;
		}
	}
	
	
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		if(timeKeyDown > 10) {
			currentCode += "-";
			timeKeyDown = 0;
		}
		else if (timeKeyDown > 0) {
			currentCode += ".";
			timeKeyDown = 0;
		}
	}
}

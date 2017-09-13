package com.logan.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public boolean[] keys = new boolean[200];

	public static boolean up, down, left, right, space, enter, pause;

	public Keyboard() {

	}

	public void update() {
		up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		pause = keys[KeyEvent.VK_E];
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

}

package com.logan.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.logan.game.Game;

public class Mouse implements MouseListener, MouseMotionListener {

	public static int x = -1, y = -1, button = -1;

	public Mouse() {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		button = e.getButton();
		x = e.getX() / Game.SCALE;
		y = e.getY() / Game.SCALE;
		
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

}

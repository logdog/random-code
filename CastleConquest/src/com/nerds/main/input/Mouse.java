package com.nerds.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.nerds.main.Game;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{

	
	public static boolean leftPressed, rightPressed, scrollPressed;
	public static int xPos, yPos;
	
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		
	}

	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	
	public void mouseMoved(MouseEvent e) {
		xPos = e.getX() / Game.SCALE;
		yPos = e.getY() / Game.SCALE;
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)
			leftPressed = true;
		if(e.getButton() == 2)
			scrollPressed = true;
		if(e.getButton() == 3)
			rightPressed = true;
	}

	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1)
			leftPressed = false;
		if(e.getButton() == 2)
			scrollPressed = false;
		if(e.getButton() == 3)
			rightPressed = false;
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}

	
	public void mouseExited(MouseEvent e) {
		
		System.exit(1);
	}

}

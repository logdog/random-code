package logdog.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import logdog.Game;

public class Mouse implements MouseListener
{

	public static int mouseX, mouseY;
	public boolean pressed;
	
	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		mouseX = e.getX() / Game.SCALE;
		mouseY = e.getY() / Game.SCALE;
		pressed = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		mouseX = e.getX() / Game.SCALE;
		mouseY = e.getY() / Game.SCALE;
		pressed = false;
	}

	
	public void mouseEntered(MouseEvent e)
	{
		
	}

	
	public void mouseExited(MouseEvent e)
	{
		
	}

}

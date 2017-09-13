package codeclub;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	public boolean up, down, left, right, space;
	
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public void reset()
	{
		up = down = left = right = space = false;
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			up = true;
			down = left = right = false;
			break;
		case KeyEvent.VK_A:
			left = true;
			up = right = down = false;
			break;
		case KeyEvent.VK_S:
			down = true;
			up = left = right = false;
			break;
		case KeyEvent.VK_D:
			right = true;
			up = down = left = false;
			break;
		case KeyEvent.VK_SPACE:
			space = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			space = false;
	}

}

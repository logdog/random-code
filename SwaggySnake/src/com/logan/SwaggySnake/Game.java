package com.logan.SwaggySnake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;



public class Game extends Canvas implements Runnable{
	
	private ArrayList<Integer> snakeX = new ArrayList<Integer>();
	private ArrayList<Integer> snakeY = new ArrayList<Integer>();
	private int headX = width/2;
	private int headY = height / 2;
	public String direction = null;
	private static final long serialVersionUID = 1L;
	private static int width = 900;
	private static int height = width / 16 * 9;
	public boolean running = false;
	private Thread thread;
	private int blocksize = 6;
	JFrame frame = new JFrame();
	
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(running){
			update();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(){
		if(snakeX.isEmpty())
			snakeX.add(headX);
			snakeY.add(headY);
		moveSnake();
	}
	
	private void moveSnake() {
		for(int i = 0; i < snakeX.size(); i++){
			int temp = 
		}
		
	}

	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.orange);
		for(int i =0; i < snakeX.size(); i++){
			g.fillRect(headX, headY, blocksize, blocksize);
		}
		
		g.dispose();
		bs.show();
	}
	
	public Game(){
		Dimension size = new Dimension(width , height );
		setPreferredSize(size);
		frame = new JFrame();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setBackground(Color.black);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
	private class KeyHandler extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()){
			case KeyEvent.VK_W:
				direction = "up";
				break;
			case KeyEvent.VK_S:
				direction = "down";
				break;
			case KeyEvent.VK_A:
				direction = "left";
				break;
			case KeyEvent.VK_D:
				direction = "right";
				break;
			default:
				break;
			}
		}

	}
}

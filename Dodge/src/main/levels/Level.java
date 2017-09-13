package main.levels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.Graphics.Screen;
import main.entities.Enemy;
import main.entities.Entity;
import main.entities.Player;

public class Level {
	
	public static List<Entity> entities = new ArrayList<Entity>();

	public Level() {
		Random rand = new Random();
		
		for(int i = 0; i < 60; i++){
			entities.add(new Player(rand.nextInt(300), rand.nextInt(300)));
			entities.add(new Enemy(rand.nextInt(300), rand.nextInt(300)));
		}
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
	}

}

package com.nerds.main.graphics;

import java.util.ArrayList;
import java.util.List;

import com.nerds.main.Game;

public class AnimatedMobSprite {

	public List<Sprite> forward = new ArrayList<Sprite>();
	public List<Sprite> backward = new ArrayList<Sprite>();
	public List<Sprite> right = new ArrayList<Sprite>();
	public List<Sprite> left = new ArrayList<Sprite>();

	public Sprite currentSprite = Sprite.pink;

	public AnimatedMobSprite(Sprite sfs, Sprite sf1, Sprite sf2, Sprite sbs, Sprite sb1, Sprite sb2, Sprite srs, Sprite sr1, Sprite sr2, 
			Sprite sls, Sprite sl1, Sprite sl2) {
		forward.add(sfs);
		forward.add(sf1);
		forward.add(sf2);
		backward.add(sbs);
		backward.add(sb1);
		backward.add(sb2);
		right.add(srs);
		right.add(sr1);
		right.add(sr2);
		left.add(sls);
		left.add(sl1);
		left.add(sl2);
	}
	
	public AnimatedMobSprite(Sprite s){
		forward.add(s);
		backward.add(s);
		right.add(s);
	}
	
	public void update(int direction, boolean moving){
		if(moving){
			
			int time = Game.gameTime % 30;
			int index;
			if(time < 15)
				index = 1;
			else if(time < 30)
				index = 2;
			else
				index = 1;
			
			if(direction == 0){  //right
				currentSprite = right.get(index);
			}
			else if(direction == 1){ //down
				currentSprite = forward.get(index);
			}
			else if(direction == 2){ //left
				currentSprite = left.get(index);
			}
			else if(direction == 3){ //up
				currentSprite = backward.get(index);
			}
			else{
				currentSprite = Sprite.pink;
			}
		}
		else{
			if(direction == 0){  //right
				currentSprite = right.get(0);
			}
			else if(direction == 1){ //down
				currentSprite = forward.get(0);
			}
			else if(direction == 2){ //left
				currentSprite = left.get(0);
			}
			else if(direction == 3){ //up
				currentSprite = backward.get(0);
			}
			else{
				currentSprite = Sprite.pink;
			}
		}
			
	}
}

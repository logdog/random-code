package com.logan.game.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Action implements ActionListener{

	String action;
	
	public Action(String action){
		this.action = action;
	}
	
	public static boolean betUp = false, betDown = false, hit = false, hold = false, deal = false,nextRound=false, split = false, reduceAce = false;
	
	public void actionPerformed(ActionEvent e) {
		if(action.equals("Bet+")){
			betUp = true;
		}
		if(action.equals("Hit")){
			hit = true;
		}
			
		if(action.equals("Hold")){
			hold = true;
		}
			
		if(action.equals("Bet-")){
			betDown = true;
		}
			
		if(action.equals("Deal")){
			deal = true;
		}
		if(action.equals("Split")){
			split = true;
		}
		if(action.equals("Reduce Ace")){
			reduceAce = true;
		}
		if(action.equals("nextRound")){
			nextRound = true;
		}
			
	}
	
	public void update(){
		betUp = false; 
		betDown = false;
		hit = false; 
		//hold = false; 
		deal = false;
		split = false;
		reduceAce = false;
		nextRound= false;
	}

	
	

}

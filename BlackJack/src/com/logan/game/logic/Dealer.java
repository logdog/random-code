package com.logan.game.logic;

import java.util.ArrayList;
import java.util.Random;

import com.logan.game.graphics.Sprite;
import com.logan.game.player.Player;

public class Dealer {

	private Random random;
	public String name;
	//public boolean[] deck = new boolean[52]; 
	//hearts 0-12 diamonds13-25 spades26-38 clubs39-51
	
	
	public static ArrayList<Integer> cardsInDeck = new ArrayList<Integer>();
	
	public static ArrayList<Integer> cardsDiscarded = new ArrayList<Integer>();	
	
	public Dealer(String name) {
		this.name = name;
		random = new Random();
		reshuffle();
	}
	
	public void dealACard(Player player, int index, String location){
		if(cardsInDeck.size() > 0){
			int i = random.nextInt(cardsInDeck.size());
			if(location.equals("hand"))
				player.hand.add(cardsInDeck.get(i));
			if(location.equals("split"))
				player.splitHand.add(cardsInDeck.get(i));
			cardsDiscarded.add(cardsInDeck.get(i));
			cardsInDeck.remove(i);
		}else{
			reshuffle();
			
		}
	}
	
	/*public void test(){
		for(int i = 0; i < cardsInDeck.size(); i++){
			System.out.println("index num: " + i + "  |  value at i: " + cardsInDeck.get(i));
		}
		System.out.println("========");
		for(int i = 0; i < cardsDiscarded.size(); i++){
			System.out.println("Discard Pile contains " + cardsDiscarded.get(i));
		}
		System.out.println("========");
		System.out.println("Total Deck: " + cardsInDeck.size() + "\nDiscard Deck: " + cardsDiscarded.size());
		System.out.println("\n \n");
	} */
	
	public void reshuffle(){
		while(cardsInDeck.size() > 0){ //clears deck
			cardsInDeck.remove(0);
		}
		for(int i = 0; i < 52; i++){ //refills deck
			cardsInDeck.add(i);
		}
		while(cardsDiscarded.size() > 0){ //clears the discard
			cardsDiscarded.remove(0);
		}
		System.out.println("RESHUFFLED");
	}
	
	public void deal(Player player, int numCards, String location){
		for(int i = 0; i < numCards; i++){
			dealACard(player, i, location);
		}
	}
	
	
	
}

package com.logan.game.player;

import java.util.ArrayList;

import com.logan.game.graphics.Sprite;
import com.logan.game.input.Action;

public class Player {

	public ArrayList<Integer> hand;
	public ArrayList<Integer> splitHand;
	public double dollars;
	public double bet = 10;
	public double splitBet = 0;
	public String name;
	public int minimumBet = 10;
	public int handPoints;
	public int splitPoints;
	public boolean betting = true;
	public boolean hasSplit = false;
	public boolean canSplit = false;
	public int stage = 0;

	public boolean hasTurn = true;

	public Sprite[] cards = { Sprite.heartsAce, Sprite.heartsTwo, Sprite.heartsThree, Sprite.heartsFour,
			Sprite.heartsFive, Sprite.heartsSix, Sprite.heartsSeven, Sprite.heartsEight, Sprite.heartsNine,
			Sprite.heartsTen, Sprite.heartsJack, Sprite.heartsQueen, Sprite.heartsKing, Sprite.diamondsAce,
			Sprite.diamondsTwo, Sprite.diamondsThree, Sprite.diamondsFour, Sprite.diamondsFive, Sprite.diamondsSix,
			Sprite.diamondsSeven, Sprite.diamondsEight, Sprite.diamondsNine, Sprite.diamondsTen, Sprite.diamondsJack,
			Sprite.diamondsQueen, Sprite.diamondsKing, Sprite.spadesAce, Sprite.spadesTwo, Sprite.spadesThree,
			Sprite.spadesFour, Sprite.spadesFive, Sprite.spadesSix, Sprite.spadesSeven, Sprite.spadesEight,
			Sprite.spadesNine, Sprite.spadesTen, Sprite.spadesJack, Sprite.spadesQueen, Sprite.spadesKing,
			Sprite.clubsAce, Sprite.clubsTwo, Sprite.clubsThree, Sprite.clubsFour, Sprite.clubsFive, Sprite.clubsSix,
			Sprite.clubsSeven, Sprite.clubsEight, Sprite.clubsNine, Sprite.clubsTen, Sprite.clubsJack,
			Sprite.clubsQueen, Sprite.clubsKing };

	public int[] cardValue = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10,
			11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

	public int[] splitCardValue = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10,
			10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

	public int[] typeOfCard = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0,
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

	public Player(double dollars, String name) {
		hand = new ArrayList<Integer>();
		splitHand = new ArrayList<Integer>();
		this.dollars = dollars;
		this.name = name;
	}

	public void reduceAce(Player player) {
		for (int i = 0; i < player.hand.size(); i++) {
			if (player.hand.get(i) == 0) {
				player.cardValue[0] = 1;

			}
			if (player.hand.get(i) == 13) {
				player.cardValue[13] = 1;

			}
			if (player.hand.get(i) == 26) {
				player.cardValue[26] = 1;

			}
			if (player.hand.get(i) == 39) {
				player.cardValue[39] = 1;

			}
		}
	}

	public void reduceSplitAce() {
		for (int i = 0; i < splitHand.size(); i++) {
			if (splitHand.get(i) == 0) {
				splitCardValue[0] = 1;

			}
			if (splitHand.get(i) == 13) {
				splitCardValue[13] = 1;

			}
			if (splitHand.get(i) == 26) {
				splitCardValue[26] = 1;

			}
			if (splitHand.get(i) == 39) {
				splitCardValue[39] = 1;

			}
		}
	}

	public Player(String name) {
		hand = new ArrayList<Integer>();
		splitHand = new ArrayList<Integer>();
		this.name = name;
		dollars = 1000000;
	}

	public void split() {
		if (hand.size() > 1 && splitHand.size() == 0) {
			doubleBet();
			splitHand.add(hand.get(1));
			hand.remove(1);
			hasSplit = true;
		}
	}

	public int getHand(int i) {
		if (hand.size() > i) {
			return hand.get(i);
		} else {
			return 100;
		}
	}

	public int getSplitHand(int i) {
		if (splitHand.size() > i) {
			return splitHand.get(i);
		} else {
			return 100;
		}
	}

	public int getHandPoints() {
		return handPoints;
	}

	public int getSplitPoints() {
		return splitPoints;
	}

	public void increaseBet(int x) {
		if (dollars - (bet + x) >= 0)
			bet += x;
	}

	public void decreaseBet(int x) {
		if (bet - x >= minimumBet)
			bet -= x;
	}

	public void doubleBet() {
		if (dollars - 2 * bet >= 0) {
			splitBet = bet;
			dollars -= splitBet;
		}
	}

	public void placeBet(double bet2) {
		if (bet2 <= dollars && bet2 >= minimumBet) {
			dollars -= bet2;
		}
	}

	public int findHandValue() {
		handPoints = 0;
		for (int i = 0; i < hand.size(); i++) {
			handPoints += cardValue[hand.get(i)];
		}
		return handPoints;
	}

	public int findSplitValue() {
		splitPoints = 0;
		for (int i = 0; i < splitHand.size(); i++) {
			splitPoints += splitCardValue[splitHand.get(i)];
		}
		return splitPoints;
	}

	public void clearHand() {
		while (hand.size() > 0) {
			hand.remove(0);
		}
		while (splitHand.size() > 0) {
			splitHand.remove(0);
		}
	}

	public double getDollars() {
		return dollars;
	}

	public Sprite getCard(int i) {
		if (i >= 0 && i < cards.length) {
			return cards[i];
		} else {
			return Sprite.voidCard;
		}
	}

	public void getWinnings(Player p1, Player p2) {// p2 is the dealer
		if (p2.findHandValue() > 21) {
			p2.handPoints = 0; // dealer bust
		}

		if (p1.findHandValue() > 21) {
			p1.handPoints = 0;
		} // hand bust

		if (p1.splitPoints > 21) {
			p1.splitPoints = 0; // split bust
		}

		if (p1.splitPoints > p2.handPoints) { // split wins
			p1.dollars += 2 * splitBet;
		}

		if (p1.handPoints > p2.handPoints) { // hand wins
			p1.dollars += 2 * bet;
		}
		if (p1.handPoints == p2.handPoints && p1.handPoints > 0) { // hand ties
			p1.dollars += bet;
		}

		if (p1.splitPoints == p2.handPoints && p1.splitPoints > 0) { // split
																					// ties
			p1.dollars += splitBet;
		}
	}

	public void reset() {
		clearHand();
		bet = minimumBet;
		splitBet = 0;
		betting = true;
		hasTurn = true;
		canSplit = false;
		hasSplit = false;
		cardValue[0] = 11;
		cardValue[13] = 11;
		cardValue[26] = 11;
		cardValue[39] = 11;

		stage = 0;

	}

}

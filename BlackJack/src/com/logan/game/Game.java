package com.logan.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.logan.game.graphics.Screen;
import com.logan.game.graphics.Sprite;
import com.logan.game.graphics.SpriteSheet;
import com.logan.game.input.Action;
import com.logan.game.input.Keyboard;
import com.logan.game.logic.Dealer;
import com.logan.game.player.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static int height = 64 * 3, width = 16 * height / 9, scale = 3;
	private static String title = "BlackJack";
	// private int numPlayers;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Screen screen;
	//private int xOffset, yOffset;
	public Player me;
	public Player ai;
	public Dealer dealer;
	// private JPanel panel;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton backgroundButton; //deal
	private JButton b4;
	private JButton split;
	private Action action;
	private JButton reduceAce;
	private JButton nextRound;
	public boolean splitAllowed = false;
	private Player theDealer;

	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(scale * width, scale * height);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		// panel = new JPanel();
		key = new Keyboard();
		action = new Action("Something");
		me = new Player(100.0, "Logan");
		ai = new Player(me.getDollars(), "AI");
		dealer = new Dealer("DEALER");
		theDealer = new Player("THE DEALER");
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		b4 = new JButton();
		split = new JButton();
		backgroundButton = new JButton();
		reduceAce = new JButton();
		nextRound = new JButton();
		addKeyListener(key);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
	//	key.update();
		if (key.enter) {
			Action.nextRound = true;
		}

		// bet (and deal), playSplit (play with split hand),
		if (me.stage == 0) // // play (normal), reset
			bet(me);
		if (me.stage == 1)
			playSplit(me);
		if (me.stage == 2)
			play(me);
		if (me.stage == 3)
			waitForReset(me);

		action.update();
		me.findHandValue();
		me.findSplitValue();
		theDealer.findHandValue();

	}

	public void bet(Player player) {
		if(Dealer.cardsInDeck.size() < 16)
			dealer.reshuffle();
		
		if (Action.betUp) {
			player.increaseBet(5);
		}
		if (Action.betDown) {
			if (player.dollars >= player.minimumBet * 2) {
				player.decreaseBet(5);
			}
		}
		if (Action.deal) {

			if (player.bet >= player.minimumBet && player.bet <= player.dollars) {
				player.stage = 1;
				player.placeBet(player.bet); // TODO fix the bet problem //TODO
												// fix the money return problem
				dealer.deal(player, 2, "hand");
				dealer.deal(theDealer, 2, "hand");

				if (theDealer.findHandValue() > 21)
					theDealer.reduceAce(theDealer);
				if (theDealer.findHandValue() > 21)
					player.stage = 3;

				// see if the cards are the same type, i.e. two 6's, or 2 J's...
				if (player.hand.size() > 1) {
					if (player.typeOfCard[player.hand.get(0)] == player.typeOfCard[player.hand.get(1)]) {
						player.canSplit = true;
					}
				}

				// for testing only .... player.canSplit = true;

				if (player.dollars - (player.bet * 2) < player.minimumBet) {
					player.canSplit = false;
				}

				if (!player.canSplit) {
					player.stage = 2;
				}
			}
		}
	}

	public void playSplit(Player player) {
		if (player.canSplit) {
			if (Action.split) {
				player.split();// this splits it

				player.hasSplit = true;
				player.canSplit = false; // goes into second part (hitting and
											// holding)
			}
			if (Action.hold || Action.hit) {
				player.stage = 2;
				player.canSplit = false;
			}
		}

		if (player.hasSplit) {
			if (Action.hit) {
				dealer.deal(player, 1, "split");
			}
			if (player.findSplitValue() > 21) {
				player.reduceSplitAce();
				if (player.findSplitValue() > 21) {
					Action.hold = true;
				}
			}
			if (Action.reduceAce) {
				player.reduceSplitAce();
			}
			// to exit
			if (Action.hold) {
				Action.hold = false;
				Action.hit = false;// ends the split hand
				player.stage = 2;
			}
		}
	}

	public void dealerTurn() {
		while (theDealer.findHandValue() < 16) {
			dealer.deal(theDealer, 1, "hand");
			if (theDealer.findHandValue() > 21)
				theDealer.reduceAce(theDealer);
		}
	}

	public void play(Player player) {
		if (player.findHandValue() > 21) {
			player.reduceAce(player);
			if (player.findHandValue() > 21) {
				Action.hold = true;
				player.stage = 3;
			}
		}

		if (Action.hit && player.hasTurn) {
			dealer.deal(player, 1, "hand");
		}
		if (Action.hold && player.hasTurn) {
			player.hasTurn = false;
			player.stage = 3;
			if (player.findHandValue() <= 21)
				dealerTurn();
		}

		if (Action.reduceAce && player.hasTurn) {
			player.reduceAce(player);
		}

		player.findHandValue();
	}

	public void waitForReset(Player player) {
		if (Action.nextRound) {
			player.findHandValue();
			theDealer.findHandValue();
			/*
			 * if(player.findHandValue() > 21) player.handPoints = 0;
			 * if(player.findSplitValue() > 21) player.splitPoints = 0;
			 * if(theDealer.handPoints > 21) theDealer.handPoints = 0;
			 */

			player.getWinnings(me, theDealer);
			me.reset();
			theDealer.reset();
			// dealer.reshuffle();
			screen.clear();
			Action.hold = false;
			action.update();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		for (int i = 0; i < me.splitHand.size(); i++) {
			screen.renderCard(i * 32, height - 128, me.getCard(me.getSplitHand(i)));
			if (i + 1 == me.splitHand.size() && me.findSplitValue() < 21 && me.stage == 1)
				screen.renderCard((i + 1) * 32, height - 128, Sprite.outline);
		}

		for (int i = 0; i < me.hand.size(); i++) {
			screen.renderCard(i * 32, height - 64, me.getCard(me.getHand(i)));
			if (i + 1 == me.hand.size() && me.findHandValue() < 21 && me.stage == 2)
				screen.renderCard((i + 1) * 32, height - 64, Sprite.outline);
		}
		for (int i = 0; i < theDealer.hand.size(); i++) {
			screen.renderCard(i * 32 + 32 * 4, 0, theDealer.getCard(theDealer.getHand(i)));
			if (Action.hold == false || me.stage < 2)
				screen.renderCard(1 * 32 + 32 * 4, 0, Sprite.back);
		}

		if (me.findHandValue() > 21 && me.stage == 3) {
			screen.renderLetter(0 + 12 * 0, height - 64 + 24, Sprite.B, 0xff0000); // red
			screen.renderLetter(0 + 12 * 1, height - 64 + 24, Sprite.U, 0xff0000);
			screen.renderLetter(0 + 12 * 2, height - 64 + 24, Sprite.S, 0xff0000);
			screen.renderLetter(0 + 12 * 3, height - 64 + 24, Sprite.T, 0xff0000);
			screen.renderLetter(0 + 12 * 4, height - 64 + 24, Sprite.exclamation, 0xff0000);
		}

		if (me.findSplitValue() > 21) {
			screen.renderLetter(0 + 12 * 0, height - 128 + 24, Sprite.B, 0xff0000);
			screen.renderLetter(0 + 12 * 1, height - 128 + 24, Sprite.U, 0xff0000);
			screen.renderLetter(0 + 12 * 2, height - 128 + 24, Sprite.S, 0xff0000);
			screen.renderLetter(0 + 12 * 3, height - 128 + 24, Sprite.T, 0xff0000);
			screen.renderLetter(0 + 12 * 4, height - 128 + 24, Sprite.exclamation, 0xff0000);
		}

		// if hand is jack and ace or vice versa then say blackjack
		if (me.stage > 2) {
			if ((me.typeOfCard[me.hand.get(0)] == 0 && me.typeOfCard[me.hand.get(1)] == 10)
					|| (me.typeOfCard[me.hand.get(0)] == 10 && me.typeOfCard[me.hand.get(1)] == 0)) {
				screen.renderLetter(0 + 12 * 0, height - 64 + 24, Sprite.B, 0xff0000);
				screen.renderLetter(0 + 12 * 1, height - 64 + 24, Sprite.L, 0xff0000);
				screen.renderLetter(0 + 12 * 2, height - 64 + 24, Sprite.A, 0xff0000);
				screen.renderLetter(0 + 12 * 3, height - 64 + 24, Sprite.C, 0xff0000);
				screen.renderLetter(0 + 12 * 4, height - 64 + 24, Sprite.K, 0xff0000);
				screen.renderLetter(0 + 12 * 5, height - 64 + 24, Sprite.J, 0xff0000);
				screen.renderLetter(0 + 12 * 6, height - 64 + 24, Sprite.A, 0xff0000);
				screen.renderLetter(0 + 12 * 7, height - 64 + 24, Sprite.C, 0xff0000);
				screen.renderLetter(0 + 12 * 8, height - 64 + 24, Sprite.K, 0xff0000);
				screen.renderLetter(0 + 12 * 9, height - 64 + 24, Sprite.exclamation, 0xff0000);
			}
		}

		if (me.hasSplit) {
			if (me.splitHand.size() > 1) {
				if (me.typeOfCard[me.splitHand.get(0)] == 0 && me.typeOfCard[me.splitHand.get(1)] == 10
						|| me.typeOfCard[me.splitHand.get(0)] == 10 && me.typeOfCard[me.splitHand.get(1)] == 0) {
					screen.renderLetter(0 + 12 * 0, height - 128 + 24, Sprite.B, 0xff0000);
					screen.renderLetter(0 + 12 * 1, height - 128 + 24, Sprite.L, 0xff0000);
					screen.renderLetter(0 + 12 * 2, height - 128 + 24, Sprite.A, 0xff0000);
					screen.renderLetter(0 + 12 * 3, height - 128 + 24, Sprite.C, 0xff0000);
					screen.renderLetter(0 + 12 * 4, height - 128 + 24, Sprite.K, 0xff0000);
					screen.renderLetter(0 + 12 * 5, height - 128 + 24, Sprite.J, 0xff0000);
					screen.renderLetter(0 + 12 * 6, height - 128 + 24, Sprite.A, 0xff0000);
					screen.renderLetter(0 + 12 * 7, height - 128 + 24, Sprite.C, 0xff0000);
					screen.renderLetter(0 + 12 * 8, height - 128 + 24, Sprite.K, 0xff0000);
					screen.renderLetter(0 + 12 * 9, height - 128 + 24, Sprite.exclamation, 0xff0000);
				}
			}
		}

		if (me.stage == 3 && me.findHandValue() == theDealer.findHandValue()) {
			screen.renderLetter(0 + 12 * 0, height / 2, Sprite.T, 0x000000);
			screen.renderLetter(0 + 12 * 1, height / 2, Sprite.I, 0x000000);
			screen.renderLetter(0 + 12 * 2, height / 2, Sprite.E, 0x000000);
		}
		/*
		 * if(me.stage == 3 && me.findHandValue() > theDealer.findHandValue()){
		 * screen.renderLetter(0 + 12 * 0, height/2, Sprite.W, 0x000000);
		 * screen.renderLetter(0 + 12 * 1, height/2, Sprite.I, 0x000000);
		 * screen.renderLetter(0 + 12 * 2, height/2, Sprite.N, 0x000000);
		 * screen.renderLetter(0 + 12 * 3, height/2, Sprite.exclamation,
		 * 0x000000); }
		 * 
		 * if(me.stage == 3 && me.findHandValue() < theDealer.findHandValue()){
		 * screen.renderLetter(0 + 12 * 0, height/2, Sprite.L, 0x000000);
		 * screen.renderLetter(0 + 12 * 1, height/2, Sprite.O, 0x000000);
		 * screen.renderLetter(0 + 12 * 2, height/2, Sprite.S, 0x000000);
		 * screen.renderLetter(0 + 12 * 3, height/2, Sprite.T, 0x000000); }
		 */
		if (theDealer.handPoints > 21) {
			screen.renderLetter(164 + 12 * 0, 26, Sprite.B, 0x000000);
			screen.renderLetter(164 + 12 * 1, 26, Sprite.U, 0x000000);
			screen.renderLetter(164 + 12 * 2, 26, Sprite.S, 0x000000);
			screen.renderLetter(164 + 12 * 3, 26, Sprite.T, 0x000000);
			screen.renderLetter(164 + 12 * 4, 26, Sprite.exclamation, 0x000000);
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawString("Money Left: $" + me.dollars, 0, 15);
		g.drawString("Current Bet: $" + (me.bet + me.splitBet), 0, 30);
		g.drawString("Minimum Bet: $" + me.minimumBet, 0, 45);
		g.drawString("Split Points: " + me.splitPoints, 0, 60);
		g.drawString("Current Points: " + me.handPoints, 0, 75);
		if (me.stage == 3)
			g.drawString("CPU Points:     " + theDealer.handPoints, 0, 115);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		int size = 75;

		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);

		game.nextRound.setText("Click to Advance Round");
		game.nextRound.setSize(size, size);
		game.frame.add(game.nextRound, BorderLayout.SOUTH);
		game.nextRound.setVisible(true);
		game.nextRound.addActionListener(new Action("nextRound"));

		game.reduceAce.setText("Reduce A");
		game.reduceAce.setSize(size, size);
		game.reduceAce.setLocation(0, 0);
		game.frame.add(game.reduceAce, BorderLayout.WEST);
		game.reduceAce.setVisible(true);
		game.reduceAce.addActionListener(new Action("Reduce Ace"));

		game.b1.setText("Bet +");
		game.b1.setSize(size, size);
		game.b1.setLocation(0, size);
		game.frame.add(game.b1, BorderLayout.WEST);
		game.b1.setVisible(true);
		game.b1.addActionListener(new Action("Bet+"));

		game.b2.setText("Hit");
		game.b2.setSize(size, size);
		game.b2.setLocation(0, size * 2);
		game.frame.add(game.b2, BorderLayout.WEST);
		game.b2.setVisible(true);
		game.b2.addActionListener(new Action("Hit"));

		game.b3.setText("Hold");
		game.b3.setSize(size, size);
		game.b3.setLocation(0, height * scale - 3 * size);
		game.frame.add(game.b3, BorderLayout.WEST);
		game.b3.setVisible(true);
		game.b3.addActionListener(new Action("Hold"));

		game.b4.setText("Bet -");
		game.b4.setSize(size, size);
		game.b4.setLocation(0, height * scale - 2 * size);
		game.frame.add(game.b4, BorderLayout.WEST);
		game.b4.setVisible(true);
		game.b4.addActionListener(new Action("Bet-"));

		game.split.setText("Split");
		game.split.setSize(size, size);
		game.split.setLocation(0, height * scale - size);
		game.frame.add(game.split, BorderLayout.WEST);
		game.split.setVisible(true);
		game.split.addActionListener(new Action("Split"));

		game.backgroundButton.setText("Deal");
		game.backgroundButton.setSize(size, size);
		game.frame.add(game.backgroundButton, BorderLayout.WEST);
		game.backgroundButton.setVisible(true);
		game.backgroundButton.addActionListener(new Action("Deal"));

		game.frame.pack();

		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setBackground(Color.green);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}

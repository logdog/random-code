package com.logan.main;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/*
* This simple game will let you interact
* With the program using the java.util.Scanner Class
* 
* 
* Try to beat all 4 Levels!
* 
* Good Luck!
* 
* @author Logan Dihel
* @version 9/4/15
* 
* 
*/

public class GuessMyNumber {

	private Random rand;
	private Scanner scan;

	private int answer, guess, guessesLeft = 0;
	private String difficulty;

	public GuessMyNumber() {

		rand = new Random();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		GuessMyNumber gn = new GuessMyNumber();

		gn.init();
		gn.run();
	}

	private void init() {
		while (true) {
			System.out.println("Type \"Play\" to Play, Type \"Rules\" for Rules");
			String option = scan.next();
			if (option.equals("Play")) {
				System.out.println("OK Let's Get Started!");
				break;
			} else if (option.equals("Rules")) {
				System.out.println("================"); // the \n means "new
														// line", it skips a
														// line
				System.out.println("\nRules\n");
				System.out.println("Objective: Correctly Guess the Magic Number Before You Run out of Guesses\n");
				System.out.println("Step 1: Select A Difficulty\n");
				System.out.println("Easy:\t\tMagic Number is between 0-50\tGuesses: 10");
				System.out.println("Medium:\t\tMagic Number is between 0-100\tGuesses: 8");
				System.out.println("Hard:\t\tMagic Number is between 0-250\tGuesses: 6");
				System.out.println("Insanity:\tMagic Number is between 0-1000\tGuesses: 5\n");

				System.out.println("Now's Where The Fun Begins...\n");

				System.out.println("At the Beginning of Each Turn, You Guess A Number");
				System.out.println("The Computer Will Tell You if You are Too High, Too Low, or A Winner!");
				System.out.println("Try To Beat the Game On Each Unique Level!\n");
				System.out.println("Good Luck!\n");
				System.out.println("================\n");
			}
		}

		while (true) {
			System.out.println("Select A Difficulty: (Easy, Medium, Hard, Insanity)");
			difficulty = scan.next();
			if (difficulty.equals("Easy") || difficulty.equals("Medium") || difficulty.equals("Hard")
					|| difficulty.equals("Insanity"))
				break;
			else
				System.out.print("\nLet's try this again... ");
		}

		if (difficulty.equals("Easy")) {
			answer = rand.nextInt(51); // set the answer to a random integer
										// between [0,51)
			guessesLeft = 10; // AKA any number from 0-50 including 0 and 50
		} else if (difficulty.equals("Medium")) {
			answer = rand.nextInt(101);
			guessesLeft = 8;
		} else if (difficulty.equals("Hard")) {
			answer = rand.nextInt(251);
			guessesLeft = 6;
		} else {
			answer = rand.nextInt(1001);
			guessesLeft = 5;
		}

	}

	private void run() {
		while (guessesLeft > 0) {
			if (difficulty.equals("Easy"))
				System.out.print("Pick A Number Between 0 and 50");
			else if (difficulty.equals("Medium"))
				System.out.print("Pick A Number Between 0 and 100");
			else if (difficulty.equals("Hard"))
				System.out.print("Pick A Number Between 0 and 250");
			else
				System.out.print("Pick A Number Between 0 and 1000");

			System.out.println("\t(Guesses Remaining: " + guessesLeft + ")");

			try {
				guess = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That's not A Number! Enter a Number Next Time");
				String s = scan.next();
				continue; // loops back to the top of the while loop if string
							// is inputed
			}

			guessesLeft--;

			if (guessesLeft == 0)
				break;

			if (guess == answer) {
				break;
			} else if (guess < answer) {
				System.out.println("Too Low! Try Again");
			} else {
				System.out.println("Too High! Try Again");
			}

		}

		if (guess == answer) {
			if (difficulty.equals("Easy")) {
				System.out.println("Congradulations! That was impressive, for an amature...");
				System.out.println("Try to Beat the Game on Medium Next Time...");
				System.out.println("\nENDING NUMBER 1\n");

			} else if (difficulty.equals("Medium")) {
				System.out.println("WOW you're no beginner anymore. Keep it up!");
				System.out.println("I bet you can't beat the game on Hard..");
				System.out.println("\nENDING NUMBER 2\n");

			} else if (difficulty.equals("Hard")) {
				System.out.println("You're something kid. Nice Work!");
				System.out.println("To be honest I didn't think anybody would get this far");
				System.out.println("So Tell Me, What is You're Name?");
				String name = scan.next();
				System.out.println("I'm telling you " + name + ", You are going to go places someday");
				System.out.println(
						"This is only the beginning to a long and dedicated carrer playing professional GuessMyNumber...");
				System.out.println("That is... if you can beat the game on insanity...");
				System.out.println("\nENDING NUMBER 3\n");
			} else {
				System.out.println("****TRUMPETS PLAYING****");
				System.out.println("****PEOPLE CHEERING****");
				System.out.println("****Confetti Falling****\n");

				System.out.println("Congradulations! Noble one...");
				System.out.println("Thou hath given a quest, and conquored it with determination and vigor");
				System.out.println(
						"Thou shall forever be remembered as the chosen one, the one who answered all the prophecies");
				System.out.println("I shall bestoy on thou the highest honor that I can bestoy...");
				System.out.println("Great and Noble Child, I pronounce you as...\n");
				System.out.println("OH, wait, I forgot a semicolon...\n");
				System.err.println("Exception in thread \"main\" java.lang.Error: Unresolved compilation problem:");
				System.err.println("Syntax error, insert \";\" to complete BlockStatements\n");

				System.err.println("Always remember you're semicolons!!!!!!");

				System.err.println("\nENDING NUMBER 4\n");

			}
		} else {
			System.out.println("Nice try buddy.... the answer was " + answer);
			System.out.println("Better luck next time");
		}
	}
}

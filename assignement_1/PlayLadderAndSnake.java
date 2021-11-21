package assignment1;// -----------------------------------------------------
// Assignment 1
// Part II
// Written by: Michael Junior Osuji
// COMP249
// -----------------------------------------------------
//
// This program is a Snake and Ladder Game that supports 2 to 4 players. Since the game is only based on dice rolling,
// which is pure luck, it runs and prints every roll until one of the players win.

import java.util.Scanner;

/**
 * @author Michael Junior Osuji <osujim2012@gmail.com>
 * The PlayLadderAndSnake class is the driver class to the Ladder and Snake game.
 * Due date: FEB 8th, 2021
 **/
public class PlayLadderAndSnake {
    public static void main(String[] args) {
        // Initializes the Scanner
        Scanner keyIn = new Scanner(System.in);

        // Welcome prompt.
        System.out.print("Hi! Are you interested in a game of Ladder and Snake created by Michael Jr Osuji? If so, " +
                "enter anything, if not, please enter \"n\" : ");
        String userResponse = keyIn.next();
        System.out.println("");
        if (userResponse.compareToIgnoreCase("n")==0){
            System.out.print("Oh, that's okay! I'll be here if you want to. Have a good day!");
            keyIn.close();
            System.exit(0);
        }

        // Prompting the user for how many players they want in the game. If they do 4 attempts, end the program.
        System.out.print("I'm glad you want to! How many people are going to play? This game supports 2 to 4 players : ");
        int playerNb = keyIn.nextInt();
        int attempt = 0;
        while (playerNb>4 || playerNb<2){
            attempt++;
            System.out.println("");
            if (attempt==4){
                System.out.print("Bad Attempt 4! You have exhausted all your chances. Program will terminate!");
                System.exit(0);
            }
            System.out.print("Bad Attempt " + attempt + " - This program only supports 2 to 4 players. Please enter" +
                    "a valid number of players");
            if (attempt==3)
                System.out.print(". This is your last attempt");
            System.out.print(" : ");
            playerNb = keyIn.nextInt();
        }
        System.out.println("Starting a " + playerNb + " player game of Ladder And Snake!");

        // Creating a new Game and playing it.
        Game lAndS = new Game(playerNb);
        lAndS.play();
    }
}

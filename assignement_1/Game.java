package assignment1;// -----------------------------------------------------
// Assignment 1
// Part I
// Written by: Michael Junior Osuji
// COMP249
// -----------------------------------------------------

import java.util.Scanner;

/**
 * @author Michael Junior Osuji <osujim2012@gmail.com>
 * The Game class implements methods to play the Ladder and Snake game.
 * Due date: FEB 8th, 2021
 **/

public class Game {
    // These two arrays store the ladder and snake information. The first value of each element is the initial square
    // (bottom of ladder or head of snake) and the second is the square you land on from stepping on the first value.
    public int[][] ladders = {{1, 38}, {4, 14}, {9, 31}, {21, 42}, {28, 84}, {36, 44}, {51, 67},
            {71, 91}, {80, 100}};
    public int[][] snakes = {{16, 6}, {64, 19}, {95, 24}, {48, 30}, {64, 60}, {93, 68}, {97, 76}, {98, 78}};

    // Boolean that is initialized to true. It becomes false when this happens when a player is on square 100.
    private boolean gameOn;
    // name of the winner.
    private String winnerName;
    // array of players to store 2-4 players
    private Player[] players;
    //  initializing the scanner
    Scanner keyIn = new Scanner(System.in);


    /**
     * Constructor
     * @param numberOfPlayers number of players in the game
     */
    public Game(int numberOfPlayers) {
        players = new Player[numberOfPlayers];
        gameOn = true;
    }

    /**
     * Simulates a dice roll
     * @return Returns a random value between 1-6
     */
    public int flipDice() {
        // adding one because values range from 0-5 without it
        return (int) (Math.random() * 6 + 1);
    }

    /**
     * Plays the turn of the player given
     * @param player the player that needs to play their turn
     */
    public void playerTurn(Player player) {
        // Rolling the dice and advancing the player by the amount rolled.
        int diceRoll = flipDice();
        player.advanceSquares(diceRoll);

        // Checking if a player has won the game
        checkGame();

        // If the new square of the player is above 100, then advance by the difference between the previous square
        // and 100. Then go backwards by the dice roll minus the difference.
        if (player.getCurrentSquare()>100){
            int previousSquare = player.getCurrentSquare() - diceRoll;
            int squaresForward = 100 - previousSquare;
            int squaresBackwards = diceRoll-squaresForward;
            System.out.println(player.getName() + " rolled " + diceRoll + ". They would exceed 100 so they move forward " +
                    squaresForward + " squares, then move backwards " +
                    squaresBackwards + " squares.");
            player.setCurrentSquare(100 - squaresBackwards);
            System.out.println("They end at the square number " + player.getCurrentSquare());
        }

        else {
            System.out.println(player.getName() + " rolled " + diceRoll + ". They move " + diceRoll + " squares forward," +
                    " landing on square " + player.getCurrentSquare() + ".");
        }
        consequence(player);
    }

    /**
     * Performs the action(s) that follow the player's current case. (If there is the head of a snake or the base of a
     * ladder, then go to the top of the ladder or to the tail of the snake)
     * @param player the player who's roll's consequences need to be printed and put in effect
     */
    public void consequence(Player player){
        // Check if the player is on the base of a ladder and updates and prints if they are.
        int playerSquare = player.getCurrentSquare();
        for (int i = 0; i < ladders.length; i++){
            if (playerSquare==ladders[i][0]){
                player.setCurrentSquare(ladders[i][1]);
                System.out.println("This square has the base of a ladder! " + player.getName() + " climbs all the way to " +
                        "Square " + player.getCurrentSquare() + ".");
                break;
            }
        }
        // Check if a player won the game. This could only happen if a player used a ladder on square 80.
        checkGame();

        // Check if the player is on the head of a snake and updates and prints if they are.
        for (int i = 0; i < snakes.length; i++){
            if (playerSquare==snakes[i][0]){
                player.setCurrentSquare(snakes[i][1]);
                System.out.println("This square has the head of a snake! " + player.getName() + " slides all the way to " +
                        "Square " + player.getCurrentSquare() + ".");
                break;
            }
        }
    }

    /**
     * Check if one of the player is on square 100. If one is, then the game is over and record the winner's name.
     */
    public void checkGame() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getCurrentSquare() == 100) {
                gameOn = false;
                winnerName = players[i].getName();
            }
        }
    }

    /**
     * Plays this game.
     */
    public void play() {
        setPlayers();
        System.out.println("");
        // As long as there are no winners, play the turn of each players in order.
        while (gameOn) {
            for (int i = 0; i < players.length; i++) {
                playerTurn(players[i]);
                System.out.println("");
                checkGame();
                if (!gameOn)
                    break;
            }
            System.out.print("If you want to print the board then continue, enter \"d\", otherwise, enter anything else : ");
            String response = keyIn.next();
            if (response.compareToIgnoreCase("d")==0)
                draw();
        }
        // Exit message
        System.out.println("Congratulations " + winnerName + "! You have won this game of Ladder and Snake!");
        draw();
        System.out.println("");
        System.out.println("We hope you guys had a great time! Bye bye now.");
        keyIn.close();
        System.exit(0);
    }

    /**
     * Returns the number of digits in the given number
     * @param nb a number of type double
     * @return the number of digits in the number
     */
    public int digits(double nb) {
        int digitNb = 1;
        // While the number still has decimals, multiply it by 10 until it has no decimals. Add the number of decimals
        // to the number of total digits
        while (nb != (int) nb) {
            nb *= 10;
            digitNb++;
        }
        return digitNb;
    }

    /**
     *  Returns the information of the square that needs to be displayed when drawing the board.
     * @param square A square between 1-100
     * @return The string that needs to be displayed in the square
     */
    public String squareState(int square){
        boolean activated = false;
        String squareStatus = "";
        for (int i = 0; i < players.length; i++){
            if (players[i].getCurrentSquare()==square){
                squareStatus += players[i].getNumber();
                activated = true;
            }
        }
        if (activated) {
            int originalLength = squareStatus.length();
            for (int i = 0; i < 4 - originalLength; i++)
                squareStatus += " ";
            return squareStatus;
        }
        for (int i = 0; i < ladders.length; i++){
            if (square == ladders[i][0] || square ==ladders[i][1]){
                squareStatus = " L" + (i+1) + " ";
                break;
            }
        }
        for (int i =0; i< snakes.length;i++){
            if (square==snakes[i][0]||square==snakes[i][1]){
                squareStatus = " S" + (i+1) + " ";
                break;
            }
        }
        if (squareStatus=="")
            squareStatus = "    ";
        return squareStatus;
    }

    /**
     * Draws the updated board of the game.
     */
    public void draw() {
        for (int i = 0; i < 10; i++) {
            drawLine();

            // Prints the information of the square and the "wall" that separates each drawn square
            for (int j = 0; j < 10; j++){
                System.out.print("|");
                if (i%2!=0)
                    System.out.print(squareState(100-(i*10)-10+(j+1)));
                else
                    System.out.print(squareState(100-(i*10)-j));
            }
            // Prints the last "wall" of square at the end of the line.
            System.out.println("|");

        }
        drawLine();
    }

    /**
     * Draws the basic line template
     */
    private void drawLine() {
        for (int j = 0; j < 10; j++) {
            System.out.print("+");
            for (int k = 0; k < 4; k++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    /**
     * Initializes the player's personalized names and determines their order
     */
    public void setPlayers() {
        // Prompts the user(s) of each players names, if they write default, then the name is "Player x" where x is
        // the player's number
        for (int i = 0; i < players.length; i++) {
            System.out.print("Please enter the name of Player " + (i + 1) + ". If you want it to be Player " + (i + 1) +
                    ", then enter \"default\" : ");
            String name = keyIn.next();
            if (name.compareToIgnoreCase("default") == 0)
                players[i] = new Player("Player " + (i + 1),i+1);
            else
                players[i] = new Player(name + " (" + (i+1) + ")", i+1);

            System.out.println("Player " + (i + 1) + "'s name set to " + players[i].getName() + ".");
        }
        System.out.println("");
        System.out.println("Every players will now roll to determine their order. Higher rolls will go before lower" +
                " rolls. If multiple players roll the same they will re-roll against each-other.");

        // Array of players that are in the correct order.
        Player[] orderedPlayers = new Player[players.length];

        // Array of type double that holds the dice rolls of each player. The index represents the player number
        // (index 0 of diceRoll ==> Player 1 ...).
        // Double so that everytime a player re-rolls, they store the new re-roll as an unused decimal.
        double[] diceRoll = new double[players.length];
        // Boolean that becomes true if all of the values in diceRoll are equal to 0.
        boolean allZero = false;

        // Every player rolls.
        for (int i = 0; i < players.length; i++) {
            diceRoll[i] = flipDice();
            System.out.println(players[i].getName() + " rolled " + (int) diceRoll[i] + ".");
        }

        while (!allZero) {
            // The max value of diceRoll.
            double max = 0;

            // The amount of players that have a value equal to the max. If its equal to 1 then its unique.
            int count = 0;

            // The index of the first value that was equal to max
            int index = 0;

            // Goes through the diceRoll array and records the max and if its unique or not.
            for (int i = 0; i < diceRoll.length; i++) {
                if (diceRoll[i] == 0)
                    continue;
                else if (diceRoll[i] > max) {
                    max = diceRoll[i];
                    count = 1;
                    index = i;
                } else if (diceRoll[i] == max)
                    count++;
            }

            // If its unique, then assign the player to the first null element of the orderedPlayers array.
            if (count == 1) {
                diceRoll[index] = 0;
                for (int i = 0; i < orderedPlayers.length; i++) {
                    if (orderedPlayers[i] == null) {
                        orderedPlayers[i] = new Player(players[index]);
                        System.out.println("");
                        System.out.print(orderedPlayers[i].getName() + " will go ");
                        switch (i) {
                            case 0:
                                System.out.println("first.");
                                break;
                            case 1:
                                System.out.println("second.");
                                break;
                            case 2:
                                System.out.println("third.");
                                break;
                            case 3:
                                System.out.println("fourth.");
                                break;
                        }
                        break;
                    }
                }
            }
            // if it is not unique, then break the tie between all of the rolls that are equal to the max and record
            // the new roll as a new decimal.
            else {
                System.out.println("");
                System.out.println("Attempting to break the tie!");
                for (int i = 0; i < diceRoll.length; i++) {
                    if (diceRoll[i] == max) {
                        int roll = flipDice();
                        System.out.println(players[i].getName() + " rolled " + roll + ".");
                        diceRoll[i] += roll / (Math.pow(10, digits(diceRoll[i])));
                    }

                }
            }

            int zeroCount = 0;
            for (int i = 0; i < diceRoll.length; i++) {
                if (diceRoll[i] == 0)
                    zeroCount++;
            }
            if (zeroCount == diceRoll.length)
                allZero = true;
        }
        // Once the orders are assigned, make the initial player array equal to the properly ordered one.
        players = orderedPlayers;
        }
    }

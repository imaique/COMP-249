package assignment1;// -----------------------------------------------------
// Assignment 1
// Part I
// Written by: Michael Junior Osuji
// COMP249
// -----------------------------------------------------
/**
 * @author Michael Junior Osuji <osujim2012@gmail.com>
 * The Player class implements methods to store and update the player's names and squares.
 * Due date: FEB 8th, 2021
 **/

public class Player {

    private int currentSquare;
    private String name;
    private int number;

    /**
     * Constructor
     * @param givenName name of a Player.
     * @param nb the player's number (1-4 according to the Player 1-4).
     **/
    public Player(String givenName, int nb){
        currentSquare = 0;
        name = givenName;
        number = nb;
    }

    /**
     * Copy Constructor
     * @param playerX player that is being copied.
     **/
    public Player(Player playerX){
        this.currentSquare = playerX.getCurrentSquare();
        this.name = playerX.getName();
        this.number = playerX.getNumber();
    }

    /**
     * Getter for this player's number.
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setter of this player's number
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Advances the player's square by the number given.
     * @param roll number of squares by which to advance this player's square.
     */
    public void advanceSquares(int roll){
        currentSquare += roll;
    }

    /**
     * Setter for the current square of this player.
     * @param newSquare the new square
     **/
    public void setCurrentSquare(int newSquare){
        currentSquare = newSquare;
    }

    /**
     * Getter for the  of this player.
     * @return int: the current square
     **/
    public String getName(){
        return name;
    }

    /**
     * Setter for the name of this player.
     * @param givenName the name
     **/
    public void setName(String givenName){
        name = givenName;
    }

    /**
     * Getter for the square.
     * @return int: the current square
     **/
    public int getCurrentSquare() {
        return currentSquare;
    }
}

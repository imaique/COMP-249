package a3;

/**
 * Exception class that is the parent of the two other custom exceptions.
 */
public class InvalidException extends Exception{
    private String message;


    public InvalidException(){
        super("Error: Input row cannot be parsed due to missing information.");
    }

    public InvalidException(String message){
        super(message);
    }
}

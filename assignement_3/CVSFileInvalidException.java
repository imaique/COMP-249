package a3;

/**
 * Exception that handles whenever a file is missing attributes. automatically prints the corresponding errors
 */
public class CVSFileInvalidException extends InvalidException {

    public CVSFileInvalidException(String fileName, String message){
        super();
        System.err.println(super.getMessage());
        System.err.println("File " + fileName + " is invalid: " + message);
        System.err.println("File is not converted to JSON");
    }
}

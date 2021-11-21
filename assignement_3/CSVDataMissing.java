package a3;

/**
 * Exception class that handles when data that are not attributes are missing in a CSV file. automatically prints the corresponding errors
 */
public class CSVDataMissing extends InvalidException{
    private int lineNb;
    private String info;
    private String filename;

    public CSVDataMissing(String fileName, int lineNb, String info){
        super();
        this.lineNb = lineNb;
        this.filename=fileName;
        this.info = info;

        System.err.println(super.getMessage());
        System.err.println("In file " + fileName + " line " + lineNb + " not converted to JSON: " + info);
    }

    public int getLineNb() {
        return lineNb;
    }

    public String getFilename() {
        return filename;
    }

    public String getInfo() {
        return info;
    }

}

package a3;

/**
 * Custom tokenizer class
 */

public class Tokenizer {
    private String line;
    private char delimiter;
    public Tokenizer(String line, char delimiter){
        this.line=line;
        this.delimiter=delimiter;
    }

    /**
     * custom next() function that ressembles the next() of StringTokenizer
     * @return the next element, does not skip empty elements and make sure to ignore the commas inside quotation marks.
     */
    public String next(){
        String next = "";
        for(int i =0 ; i< line.length();i++){
            if (line.charAt(i)=='"'){
                line = line.substring(1);
                while (line.charAt(i)!='"'){
                    i++;
                }
            }
            if (line.charAt(i)==delimiter||i==line.length()-1){

                next=line.substring(0,i);
                if (next.length()>1 && next.charAt(next.length() - 1) == '"') {

                    next = next.substring(0, next.length() - 1);

                }
                line=line.substring(i+1);
                break;
            }
        }
        return next;
    }

}

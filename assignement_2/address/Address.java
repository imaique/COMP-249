// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package address;

import java.util.Objects;
/**
 * Base Address class. Only contains the valid from and valid to attributes.
 * @author Michael
 */
public class Address {
    private String validFrom;
    private String validTo;

    protected Address(){ }

    /**
     * Parametirized Address Constructor
     * @param validFrom Date from which the address is valid.
     * @param validTo The last date where the address is valid.
     */
    protected Address(String validFrom, String validTo){
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    /**
     *
     * @param original original Address to be copied.
     */
    protected Address(Address original){
        this.validFrom = original.validFrom;
        this.validTo = original.validTo;
    }


    public String getValidFrom(){
        return validFrom;
    }

    public void setValidFrom(String validFrom){
        this.validFrom = validFrom;
    }

    public String getValidTo(){
        return validTo;
    }

    public void setValidTo(String validTo){
        this.validTo = validTo;
    }

    /**
     * Method that returns a 2D array of converted dates (String to Integer)
     * @return
     */
    public int[][] getIntegerDates(){
        return new int[][]{convertDates(validFrom), convertDates(validTo)};
    }

    /**
     * Converts the validFrom or validTo Strings to integers.
     * @param date String date that is either the validFrom or validTo
     * @return An integer array of the for {YYYY,MM,DD}
     */
    public static int[] convertDates(String date){
        // Array of length 3 for the 3 numbers to represent dates.
        int[] converted = new int[3];
        String temp = "";
        int index = 0;
        for (int i = 0; i<=date.length();i++){
            // If it's not a dash or the end of the string, then add the character to the temp.
            if (i!= date.length() && date.charAt(i)!='-')
                temp += date.charAt(i);
            else{
                converted[index]= Integer.parseInt(temp);
                temp = "";
                index++;
            }

        }
        return converted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getValidFrom(), address.getValidFrom()) &&
                Objects.equals(getValidTo(), address.getValidTo());
    }


    @Override
    public String toString() {
        return "It is valid from " + validFrom + " to " + validTo + ". ";
    }
}

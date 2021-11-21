// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressMap;

import java.util.Objects;
/**
 * Locale Class that identifies the relevant information about a country when identifying some addresses.
 * @author Michael
 */
public class Locale {
    private String letterCountryCode;
    private int numCountryCode;
    private String countryName;

    public Locale(){}

    /**
     * Parameterized Constructor for the Locale
     * @param letterCountryCode The two letter code that represents the country.
     * @param numCountryCode The numeric code that represents the same country.
     * @param countryName The string name of the country.
     */
    public Locale(String letterCountryCode, int numCountryCode, String countryName){
        this.letterCountryCode = letterCountryCode;
        this.numCountryCode = numCountryCode;
        this.countryName = countryName;

    }

    public int getNumCountryCode() {
        return numCountryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getLetterCountryCode() {
        return letterCountryCode;
    }

    public Locale(Locale original){
        this.letterCountryCode = original.letterCountryCode;
        this.numCountryCode = original.numCountryCode;
        this.countryName = original.countryName;
    }

    @Override
    public String toString() {
        return "It has the locale of " + countryName + " described by the letter country code " + letterCountryCode
                + " and the numeric country code " + numCountryCode + ". ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locale)) return false;
        Locale locale = (Locale) o;
        return numCountryCode == locale.numCountryCode &&
                Objects.equals(letterCountryCode, locale.letterCountryCode) &&
                Objects.equals(countryName, locale.countryName);
    }

}

// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------

package addressWeb;

import address.Address;

import java.util.Objects;

/**
 * public Telecom Address class that extends Address. It holds the complete information of a phone number.
 * Due date: March 8th, 2021
 * @author Michael
 */
public class TelecomAddress extends Address {
    private String countryCode;
    private String nationalDirectDialingPrefix;
    private int areaCode;
    private long number;
    private int extension;
    private String physicalType;

    public int getAreaCode() {
        return areaCode;
    }

    public int getExtension() {
        return extension;
    }

    public long getNumber() {
        return number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNationalDirectDialingPrefix() {
        return nationalDirectDialingPrefix;
    }

    public String getPhysicalType() {
        return physicalType;
    }

    public void setNationalDirectDialingPrefix(String nationalDirectDialingPrefix) {
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public void setPhysicalType(String physicalType) {
        this.physicalType = physicalType;
    }

    public TelecomAddress(){
        super();
    }

    /**
     * Telecom Address parameterized Constructor
     * @param countryCode the International Direct Dialing (IDD) for a country.
     * @param nationalDirectDialingPrefix the prefix to make a call within a country between different cities or areas
     * @param areaCode the code for an area or city
     * @param number the telephone number
     * @param extension the extension of the telephone number
     * @param physicalType the type of device, such as phone, fax, mobile, pager, and so on
     */
    public TelecomAddress(String validFrom, String validTo, String countryCode, String nationalDirectDialingPrefix,
                          int areaCode, long number, int extension, String physicalType){
        super(validFrom,validTo);
        this.countryCode = countryCode;
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
        this.areaCode = areaCode;
        this.number = number;
        this.extension = extension;
        this.physicalType = physicalType;
    }

    /**
     * Telecom Address copy Constructor
     * @param original
     */
    public TelecomAddress(TelecomAddress original){
        super(original.getValidFrom(), original.getValidTo());
        this.countryCode = original.countryCode;
        this.nationalDirectDialingPrefix = original.nationalDirectDialingPrefix;
        this.areaCode = original.areaCode;
        this.number = original.number;
        this.extension = original.extension;
        this.physicalType = original.physicalType;
    }



    @Override
    public String toString() {
        return "The telecom address is " + countryCode + " " + nationalDirectDialingPrefix
                + areaCode + " " + number + " ext. " + extension + " and is of type " + physicalType + ". " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TelecomAddress that = (TelecomAddress) o;
        return areaCode == that.areaCode &&
                number == that.number &&
                extension == that.extension &&
                Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(nationalDirectDialingPrefix, that.nationalDirectDialingPrefix) &&
                Objects.equals(physicalType, that.physicalType);
    }


}

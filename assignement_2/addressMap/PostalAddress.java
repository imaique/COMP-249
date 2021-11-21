// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressMap;

import address.Address;

abstract class PostalAddress extends Address {
    private String addressLine;
    private String city;
    private String regionOrState;
    private String zipOrPostCode;

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegionOrState() {
        return regionOrState;
    }

    public void setRegionOrState(String regionOrState) {
        this.regionOrState = regionOrState;
    }

    public String getZipOrPostCode() {
        return zipOrPostCode;
    }

    public void setZipOrPostCode(String zipOrPostCode) {
        this.zipOrPostCode = zipOrPostCode;
    }

    public PostalAddress(){
        super();
    }

    /**
     * Parameterized constructor of a Postal Address
     * @param validFrom Date from which the address is valid.
     * @param validTo The last date where the address is valid.
     * @param addressLine The address number and road identification.
     * @param city The city that corresponds to the Address
     * @param regionOrState The region or State that corresponds to the address
     * @param zipOrPostCode The zip or Post Code corresponding to the Postal Address
     */
    public PostalAddress(String validFrom, String validTo, String addressLine,String city,String regionOrState,
                         String zipOrPostCode){
        super(validFrom,validTo);
        this.addressLine = addressLine;
        this.city = city;
        this.regionOrState = regionOrState;
        this.zipOrPostCode = zipOrPostCode;
    }

    @Override
    public String toString() {
        return "It is a type of postal address and is located at " + addressLine + ", " + city + ", " + regionOrState + ". "
                + zipOrPostCode +". " + super.toString();
    }
}

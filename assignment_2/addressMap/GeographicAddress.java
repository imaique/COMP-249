// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressMap;

import address.Address;

import java.util.Objects;

public class GeographicAddress extends PostalAddress {
    Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    /**
     * Parametirized constructor of a Postal Address
     * @param addressLine The address number and road identification.
     * @param city The city that corresponds to the Address
     * @param regionOrState The region or State that corresponds to the address
     * @param zipOrPostCode The zip or Post Code corresponding to the Postal Address
     * @param locale The Locale of the Postal Address that informs the necessary information about which country
     *               the address is located.
     */
    public GeographicAddress(String validFrom, String validTo, String addressLine, String city, String regionOrState,
                             String zipOrPostCode, Locale locale){
        super(validFrom,validTo,addressLine,city,regionOrState,zipOrPostCode);
        this.locale = locale;
    }

    public GeographicAddress() {
        super();
    }

    public GeographicAddress(GeographicAddress original){
        super(original.getValidFrom(), original.getValidTo(), original.getAddressLine(), original.getCity(), original.getRegionOrState(),
                original.getZipOrPostCode());
        this.locale = original.locale;
    }

    @Override
    public String toString() {
        return "This is a Geographic Address. " + locale.toString() + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeographicAddress)) return false;
        if (!super.equals(o)) return false;
        GeographicAddress that = (GeographicAddress) o;
        return Objects.equals(locale, that.locale);
    }


}

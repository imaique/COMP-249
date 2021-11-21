// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressMap;

import address.Address;
import addressWeb.TelecomAddress;

import java.util.Objects;
/**
 * Web Page Address class. Extends Postal address and contains the attribute telecomAddress.
 * @author Michael
 */
public class GeneralDeliveryAddress extends PostalAddress {
    private TelecomAddress telecomAddress;

    public TelecomAddress getTelecomAddress() {
        return telecomAddress;
    }

    public void setTelecomAddress(TelecomAddress telecomAddress) {
        this.telecomAddress = telecomAddress;
    }

    public GeneralDeliveryAddress(){
        super();
    }
    /**
     * Parametirized constructor of a Postal Address
     * @param addressLine The address number and road identification.
     * @param city The city that corresponds to the Address
     * @param regionOrState The region or State that corresponds to the address
     * @param zipOrPostCode The zip or Post Code corresponding to the Postal Address
     * @param telecomAddress The information of the telecommunication device related to this address.
     */
    public GeneralDeliveryAddress(String validFrom, String validTo, String addressLine, String city, String regionOrState,
                                  String zipOrPostCode, TelecomAddress telecomAddress){
        super(validFrom,validTo,addressLine,city,regionOrState,zipOrPostCode);
        this.telecomAddress = telecomAddress;
    }

    public GeneralDeliveryAddress(GeneralDeliveryAddress original){
        super(original.getValidFrom(), original.getValidTo(), original.getAddressLine(), original.getCity(), original.getRegionOrState(),
                original.getZipOrPostCode());
        this.telecomAddress = original.telecomAddress;
    }

    @Override
    public String toString() {
        return "This is a General Delivery Address. " + super.toString() + telecomAddress.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GeneralDeliveryAddress that = (GeneralDeliveryAddress) o;
        return Objects.equals(telecomAddress, that.telecomAddress);
    }

}

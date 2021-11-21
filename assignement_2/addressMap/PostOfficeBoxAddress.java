// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressMap;

import address.Address;

import java.util.Objects;

public class PostOfficeBoxAddress extends GeographicAddress {
    private int boxLobbyDoorCode;

    public int getBoxLobbyDoorCode() {
        return boxLobbyDoorCode;
    }

    public void setBoxLobbyDoorCode(int boxLobbyDoorCode) {
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    public PostOfficeBoxAddress(){
        super();
    }

    /**
     * Parametirized constructor of a Postal Address
     * @param addressLine The address number and road identification.
     * @param city The city that corresponds to the Address
     * @param regionOrState The region or State that corresponds to the address
     * @param zipOrPostCode The zip or Post Code corresponding to the Postal Address
     * @param boxLobbyDoorCode The code of the corresponding box at the address.
     */
    public PostOfficeBoxAddress(String validFrom, String validTo, String addressLine, String city, String regionOrState,
                                String zipOrPostCode, Locale locale, int boxLobbyDoorCode) {
        super(validFrom, validTo, addressLine, city, regionOrState, zipOrPostCode, locale);
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    public PostOfficeBoxAddress(PostOfficeBoxAddress original){
        super(original.getValidFrom(), original.getValidTo(), original.getAddressLine(), original.getCity(), original.getRegionOrState(),
                original.getZipOrPostCode(), original.locale);
        this.boxLobbyDoorCode = original.boxLobbyDoorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PostOfficeBoxAddress that = (PostOfficeBoxAddress) o;
        return boxLobbyDoorCode == that.boxLobbyDoorCode;
    }

    @Override
    public String toString() {
        return "This is a Post Office Box Address with " + boxLobbyDoorCode + " as a lobby code. It is a type of " +
                super.toString().substring(10);
    }
}

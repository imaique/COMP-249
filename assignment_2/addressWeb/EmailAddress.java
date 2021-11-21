// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------

package addressWeb;

import address.Address;

import java.util.Objects;

public class EmailAddress extends WebAddress {
    private String userName;
    private String TLD;

    public String getUserName() {
        return userName;
    }

    public String getTLD() {
        return TLD;
    }

    public void setTLD(String TLD) {
        this.TLD = TLD;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public EmailAddress(){
        super();
    }

    /**
     *
     * @param validFrom Date from which the address is valid.
     * @param validTo The last date where the address is valid.
     * @param userName The userName of the email address
     * @param domainName the domain under which the email address is under.
     * @param TLD The Top Level Domain of the email address.
     */
    public EmailAddress(String validFrom, String validTo, String userName, String domainName, String TLD){
        super(validFrom, validTo,domainName);
        this.userName = userName;
        this.TLD = TLD;
    }
    public EmailAddress(EmailAddress original){
        super(original.getValidFrom(), original.getValidTo(), original.getDomainName());
        this.userName = userName;
        this.TLD = TLD;
    }

    @Override
    public String toString() {
        return "This is an email address described by " + userName + "@" + this.getDomainName() + "." + TLD + ". " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(TLD, that.TLD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, TLD);
    }
}

// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------
package addressWeb;

import address.Address;

abstract class WebAddress extends Address {
    private String domainName;

    public String getDomainName(){
        return domainName;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    protected WebAddress(){
        super();
    }

    /**
     * Parameterized constructor of the website
     * @param validFrom Date from which the address is valid.
     * @param validTo The last date where the address is valid.
     * @param domainName the name of the domain of the website
     */
    protected WebAddress(String validFrom, String validTo,String domainName){
        super(validFrom,validTo);
        this.domainName=domainName;
    }

    protected WebAddress(WebAddress original){
        super(original.getValidFrom(),original.getValidTo());
        this.domainName = original.domainName;
    }



}

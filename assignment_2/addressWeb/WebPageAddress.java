// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------

package addressWeb;
import address.Address;
/**
 * Web Page Address class. Extends Web Address and contains the resource Name of the web page.
 * @author Michael
 */
public class WebPageAddress extends WebAddress {

    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public WebPageAddress(){
        super();
    }

    /**
     * Parameterized Web Page Address Constructor
     * @param validFrom Date from which the address is valid.
     * @param validTo The last date where the address is valid.
     * @param domainName
     * @param resourceName
     */
    public WebPageAddress(String validFrom, String validTo, String domainName, String resourceName){
        super(validFrom,validTo,domainName);
        this.resourceName= "/" + resourceName;
    }

    public WebPageAddress(String validFrom, String validTo, String domainName){
        super(validFrom,validTo,domainName);
        this.resourceName = "";
    }

    public WebPageAddress(WebPageAddress original){
        super(original.getValidFrom(), original.getValidTo(),original.getDomainName());
        this.resourceName = original.resourceName;
    }

    @Override
    public String toString() {

        return "This is a webpage address located at www." + this.getDomainName() + resourceName + ". " + super.toString();
    }
}

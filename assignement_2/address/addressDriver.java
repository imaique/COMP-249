// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// -----------------------------------------------------

package address;

import addressMap.GeneralDeliveryAddress;
import addressMap.GeographicAddress;
import addressMap.Locale;
import addressMap.PostOfficeBoxAddress;
import addressWeb.EmailAddress;
import addressWeb.TelecomAddress;
import addressWeb.WebPageAddress;

/**
 * Driver class. Creates several addresses, displays their information and traces the obsolete ones depending on a passed date.
 * @author Michael
 */
public class addressDriver{
    public static void main(String[] args){
        // Create the locales as described in the assignment description. final because they do not change.
        final Locale CANADA = new Locale("CA",124,"Canada");
        final Locale USA = new Locale("US", 840, "The United States of America");
        final Locale IRAN = new Locale("IR", 364, "The Islamic Republic of Iran");
        final Locale INDIA = new Locale("IN", 356, "The Republic of India");

        // Creating 18 various addresses.
        Address[] addresses = new Address[18];
        addresses[0] = new TelecomAddress("2015-08-21","2023-02-12", "+1",
                "(0)", 208,1234567, 789, "mobile");
        addresses[1] = new GeneralDeliveryAddress("2015-08-21","2023-02-12","2901 Lake St",
                "Montreal", "Quebec", "H9B 2K5", (TelecomAddress)addresses[0]);
        addresses[2] = new WebPageAddress("2016-03-11","2024-09-23","michael","home");
        addresses[3] = new EmailAddress("2016-03-11","2024-09-23","michael","gmail","ca");
        addresses[4] = new PostOfficeBoxAddress("2015-03-17","2018-02-23","329 Look St",
                "Longueuil", "Quebec", "J4Y L9K", CANADA,501);
        addresses[5] = new GeographicAddress("2015-08-21","2023-02-12", "2902 Lake St",
                "Montreal", "Quebec","H9B 2K6",CANADA);

        addresses[6] = new TelecomAddress("2018-08-21","2021-02-12", "+1",
                "(0)", 208,7654321, 789, "fixed");
        addresses[7] = new TelecomAddress("2019-01-21","2026-12-11", "+3",
                "(7)", 232,1234257, 379, "mobile");

        addresses[8] = new GeneralDeliveryAddress("2013-08-21","2014-05-12","291 Lake St",
                "Ottawa", "Ontario", "H9C 2K5", (TelecomAddress)addresses[6]);
        addresses[9] = new GeneralDeliveryAddress("2018-02-11","2021-04-15","29 Lake St",
                "Winnipeg", "Manitoba", "Z8B 2G5", (TelecomAddress)addresses[7]);

        addresses[10] = new WebPageAddress("2015-03-17","2018-02-23","youtube","concordia");
        addresses[11] = new WebPageAddress("2021-06-11","2021-09-23","concordia","library");

        addresses[12] = new PostOfficeBoxAddress("2016-02-17","2017-01-23","963 Alook St",
                "Mumbai", "Maharashtra", "K4D D9K", INDIA,301);
        addresses[13] = new PostOfficeBoxAddress("2015-03-17","2018-02-23","907 Tokk Blv.",
                "Shiraz", "Fars", "D4H K9B", CANADA,331);

        addresses[14] = new EmailAddress("2019-03-18","2020-03-13","karim","concordia","qc.ca");
        addresses[15] = new EmailAddress("2019-03-18","2020-03-13","karim","concordia","qc.ca");

        addresses[16] = new GeographicAddress("2011-08-21","2013-02-12", "2908 Lake St",
                "Montreal", "Quebec","H9B 2K9",CANADA);
        addresses[17] = new GeographicAddress("2011-08-21","2013-02-12", "2908 Lake St",
                "Montreal", "Quebec","H9B 2K9",CANADA);


        // Testing for equality
        System.out.println("Address[0] is equal to address[1] is a " + addresses[0].equals(addresses[1]) + " statement.");
        System.out.println("Address[3] is equal to address[6] is a " + addresses[3].equals(addresses[6]) + " statement.");
        System.out.println("Address[14] is equal to address[15] is a " + addresses[14].equals(addresses[15]) + " statement.");
        System.out.println("Address[16] is equal to address[17] is a " + addresses[16].equals(addresses[17]) + " statement.");

        // Printing all of the addresses information
        System.out.println("");
        for (int i =0; i<addresses.length;i++){
            System.out.println("#" + (i+1) + " " + addresses[i]);
        }
        System.out.println("");
        System.out.println("Using 2021-03-07 as the passed date, let's see which addresses are obsolete: ");
        traceObsoleteAddresses(addresses,2021,03,07);
        
        System.out.println("All done!");

    }

    /**
     * Traces obsolete addresses compared to the passed date
     * @param addresses array of addresses to be traced.
     * @param YYYY Year of the passed date
     * @param MM Month of the passed date
     * @param DD Day of the passed date
     */
    public static void traceObsoleteAddresses(Address[] addresses, int YYYY, int MM, int DD){
        int[] passedDate = {YYYY,MM,DD};
        boolean valid;
        for(int i = 0; i < addresses.length; i++){
            valid = false;
            int[][] intDates = addresses[i].getIntegerDates();
            // If the passed date is earlier than or equal to the validTo and is later or equal to the validFrom, then
            // it is valid.
            if (lessThanOrEqual(intDates[0],passedDate) && lessThanOrEqual(passedDate,intDates[1]))
                valid=true;

            if (!valid)
                System.out.println("#" + (i+1) + " " + addresses[i] + "This address is the element number " + (i+1) + " in the array and is " +
                        "considered obsolete when using the date " + YYYY + "-" + MM + "-" + DD + ".");
        }
    }

    /**
     * Checks whether the first date is earlier or equal to the second date. The parameters are in form of
     * {YYYY,MM,DD}
     * @param first first date in integer that is meant to be compared
     * @param second second date in integer that is meant to be compared
     * @return true if the first date is earlier or the same as the second date, false otherwise
     */
    public static boolean lessThanOrEqual(int[] first, int[] second){
        // if the year of the first date is less than the one of the second return true
        if (first[0]<second[0])
            return true;
        // if the year is the same for both dates check if the month of the first one is earlier than the second one
        if (first[0]==second[0])
            if (first[1]<second[1])
                return true;
            // If the month is equal check if the day is smaller or equal
            if (first[1]==second[1])
                if (first[2] <= second[2])
                    return true;
        return false;
    }



}

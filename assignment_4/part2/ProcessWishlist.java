package part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
// -----------------------------------------------------
// Part: 2
// Written by: Michael Jr Osuji 40182642
// -----------------------------------------------------
/**
 *  This class is the main class that processes the wish list. It processes the TVGuide.txt file and, depending on the show objects it contains,
 *  determines wheter the wishlisht from the interest file is watchable or not. It also prompts the user for showIds and prints how many times
 *  the find() method of ShowList iterated.  Finally, it tests the remaining unused methods of all the other classes.
 */
public class ProcessWishlist {
    public static void main(String[] args) {
        ShowList guideList = new ShowList();

        File guideTXT = new File("TVGuide.txt");

        try{
            System.out.println("Reading " + guideTXT.getName());
            Scanner guideReader = new Scanner(new FileInputStream(guideTXT));
            while (guideReader.hasNextLine()){
                String nextLine = guideReader.nextLine();
                if (!nextLine.equalsIgnoreCase("")) {
                    String[] firstLine = nextLine.split(" ");
                    String showID = firstLine[0];
                    if (guideList.contains(showID)) {
                        System.out.println("duplicate");
                        continue;
                    }
                    String showName = firstLine[1];
                    double startTime = Double.parseDouble(guideReader.nextLine().substring(2));
                    double endTime = Double.parseDouble(guideReader.nextLine().substring(2));
                    guideList.addToStart(new Show(showID,showName,startTime,endTime));
                }
            }
            guideReader.close();


            askAndProcessInputFiles(guideList);





            //(d) Finding 3 showIDs
            Scanner keyInput = new Scanner(System.in);
            System.out.println("\nNow, you will need to enter 3 show IDs you want to find in the TVGuide.txt.");
            String[] showIDS = new String[3];
            for(int i =0; i<3; i++){
                System.out.print("Please enter the showID number " + (i+1) + " you want to find: ");
                showIDS[i] = keyInput.next();
            }
            for (int i =0; i<showIDS.length;i++){
                System.out.print("Finding show " + showIDS[i] + ": ");
                guideList.find(showIDS[i]);

            }
            keyInput.close();






            //(e) testing methods and exiting the program
            System.out.println("\nThe testing starts! \n");


            Show NBC20 = new Show("NBC20","World_Of_Dance",20.00,22.00);
            Show PBS21 = new Show("PBS21","Wonders_Of_Mexico",21.00,22.00);
            Show FOX21 = new Show(PBS21,"FOX21");
            FOX21.setShowName("Gordon_Ramsay's_24_Hours_To_Hell_And_Back");
            Show NBC21 = new Show(PBS21,"NBC21");
            NBC21.setStartTime(20.00);
            Show ABC21 = new Show(PBS21,"ABC21");
            Show ABC21Copy = new Show(ABC21,ABC21.getShowID());
            ABC21.setEndTime(21.30);
            System.out.println("ABC21 print is \"" + ABC21 + "\" versus ABC21Copy which is \"" + ABC21Copy + "\".");
            System.out.println("The statement \"ABC21 is equal to ABC21Copy\" is " + ABC21.equals(ABC21Copy));

            System.out.println("\nI set the end time of ABC21Copy to 21.30");
            ABC21Copy.setEndTime(21.30);
            System.out.println("Now, the statement \"ABC21 is equal to ABC21Copy\" is " + ABC21.equals(ABC21Copy));

            System.out.println("\nI set the showID of ABC21Copy to ABC21Copy");
            ABC21Copy.setShowID("ABC21Copy");
            System.out.println("Now, the statement \"ABC21 is equal to ABC21Copy\" is " + ABC21.equals(ABC21Copy));






            ShowList testing = new ShowList();
            testing.addToStart(NBC20);
            testing.addToStart(PBS21);
            System.out.println("\nThe statement \"testing is equal to guidelist\" is " + testing.equals(guideList));

            System.out.println("\nPrinting the contents of guidelist");
            guideList.printList();

            System.out.println("\nPrinting the contents of testing");
            testing.printList();

            testing.insertAtIndex(FOX21,2);
            System.out.println("\nInserted FOX21 at index 2 of testing");
            System.out.println("\nPrinting the contents of testing");
            testing.printList();

            guideList.replaceAtIndex(NBC20,4);
            System.out.println("\nReplaced show at index 4 of guideList with NBC20");
            System.out.println("\nPrinting the contents of guidelist");
            guideList.printList();

            guideList.deleteFromIndex(8);
            System.out.println("\nDeleted index 8 of guidelist");
            System.out.println("\nPrinting the contents of guidelist");
            guideList.printList();

            guideList.deleteFromStart();
            System.out.println("\nDeleted head of guidelist");
            System.out.println("\nPrinting the contents of guidelist");
            guideList.printList();

            guideList.cloneAtIndex(6);
            System.out.println("\nCloned the node at index 6 of guidelist");
            System.out.println("\nPrinting the contents of guidelist");
            guideList.printList();
            
            Show NBC21Clone = (Show) NBC21.clone("NBC21");
            System.out.println("\nCloned the show NBC21");
            System.out.println("\nPrinting the NBC21 clone");
            System.out.println(NBC21Clone);;

            System.out.println("\nAll done! Thanks for using this program!");
            System.exit(0);



        } catch (FileNotFoundException e) {
            // update
            System.err.println("TVGuide.txt could not be found, shutting down the program.");
            System.exit(0);
        }
        catch (CloneNotSupportedException e){
            System.exit(0);
        }

    }

    /**
     * Prompts the user for input files (of type interest.txt) and processes them to see wheter the wishlist is watchable
     * or not.
     * @param guideList The ShowList object created from TVGuide.txt
     */
    public static void askAndProcessInputFiles(ShowList guideList){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Hello, please enter the amount of Interest files you would like to process: ");
        int fileNb = keyboard.nextInt();
        String[] arrayFileNames = new String[fileNb];
        for(int i = 0; i < arrayFileNames.length; i++){
            System.out.print("Please enter the name of file input " + (i+1) + ": ");
            arrayFileNames[i] = keyboard.next();
        }

        for (int i = 0; i<arrayFileNames.length; i++){
            try {
                Scanner interestReader = new Scanner(new FileInputStream(new File(arrayFileNames[i])));
                ArrayList<String> watchingArray = new ArrayList<>();
                ArrayList<String> wishlistArray = new ArrayList<>();
                boolean watch = true;
                // skipping the watching line
                interestReader.nextLine();

                while (interestReader.hasNextLine()) {
                    String input = interestReader.nextLine();
                    if (input.equalsIgnoreCase("")) {

                        break;
                    } else if (input.equalsIgnoreCase("wishlist")) {
                        watch = false;
                    } else if (watch) {
                        System.out.println("Adding " + input+ " to watchlist arraylist");
                        watchingArray.add(input);
                    } else {
                        System.out.println("Adding " + input+ " to wishlist arraylist");
                        wishlistArray.add(input);
                    }
                }
                System.out.println();

                ShowList wishlist = new ShowList();
                ShowList watchlist = new ShowList();

                for (int j = 0; j<watchingArray.size(); j++){
                    String showID = watchingArray.get(j);
                    if (guideList.contains(showID)){
                        System.out.print("To find " + showID + ", the ");
                        Show contained = guideList.getShow(showID);
                        watchlist.addToStart(contained);
                    }
                    else {
                        System.err.println("No show with showID " + showID + " in TVGuide file.");
                    }
                }
                //maybe method it out
                for (int j = 0; j<wishlistArray.size(); j++){
                    String showID = wishlistArray.get(j);
                    if (guideList.contains(showID)){
                        System.out.print("To find " + showID + ", the ");
                        Show contained = guideList.getShow(showID);
                        wishlist.addToStart(contained);
                    }
                    else {
                        System.err.println("No show with showID " + showID + " in TVGuide file.");
                    }
                }
                System.out.println();
                System.out.println("Printing the outcome as to whether the user will be able to watch the shows or not.");
                watchlist.printWatchable(wishlist);

                interestReader.close();



            } catch (FileNotFoundException e) {
                System.err.println("Couldn't process " + arrayFileNames[i]);
            }
        }

    }
}

package part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
// -----------------------------------------------------
// Part: 1
// Written by: Michael Junior Osuji
// -----------------------------------------------------
/**
 * Parses the files and outputs the requirements.
 */
public class FileParser {
    public static void main(String[] args) {
        // vowels and initializes scanner.
        final char[] vowels = {'a','e','i','o','u'};
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the name of a file you want to parse: ");
        File inputFile = new File(userInput.nextLine());
        Scanner read = null;
        try{




            read = new Scanner(new FileInputStream(inputFile));

            ArrayList<String> vowelList = new ArrayList<>();
            ArrayList<String> oList = new ArrayList<>();
            ArrayList<String> distinctList = new ArrayList<>();

            while(read.hasNext()){
                String next = read.next().replaceAll("[^a-zA-Z0-9]", "");
                String lower = next.toLowerCase();
                if (!lower.equals("") && lower.charAt(0)=='o'){
                    oList.add(next);
                }
                int vowelCount = 0;
                for (int i = 0; i<lower.length(); i++){
                    for(int j =0; j<vowels.length;j++){
                        if (lower.charAt(i)==vowels[j]){
                            vowelCount++;
                            break;
                        }
                    }
                    if (vowelCount==4) break;
                }
                if (vowelCount==4){
                    vowelList.add(next);
                }
                //their distinct file is bad since these and These are distinct words.
                // just change .equals to .equalsIgnoreCase for a better version upon request
                boolean add = true;
                for (int i = 0; i < distinctList.size(); i++){
                    if (next.equals(distinctList.get(i))){
                        add = false;
                    }
                }
                if (add){
                    distinctList.add(next);
                }
            }
            read.close();

            printList(vowelList,"vowel_verbiage.txt");
            printList(oList,"obsessive_o.txt");
            printList(distinctList,"distinct_data.txt");





        } catch (FileNotFoundException e) {
            System.err.println("The file " + inputFile.getName() + " cannot be found, shutting down the program.");
            System.exit(0);
        }

        System.out.println("All done!");

    }

    /**
     * Prints the arraylist content in the file given. Also prints the word count at the start.
     * @param list Arraylist with the desired output
     * @param fileName The name of the File
     */
    public static void printList(ArrayList<String> list, String fileName){
        File file = new File(fileName);
        try {
            PrintWriter write = new PrintWriter(new FileOutputStream(file));

            write.println("Word Count: " + list.size());
            for (int i = 0; i < list.size(); i++) {
                write.println(list.get(i));
            }
            write.close();
        }catch (Exception e){
            System.err.println("The file " + fileName + " cannot be written to or found.");
        }
    }
}

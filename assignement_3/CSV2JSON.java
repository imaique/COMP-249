package a3;

import java.io.*;
import java.util.Scanner;

// ---------------------------------
// Part: 1
// Written by: Michael Junior Osuji 40182642
// ---------------------------------
/**
 * Driver class. Welcomes the user, prompts for files to be converted to json, converts these files and asks to display one of those converted files.
 * @author Michael
 */
public class CSV2JSON {
    private static PrintWriter log;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // Creating the log file
        File logFile = new File("log.txt");
        try {
             log = new PrintWriter(new FileOutputStream(logFile));
        } catch (FileNotFoundException e) {
            System.err.println("Could not create log file");
            System.err.println("Terminating the program");
            System.err.println("----------------------------------------------------");
            System.exit(0);
        }

        System.out.println("Hello! Welcome to this program that converts your excel sheets in csv format to JSON format.");
        // Change to promptFiles()
        File[] inputFiles = promptFiles();

        // Creating a scanner array
        Scanner[] scanners =  new Scanner[inputFiles.length];

        // Opening all of the files using scanner
        for (int i = 0; i < scanners.length; i++){
            try {
                scanners[i] = new Scanner(new FileInputStream(inputFiles[i]));
            }catch (FileNotFoundException e){
                System.err.println("Could not open input file " + inputFiles[i].getName() + " for reading. Please check " +
                        "if file exists! Program will terminate after closing any opened files.");
                System.err.println("----------------------------------------------------");

                closeScanners(scanners);

                System.exit(0);
            }
        }
        // Creating outputFiles array
        File[] outputFiles = new File[inputFiles.length];
        for (int i = 0; i < outputFiles.length; i++){
            String fullInputFileName = inputFiles[i].getName();
            outputFiles[i] = new File(fullInputFileName.substring(0,fullInputFileName.length()-4) + ".json");
        }

        // Creating a PrintWriter array
        PrintWriter[] printWriters = new PrintWriter[inputFiles.length];

        // Creating/opening the output files
        for (int i = 0; i< printWriters.length; i++){
            try{
                printWriters[i] = new PrintWriter(new FileOutputStream(outputFiles[i].getName()));
            } catch (FileNotFoundException e) {
                System.err.println("Could not open/create " + outputFiles[i].getName());

                System.err.println("----------------------------------------------------");
                deleteFiles(outputFiles);
                closeScanners(scanners);
                System.exit(0);
            }
        }
        processFilesForValidation(inputFiles,scanners,printWriters,outputFiles);
        //PrintWriter

        promptFileForDisplay();

        log.close();
        System.out.println("All done!");
    }

    /**
     * Prompts the user for the output file he wants to display.
     */
    public static void promptFileForDisplay(){
        System.out.print("Please enter a output file that you want to display: ");

        File displayFile = null;

        try{

            displayFile = testFile();

            read(displayFile);

        } catch (FileNotFoundException e) {
            System.out.print("This file does not exist, this is your final chance. Please enter a output file that you want to display: ");
            try {
                displayFile = testFile();
                read(displayFile);

            }catch (FileNotFoundException b){
                System.out.print("You failed to enter a valid name. The program will now exit.");
                log.close();
                System.exit(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Reads the file the user requested to be displayed
     * @param displayFile file to be displayed
     * @throws FileNotFoundException if the File is not found
     * @throws IOException if the file cannot be read from
     */
    public static void read(File displayFile) throws FileNotFoundException,IOException {
        BufferedReader reader = new BufferedReader(new FileReader(displayFile));

        String line = reader.readLine();
        while (line!=null){
            System.out.println(line);
            line = reader.readLine();
        }
    }

    /**
     * Tests whether the output file the user demanded for exists
     * @return The corresponding file
     * @throws FileNotFoundException if the file does not exist
     */
    public static File testFile() throws FileNotFoundException{
        String fileName = input.nextLine();
        File displayFile = new File(fileName);
        if (!displayFile.exists()) throw new FileNotFoundException();
        return displayFile;
    }


    /**
     * Processes file for validation and converts them if valid.
     * @param inputFiles the array of files to be converted
     * @param scanners the array of scanners that corresponds to the files
     * @param printWriters the array of printwriters that correspond to the files
     * @param outputFiles the array of output files that are the converted csv to json files
     */
    public static void processFilesForValidation(File[] inputFiles, Scanner[] scanners, PrintWriter[] printWriters, File[] outputFiles){
        for(int i =0; i<inputFiles.length;i++){
            String stringAttributes = scanners[i].nextLine();


            int missingAttributes = 0;
            String[] attributes = stringAttributes.split(",");

            for(int j = 0; j < attributes.length; j++){
                if (attributes[j].equals("")){
                    attributes[j] = "***";
                    missingAttributes++;
                }
            }

            try {

                if (missingAttributes!=0) throw new CVSFileInvalidException(inputFiles[i].getName(),"attribute(s) is/are missing");
                createJSONFile(attributes,scanners[i],printWriters[i],inputFiles[i].getName());

            }catch (CVSFileInvalidException e) {

                scanners[i].close();
                printWriters[i].close();
                outputFiles[i].delete();

                for(int k =0; k< attributes.length;k++){
                    log.print(attributes[k]+"\t");
                }
                log.println();
                log.println("File " + inputFiles[i].getName() + " is missing attributes");
                log.println("Missing " + missingAttributes + " attribute(s). Expected number of attributes: " + attributes.length + ".");
                log.println("-------------------------");


            }
        }

    }

    /**
     * creates a json file
     * @param attributes attributes of that file
     * @param scanner scanner of that file
     * @param printWriter printwriter of that file
     * @param fileName name of the file
     */
    public static void createJSONFile(String[] attributes,Scanner scanner, PrintWriter printWriter, String fileName)  {
        int lineNb = 1;
        printWriter.println("[");

        while (scanner.hasNextLine()) {
            String dataLine = scanner.nextLine();
            Tokenizer splitData = new Tokenizer(dataLine,',');

            String[] data = new String[attributes.length];
            for (int i =0; i<data.length;i++){

                data[i] = splitData.next();
            }


            boolean isThrow = false;
            for (int i = 0; i < attributes.length; i++) {

                if (data[i].equals("")) {
                    data[i] = "***";
                    isThrow = true;
                }
            }
            try {
                if (isThrow) throw new CSVDataMissing(fileName,lineNb, "missing fields");
                printWriter.println("\t{");
                for (int j = 0; j < attributes.length; j++) {
                    printWriter.print("\t\t\"" + attributes[j] + "\": ");
                    try{
                        Integer.parseInt(data[j]);
                        printWriter.print(data[j]);
                    }catch (Exception e){
                        printWriter.print("\"" + data[j] + "\"");
                    }


                    printWriter.println((j == attributes.length - 1 ? "" : ","));
                }
                printWriter.println("\t}" + (scanner.hasNextLine() ? "," : ""));

            }catch (CSVDataMissing e) {

                log.println("In file " + fileName+ " line " + lineNb);
                int missingFields = 0;
                String missing = "Missing: ";
                for (int k =0; k < data.length; k++){
                    log.print(data[k]+"\t");
                    if (data[k]=="***"){
                        missing+= attributes[k] + ", ";
                        missingFields++;
                    }
                }
                log.println();
                missing= missing.substring(0,missing.length()-2) + ".";
                log.println(missing);
                log.println("Expected " + attributes.length + " fields but " + missingFields + " are missing.");
                log.println("-------------------------");

            }

            lineNb++;
        }
        printWriter.println("]");
        scanner.close();
        printWriter.close();
    }

    /**
     * delete all of the created files
     * @param files array of files
     */
    public static void deleteFiles(File[] files){
        for (int i = 0; i < files.length; i++){
            if (!files[i].exists())
                break;
            files[i].delete();
        }
    }

    /**
     * closes all of the existing scanners
     * @param scanners array of scanners
     */
    public static void closeScanners(Scanner[] scanners){
        for (int i = 0; i < scanners.length; i++){
            if (scanners[i]==null)
                break;
            scanners[i].close();
        }
    }

    /**
     * Prompts the user for the files he wants to convert
     * @return the array of files
     */
    public static File[] promptFiles(){
        Scanner userInput = new Scanner(System.in);
        int fileNb = 0;
        do{
            System.out.println("Please enter the number of files you want to convert (Minimum 2):");
            try{
                String input = userInput.nextLine();
                fileNb = Integer.parseInt(input);
                if (fileNb<2) throw new Exception();
            }catch (Exception e){
                System.out.println("Sorry, that is an incorrect amount of files.");
            }

        } while(fileNb<2);
        File[] files = new File[fileNb];

        for (int i = 0; i<files.length; i++){
            System.out.println("Please enter the name of the file number " + (i+1) + " (Do not include the .csv) :");
            files[i]= new File(userInput.nextLine()+".csv");
        }

        return files;
    }
}

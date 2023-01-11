import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
/**
* Purpose: Main class creates a Linked List of type Baby, from the Baby class. Reads through
* given csv files, stores data as type Baby. Given the year(s) inputted by user on
* the command line, program searches a baby name and returns the frequency of that name, the
* number of uses of the name, and the position of the name in the sorted linked list.
*
* @author ashultz
*/
public class Main {
    // Create the Linked List that the data will be stored in, of type Baby
    SingleLinkedList<Baby> infoList = new SingleLinkedList<Baby>();
 
    /**
     * Reads the given csv file(s) and stores the data as type Baby into the LL
     * @param txt the csv file(s)
     */
    public void readFile(String csv) {
        ReadCSV read;
     
        // Try-catch with given csv file(s)
        try {
            read = new ReadCSV(csv);
        }
        catch (IOException ioe) {
            System.err.println(ioe);
            return;
        }
        // Use iterator to read the file(s)
        try {
            Iterator<String[]> iitt = read.iterator();
            // While their is content to read in the file
            while(iitt.hasNext()) {
             
                // Create a temporary array called to store the information
                String[] readLine = iitt.next();
                // Create an object of type Baby to hold the data info: name, frequency, position
                Baby name = new Baby(readLine[0], Integer.parseInt(readLine[1]), 1);
                // if the LL has the name in its list already
                if (infoList.contains(name)) {
                    // find the name in the LL
                    Baby bb = infoList.find(name);
                    // remove that name from the list
                    infoList.remove(bb);
                    // update the number of times that name came up in the LL
                    bb.updateFreq(name.getFrequency());
                    // update the number of uses of that name
                    bb.updateUses();
                    // add the name back to the list in sorted order, the LL will remain sorted
                    infoList.addSorted(bb);
                }
                else {
                    // if the name was not in the list previously, add it to the list
                    infoList.addSorted(name);
                }
            }   
        }
        catch (Exception exc) {
            System.err.println("There is an error when reading through the file(s)");
            return;
        }
    }
    /**
     * Main method reads the csv file(s), returns the freq, uses, and position of a
     * given name based on the inputted years from the user in the command line
     * @param args
     */
    public static void main(String[] args) {
        // create an object from the constructor Main
        Main sll = new Main();
        // allows user to input multiple years in the command line to search
        // this allows program to read through multiple csv files
        for(int i = 0; i < args.length; i++) {
            sll.readFile("https://cs.brynmawr.edu/cs151/Data/Hw9/NN" + args[i] + ".csv"); //sample file that can access files between years 1990-2020
        }
        System.out.println("The spelling of any inputted name is not case sensitive");
        // Use scnr to get user input of baby name to search for from the command line
        Scanner scnr = new Scanner(System.in);
        // After reading the file(s) tells user to input a baby name
        // user can quit inputting a baby name by typing q to quit the while loop
        // otherwise, the loop will keep asking for another baby name to search
        while (true) {
            System.out.print("Name (q to quit): ");
            String bbName = scnr.next();
            // If user presses q, break the loop
            if (bbName.equals("q")) {
                System.out.println("Goodbye");
                System.exit(0);
            }
            // Creates an object of type Baby to store the given baby name and
            // temp int values in the other two spots to start since their values don't
            // matter at this time when trying to find the name in the LL infoList
            Baby nameInput = new Baby(bbName, -1, -1); // Both integers not using them, hence -1
            // find the name in the LL
            Baby findName = sll.infoList.find(nameInput);
            // if the name is found in the LL
            if(findName!=null){
                // converts inputted name to all lowercase and prints out this version of name
                System.out.println(nameInput.name.toLowerCase());
                // get the position of the name and print it out
                System.out.println("Pos: " + sll.infoList.findPosition(findName));
                // print out the name searched
                System.out.println(findName);
            }
            // otherwise not found so print out name not found
            else {
                System.out.println("The name was not found in our file(s). Try a different name or spelling.");
            }
        }    
    }
}

package edu.ncsu.csc316.trail.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.trail.dsa.DataStructure;
import edu.ncsu.csc316.trail.manager.ReportManager;

/**
 * The UI class for the TrailManager, deals with the UI and printing out whatever is commanded
 */
public class TrailManagerUI {

    /**
     * main method that starts the program
     * @param args the arguments given
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String landmarkFile;
        String trailFile;
        
        // checking for the input given by user 
        if (args.length >= 2) {
            landmarkFile = args[0];
            trailFile = args[1];
        } else {
            System.out.println("Enter the path to the landmark file:");
            landmarkFile = scanner.nextLine();
            printFileContents(landmarkFile);
            
            System.out.println("Enter the path to the trail file:");
            trailFile = scanner.nextLine();
            printFileContents(trailFile);
        }
        
        // Initialize the ReportManager 
        ReportManager reportManager = null;
        try {
        	long start = System.currentTimeMillis(); 
            reportManager = new ReportManager(landmarkFile, trailFile, DataStructure.LINEARPROBINGHASHMAP);
            reportManager.getDistancesReport("L0000001");
            long end = System.currentTimeMillis(); 
            long duration = end - start; 
            System.out.println(duration); 
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error initializing TrailManager: " + e.getMessage());
            scanner.close();
            return;
        }
        
        // Main menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- TrailManager Menu ---");
            System.out.println("1. View Distances to All Reachable Landmarks");
            System.out.println("2. List Potential Locations for First Aid Stations");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
            
            String input = scanner.nextLine();
            int choice = 0;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }
            
            switch(choice) {
                case 1:
                    System.out.print("Enter the starting landmark ID: ");
                    String id = scanner.nextLine();
                    String distancesReport = reportManager.getDistancesReport(id);
                    System.out.println("\n" + distancesReport);
                    break;
                case 2:
                    System.out.print("Enter the minimum number of intersecting trails: ");
                    String numStr = scanner.nextLine();
                    int minTrails = 0;
                    try {
                        minTrails = Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a positive integer.");
                        break;
                    }
                    String firstAidReport = reportManager.getProposedFirstAidLocations(minTrails);
                    System.out.println("\n" + firstAidReport);
                    break;
                case 3:
                    System.out.println("Exiting TrailManager. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 3.");
                    break;
            }
        }
        
        scanner.close();
    }

    /**
     * Reads and prints the contents of a file
     * @param filePath the path of the file to read
     */
    public static void printFileContents(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: The file does not exist.");
            return;
        }

        // start of printing 
        System.out.println("\n--- Displaying File: " + filePath + " ---");
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to read the file.");
        }
        System.out.println("--- End of File ---\n");
    }
}

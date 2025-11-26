import java.io.File;
import java.util.Scanner;

/**
 * DirectoryAnalyzer - Analyzes directories and counts Java files
 *
 * @author YEOH CHONG CHAO
 * @matric 301334
 * @course STIWK3014 Real-Time Programming
 */
public class DirectoryAnalyzer {

    private Scanner scanner;
    private FileCounter fileCounter;

    // Constructor
    public DirectoryAnalyzer() {
        this.scanner = new Scanner(System.in);
        this.fileCounter = new FileCounter();
    }

    // Main method to run the program
    public void run() {
        System.out.println("===========================================");
        System.out.println("   Directory Analysis System");
        System.out.println("===========================================");
        System.out.println();

        // Get directory path from user
        String directoryPath = promptForDirectory();
        File directory = new File(directoryPath);

        // Validate and analyze
        if (validateDirectory(directory)) {
            analyzeDirectory(directory);
        }

        scanner.close();
    }

    // Prompt user to enter directory path
    private String promptForDirectory() {
        System.out.print("Please enter the directory path to analyze: ");
        return scanner.nextLine().trim();
    }

    // Validate if directory exists and is accessible
    private boolean validateDirectory(File directory) {
        try {
            if (!directory.exists()) {
                System.err.println("Error: The specified directory does not exist.");
                return false;
            }

            if (!directory.isDirectory()) {
                System.err.println("Error: The specified path is not a directory.");
                return false;
            }

            if (!directory.canRead()) {
                System.err.println("Error: No read permission for this directory.");
                return false;
            }

            return true;

        } catch (SecurityException e) {
            System.err.println("Error: Security exception - " + e.getMessage());
            return false;
        }
    }

    // Analyze the directory
    private void analyzeDirectory(File directory) {
        System.out.println("\nAnalyzing directory: " + directory.getAbsolutePath());
        System.out.println("-------------------------------------------");

        try {
            fileCounter.analyzeDirectory(directory);
            displayResults();
        } catch (Exception e) {
            System.err.println("Error during analysis: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Display the results
    private void displayResults() {
        System.out.println("\n===========================================");
        System.out.println("   Analysis Results");
        System.out.println("===========================================");
        System.out.println("Number of Java Files = " + fileCounter.getJavaFileCount());
        System.out.println("Number of Issues = " + fileCounter.getIssueCount());
        System.out.println("===========================================");
    }

    // Main entry point
    public static void main(String[] args) {
        DirectoryAnalyzer analyzer = new DirectoryAnalyzer();
        analyzer.run();
    }
}
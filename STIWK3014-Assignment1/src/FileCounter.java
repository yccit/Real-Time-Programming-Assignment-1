import java.io.File;

/**
 * FileCounter - Handles the counting logic for Java files and issues
 *
 * This class provides functionality to:
 * - Count .java files in a directory
 * - Count files representing solved issues
 * - Recursively search through subdirectories
 *
 * @author YEOH CHONG CHAO
 * @matric 301334
 */
public class FileCounter {

    private int javaFileCount;
    private int issueCount;

    /**
     * Constructor - It will Initializes counters to zero
     */
    public FileCounter() {
        this.javaFileCount = 0;
        this.issueCount = 0;
    }

    /**
     * Analyzes a directory and counts Java files and issues
     * @param directory The directory to analyze
     */
    public void analyzeDirectory(File directory) {
        // Reset counters before analysis
        resetCounters();

        // Start recursive analysis
        countFilesRecursively(directory);
    }

    /**
     * Recursively counts Java files and issues in directory and subdirectories
     * @param directory The current directory to process
     */
    private void countFilesRecursively(File directory) {
        File[] files = directory.listFiles();

        if (files == null) {
            return; // No files or cannot access directory
        }

        for (File file : files) {
            if (file.isDirectory()) {
                // Recursively process subdirectories
                countFilesRecursively(file);
            } else if (file.isFile()) {
                // Process individual files
                processFile(file);
            }
        }
    }

    /**
     * Processes an individual file and updates counters
     * @param file The file to process
     */
    private void processFile(File file) {
        String fileName = file.getName();

        // Check if it's a Java file
        if (isJavaFile(fileName)) {
            javaFileCount++;

            // Check if it's also an issue file
            if (isIssueFile(fileName)) {
                issueCount++;
            }
        }
    }

    /**
     * Checks if a file is a Java file
     * @param fileName The name of the file
     * @return true if it's a .java file, false otherwise
     */
    private boolean isJavaFile(String fileName) {
        return fileName.toLowerCase().endsWith(".java");
    }

    /**
     * Checks if a file represents a solved issue
     * Common patterns for issue files:
     * - Files containing "Issue" in the name
     * - Files containing "Problem" in the name
     * - Files containing "Solution" in the name
     * - Files matching pattern like "Issue1.java", "Problem2.java"
     *
     * @param fileName The name of the file
     * @return true if it represents an issue, false otherwise
     */
    private boolean isIssueFile(String fileName) {
        String lowerCaseFileName = fileName.toLowerCase();

        // Check for common issue/problem naming patterns
        return lowerCaseFileName.contains("issue") ||
                lowerCaseFileName.contains("problem") ||
                lowerCaseFileName.contains("solution") ||
                lowerCaseFileName.contains("exercise") ||
                lowerCaseFileName.contains("question");
    }

    /**
     * Resets all counters to zero
     */
    private void resetCounters() {
        this.javaFileCount = 0;
        this.issueCount = 0;
    }

    /**
     * Gets the count of Java files found
     * @return The number of Java files
     */
    public int getJavaFileCount() {
        return javaFileCount;
    }

    /**
     * Gets the count of issues found
     * @return The number of issues
     */
    public int getIssueCount() {
        return issueCount;
    }
}

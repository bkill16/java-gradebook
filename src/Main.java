import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Create a scanner object for user input
        Scanner sc = new Scanner(System.in);

        // Initialize the grade book as a list of strings
        List<String> gradeBook = new ArrayList<>();

        // Main loop for the grade book application
        while (true) {
            // Display the menu options to the user
            System.out.println("\nPlease Select an Option from the Menu");
            System.out.println(" 1. Add Grades");
            System.out.println(" 2. View All Grades");
            System.out.println(" 3. View Student's Total Grade");
            System.out.println(" 4. Save Grades");
            System.out.println(" 5. Load Grades");
            System.out.println(" 6. Quit");
            System.out.print("Enter choice: ");
            int menuChoice = sc.nextInt(); // Get user choice
            sc.nextLine(); // Consume the leftover newline character

            switch (menuChoice) {
                case 1:
                    // Add a new grade entry
                    System.out.print("\nStudent name: ");
                    String studentName = sc.nextLine();
                    System.out.print("Assignment name: ");
                    String assignmentName = sc.nextLine();
                    System.out.print("Grade: ");
                    double assignmentGrade = sc.nextDouble();
                    sc.nextLine(); // Consume the leftover newline character

                    // Create the grade entry in CSV format
                    String gradeEntry = studentName + "," + assignmentName + "," + assignmentGrade;
                    gradeBook.add(gradeEntry); // Add entry to the grade book
                    System.out.println("Entry has been added to the grade book.");
                    break;
                case 2:
                    // View all grades in the grade book
                    if (gradeBook.isEmpty()) {
                        System.out.println("\nNo grades to display.");
                    } else {
                        System.out.println("\nAll Grades:");
                        // Display each grade in the grade book
                        for (String entry : gradeBook) {
                            String[] parts = entry.split(",");
                            System.out.println(parts[0] + " - " + parts[1] + " - " + parts[2]);
                        }
                    }
                    break;
                case 3:
                    // View a student's total grade and calculate average with letter grade
                    if (gradeBook.isEmpty()) {
                        System.out.println("\nNo grades to display.");
                    } else {
                        System.out.print("\nStudent name: ");
                        String studentNameForTotal = sc.nextLine();
                        double totalGrade = 0; // Sum of grades
                        int count = 0; // Number of grades

                        // Calculate total grade for the student
                        for (String entry : gradeBook) {
                            String[] parts = entry.split(",");
                            if (parts[0].equalsIgnoreCase(studentNameForTotal)) {
                                totalGrade += Double.parseDouble(parts[2]);
                                count++;
                            }
                        }

                        if (count > 0) {
                            // Calculate average grade
                            double grade = totalGrade / count;
                            String letter = ""; // Letter grade
                            String sign = "";   // + or - sign for the grade

                            // Determine the letter grade
                            if (grade > 89) {
                                letter = "A";
                            } else if (grade > 79) {
                                letter = "B";
                            } else if (grade > 69) {
                                letter = "C";
                            } else if (grade > 59) {
                                letter = "D";
                            } else {
                                letter = "F";
                            }

                            // Determine the + or - sign
                            if (grade > 100) {
                                sign = "+";
                            } else if (grade < 93 && grade >= 90) {
                                sign = "-";
                            } else if (grade < 90 && grade >= 86) {
                                sign = "+";
                            } else if (grade < 83 && grade >= 80) {
                                sign = "-";
                            } else if (grade < 80 && grade >= 76) {
                                sign = "+";
                            } else if (grade < 73 && grade >= 70) {
                                sign = "-";
                            } else if (grade < 70 && grade >= 66) {
                                sign = "+";
                            } else if (grade < 63 && grade >= 60) {
                                sign = "-";
                            }

                            // Display the average grade and letter grade
                            System.out.println(studentNameForTotal + ": " + grade + " (" + letter + sign + ")");
                        } else {
                            System.out.println("No grades found for " + studentNameForTotal);
                        }
                    }
                    break;
                case 4:
                    // Save the grade book to a file
                    System.out.print("\nFile name: ");
                    String fileName = sc.nextLine();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                        for (String entry : gradeBook) {
                            writer.write(entry);
                            writer.newLine(); // Add a newline after each entry
                        }
                        System.out.println("Grade book saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving to file: " + e.getMessage());
                    }
                    break;
                case 5:
                    // Load grades from a file into the grade book
                    System.out.print("\nFile name: ");
                    String loadFile = sc.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))) {
                        gradeBook.clear(); // Clear the current grade book
                        String line;
                        while ((line = reader.readLine()) != null) {
                            gradeBook.add(line); // Add each line to the grade book
                        }
                        System.out.println(loadFile + " loaded successfully.");
                    } catch (IOException e) {
                        System.out.println("Error loading file: " + e.getMessage());
                    }
                    break;
                case 6:
                    // Exit the program
                    System.out.println("Exiting grade book. Goodbye.");
                    return;
                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

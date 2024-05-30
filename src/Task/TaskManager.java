/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TaskManager {
    private List<Task> tasks;
    private List<String> messages; // To store messages for unit testing

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void runApplication(Scanner scanner) {
        System.out.println("Welcome to EasyKanban");
//I took this code from Stack Overflow
//https://stackoverflow.com/questions/22512830/what-condition-does-whiletrue-test-when-is-it-true-and-false
//Gregg1989
//https://stackoverflow.com/users/2915567/gregg1989

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1) Add tasks");
            System.out.println("2) Show report");
            System.out.println("3) Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTasks(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    System.out.println("Total hours of all tasks: " + Task.returnTotalHours());
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addTasks(Scanner scanner) {
        System.out.println("How many tasks would you like to enter?");
        int numberOfTasks = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println("Enter task name:");
            String taskName = scanner.nextLine();
            String taskDescription = "";
            while (true) {
                System.out.println("Enter task description (max 50 characters):");
                taskDescription = scanner.nextLine();

                if (taskDescription.length() <= 50) {
                    System.out.println("Task successfully captured");
                    break;// Exit the loop if description is valid
                } else {
                    System.out.println("Please enter a task description of less than 50 characters");
                    // Continue the loop if description is too long
                }
            }
            
            System.out.println("Enter developer details (First and Last Name):");
            String developerDetails = scanner.nextLine();

            System.out.println("Enter task duration (in hours):");
            int taskDuration = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Select task status: (1. ToDo, 2. Doing, 3. Done)");
            int statusChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String taskStatus = getStatusFromChoice(statusChoice);

            Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
            Task.totalHours = Task.totalHours + taskDuration;
            tasks.add(task);

            // Use JOptionPane to display task details
            JOptionPane.showMessageDialog(null, task.printTaskDetails(), "Task Details", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("Total hours of all tasks: " + Task.returnTotalHours());
        
    }
    
    public void resetTotalHours() {
        Task.totalHours = 0;
    }

    
    private String getStatusFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "ToDo";
            case 2:
                return "Doing";
            case 3:
                return "Done";
            default:
                return "Unknown";
        }
    }
}



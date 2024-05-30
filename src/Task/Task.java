/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Task;

/**
 *
 
 */
public class Task {
    //attributes
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    //static attributes
    private static int taskCounter = 0;
    static int totalHours = 0;
    

    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskCounter++;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }
    
    
// I adapted this method which I took from Stack Overflow
 // https://stackoverflow.com/questions/68085395/how-to-write-java-string-length-method-code
 // GURU Shreyansh
 // https://stackoverflow.com/users/13882425/guru-shreyansh
    
    public boolean checkTaskDescription() {
    	System.out.println(taskDescription.length());
        return taskDescription.length() <= 50;
    }

    
    //method used to create the task ID
    public String createTaskID() {
        String taskNamePart = taskName.substring(0, 2).toUpperCase();
        String[] devFirstName = developerDetails.split(" ");
        String developerNamePart = devFirstName[0].substring(devFirstName[0].length() - 3).toUpperCase();
        return taskNamePart + ":" + taskNumber + ":" + developerNamePart;
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n"
                + "Developer Details: " + developerDetails + "\n"
                + "Task Number: " + taskNumber + "\n"
                + "Task Name: " + taskName + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Task ID: " + taskID + "\n"
                + "Task Duration: " + taskDuration + " hours";
    }

    public static int returnTotalHours() {
        return totalHours;
    }
}



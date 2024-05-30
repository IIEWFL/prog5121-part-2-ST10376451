/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManager;

import Task.Task;
import Task.TaskManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class TaskManagerTest {
  private TaskManager taskManager;

    @Before
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testTaskDescriptionLengthSuccess() {
        Task task = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 8, "ToDo");
        assertTrue(task.checkTaskDescription());
    }

  

    @Test
    public void testTaskIDCorrectness() {
        Task task = new Task("Login Feature", "Create Login to authenticate users", "Robyn Harrison", 10, "ToDo");
        System.out.println("Taskkkkkkkkkkkkkkkkkkkkk" + task.createTaskID());
        assertEquals("LO:0:BYN", task.createTaskID());
    }

    @Test
    public void testTotalHoursAccumulated() {
        String simulatedInput = "1\n2\nLogin Feature\nCreate Login to authenticate users\nRobyn Harrison\n8\n1\nAdd Task Feature\nCreate Add Task feature to add task users\nMike Smith\n10\n2\n3\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            taskManager.runApplication(new Scanner(System.in));
        } finally {
            System.setIn(stdin);
        }

        int expectedTotalHours = 18; // 8 + 10 from the provided tasks
        System.out.println("NDBS fn adbkjvs d jbs jb sj" + Task.returnTotalHours());
        assertEquals(expectedTotalHours, Task.returnTotalHours());
    }

    
    @Test
    public void testTaskDescriptionLengthFailure() {
        String simulatedInput = "1\n1\nFeature\nThis description is definitely more than fifty characters long.\nValid description\nDev One\n10\n1\n3\n";
        InputStream stdin = System.in;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            System.setOut(new PrintStream(outputStream));

            taskManager.runApplication(new Scanner(System.in));
        } finally {
            System.setIn(stdin);
            System.setOut(originalOut);
        }

        String output = outputStream.toString();
        assertEquals(true, output.contains("Please enter a task description of less than 50 characters"));
        assertEquals(true, output.contains("Task successfully captured"));
        taskManager.resetTotalHours();
    }
    
    @Test
    public void testAdditionalDataTotalHours() {
        String simulatedInput = "1\n5\nFeature1\nDescription1\nDev One\n10\n1\nFeature2\nDescription2\nDev Two\n12\n2\nFeature3\nDescription3\nDev Three\n55\n3\nFeature4\nDescription4\nDev Four\n11\n1\nFeature5\nDescription5\nDev Five\n1\n2\n3\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            taskManager.resetTotalHours();
            taskManager.runApplication(new Scanner(System.in));
        } finally {
            System.setIn(stdin);
        }

        int expectedTotalHours = 89; // 10 + 12 + 55 + 11 + 1
        assertEquals(expectedTotalHours, Task.returnTotalHours());
    }
}
   


package com.example.todolist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class AppIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testAppIntegration() {
        String simulatedUserInput = "1\nSample Task\nDescription\n" + // Add Task
                                    "2\n1\n" +                       // Mark Task as Completed
                                    "3\n1\nNew Title\nNew Description\n" + // Update Task
                                    "4\n1\n" +                       // Delete Task
                                    "5\n" +                          // View Tasks
                                    "6\n";                           // Exit
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(inputStream);

        App.main(new String[0]);

        String expectedOutput = "===== To-Do List Application =====\n" +
                "1. Add Task\n" +
                "2. Mark Task as Completed\n" +
                "3. Update Task\n" +
                "4. Delete Task\n" +
                "5. View Tasks\n" +
                "6. Exit\n" +
                "Enter your choice: Enter task title: Enter task description: Task added successfully.\n" +
                "Tasks:\n" +
                "Task 1: Sample Task\n" +
                "Description: Description\n" +
                "Completed: No\n" +
                "\n" +
                "===== To-Do List Application =====\n" +
                "1. Add Task\n" +
                "2. Mark Task as Completed\n" +
                "3. Update Task\n" +
                "4. Delete Task\n" +
                "5. View Tasks\n" +
                "6. Exit\n" +
                "Enter your choice: Exiting...\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}

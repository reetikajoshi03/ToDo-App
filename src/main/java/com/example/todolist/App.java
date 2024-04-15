package com.example.todolist;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== To-Do List Application =====");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. View Tasks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task task = new Task(title, description);
                    toDoList.addTask(task);
                    System.out.println("Task added successfully.");
                    break;
                case 2:
                    System.out.print("Enter the index of the task to mark as completed: ");
                    int indexComplete = scanner.nextInt();
                    toDoList.markTaskAsCompleted(indexComplete);
                    System.out.println("Task marked as completed.");
                    break;
                case 3:
                    System.out.print("Enter the index of the task to update: ");
                    int indexUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter updated task title: ");
                    String updatedTitle = scanner.nextLine();
                    System.out.print("Enter updated task description: ");
                    String updatedDescription = scanner.nextLine();
                    Task updatedTask = new Task(updatedTitle, updatedDescription);
                    toDoList.updateTask(indexUpdate, updatedTask);
                    System.out.println("Task updated successfully.");
                    break;
                case 4:
                    System.out.print("Enter the index of the task to delete: ");
                    int indexDelete = scanner.nextInt();
                    toDoList.deleteTask(indexDelete);
                    System.out.println("Task deleted successfully.");
                    break;
                case 5:
                    System.out.println("Tasks:");
                    int taskIndex = 0;
                    for (Task t : toDoList.viewTasks()) {
                        System.out.println("Task " + (taskIndex + 1) + ": " + t.getTitle());
                        System.out.println("Description: " + t.getDescription());
                        System.out.println("Completed: " + (t.isCompleted() ? "Yes" : "No"));
                        System.out.println();
                        taskIndex++;
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

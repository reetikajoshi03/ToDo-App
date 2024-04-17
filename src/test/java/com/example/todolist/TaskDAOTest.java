package com.example.todolist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TaskDAOTest {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            TaskDAO taskDAO = new TaskDAO(connection);

            // Create the tasks table
            createTasksTable(connection);

            // Test create and getAllTasks methods
            Task task1 = new Task("Task 1", "Description 1");
            taskDAO.createTask(task1);
            Task task2 = new Task("Task 2", "Description 2");
            taskDAO.createTask(task2);

            List<Task> tasks = taskDAO.getAllTasks();
            for (Task task : tasks) {
                System.out.println(task);
            }

            task1.setTitle("Updated Task 1");
            task1.setDescription("Updated Description 1");
            taskDAO.updateTask(task1);

            tasks = taskDAO.getAllTasks();
            for (Task task : tasks) {
                System.out.println(task);
            }

            // Test deleteTask method
            taskDAO.deleteTask(task2.getId());

            tasks = taskDAO.getAllTasks();
            for (Task task : tasks) {
                System.out.println(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTasksTable(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), description VARCHAR(255), completed BOOLEAN)");
        }
    }
}

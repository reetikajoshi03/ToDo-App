import java.util.ArrayList;
import java.util.List;

public class AppIntegrationTest {
    private List<Task> tasks;

    public AppIntegrationTest() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}

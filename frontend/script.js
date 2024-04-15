// Task data structure
class Task {
    constructor(title, description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
}

// Array to store tasks
let tasks = [];

// Function to show modal for adding a task
function showAddTaskForm() {
    const modal = document.getElementById("modal-container");
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add Task</h2>
            <input type="text" id="task-title" placeholder="Title"><br>
            <textarea id="task-description" placeholder="Description"></textarea><br>
            <button onclick="addTask()">Add</button>
        </div>
    `;
    modal.style.display = "block";
}

// Function to add a task
function addTask() {
    const title = document.getElementById("task-title").value;
    const description = document.getElementById("task-description").value;
    const task = new Task(title, description);
    tasks.push(task);
    closeModal();
    viewTasks();
}

// Function to close modal
function closeModal() {
    const modal = document.getElementById("modal-container");
    modal.style.display = "none";
}

// Function to view tasks
function viewTasks() {
    const tasksContainer = document.getElementById("tasks-container");
    
    // Check if tasksContainer exists
    if (!tasksContainer) {
        console.error("Tasks container not found.");
        return;
    }
    
    // Clear existing tasks
    tasksContainer.innerHTML = "";

    // Check if there are tasks to display
    if (tasks.length === 0) {
        const noTasksMessage = document.createElement("p");
        noTasksMessage.textContent = "No tasks available.";
        tasksContainer.appendChild(noTasksMessage);
        return;
    }

    // Iterate over tasks and create task elements
    tasks.forEach((task, index) => {
        const taskElement = document.createElement("div");
        taskElement.classList.add("task");
        taskElement.innerHTML = `
            <h3>${task.title}</h3>
            <p>${task.description}</p>
            <button onclick="markTaskCompleted(${index})">Mark Completed</button>
            <button onclick="showUpdateTaskForm(${index})">Update Task</button>
            <button onclick="deleteTask(${index})">Delete Task</button>
        `;

        // Add completed class if task is completed
        if (task.completed) {
            taskElement.classList.add("completed");
        }

        // Append task element to tasks container
        tasksContainer.appendChild(taskElement);
    });
}


// Function to mark a task as completed
function markTaskCompleted(index) {
    tasks[index].completed = true;
    viewTasks();
}

// Function to delete a task
function deleteTask(index) {
    tasks.splice(index, 1);
    closeModal();
    viewTasks();
}


// Function to show modal for updating a task
function showUpdateTaskForm(index) {
    const task = tasks[index];
    const modal = document.getElementById("modal-container");
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Update Task</h2>
            <input type="text" id="update-task-title" value="${task.title}"><br>
            <textarea id="update-task-description">${task.description}</textarea><br>
            <button onclick="updateTask(${index})">Update</button>
        </div>
    `;
    modal.style.display = "block";
}

// Function to update a task
function updateTask(index) {
    const title = document.getElementById("update-task-title").value;
    const description = document.getElementById("update-task-description").value;
    tasks[index].title = title;
    tasks[index].description = description;
    closeModal();
    viewTasks();
}

// Function to toggle the completion state of a task
function toggleCompleted(button) {
    // Get the parent task element
    var task = button.parentNode;

    // Toggle the 'completed' class on the task element
    task.classList.toggle('completed');
    
    // Change the button text based on the task's completion state
    if (task.classList.contains('completed')) {
        button.textContent = 'Mark Incomplete';
    } else {
        button.textContent = 'Mark Completed';
    }
}



// Function to exit the application
function exitApp() {
    location.reload();
}
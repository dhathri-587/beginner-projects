let tasks = JSON.parse(localStorage.getItem("studyTasks")) || [];

let currentFilter = "all";


// Add Task

document.getElementById("taskForm").addEventListener("submit", function(event) {

    event.preventDefault();

    const subject = document.getElementById("subject").value;
    const task = document.getElementById("task").value;
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const priority = document.getElementById("priority").value;

    const newTask = {

        id: Date.now(),

        subject: subject,

        task: task,

        date: date,

        time: time,

        priority: priority,

        completed: false

    };

    tasks.push(newTask);

    saveTasks();

    displayTasks();

    document.getElementById("taskForm").reset();

});


// Display Tasks

function displayTasks() {

    const taskList = document.getElementById("taskList");

    taskList.innerHTML = "";

    let filteredTasks = tasks;

    if (currentFilter === "pending") {

        filteredTasks = tasks.filter(task => !task.completed);

    }

    if (currentFilter === "completed") {

        filteredTasks = tasks.filter(task => task.completed);

    }


    if (filteredTasks.length === 0) {

        taskList.innerHTML = `
            <div class="task-card">
                <p>No tasks found. Add a study task above! 📚</p>
            </div>
        `;

    }


    filteredTasks.forEach(task => {

        const taskCard = document.createElement("div");

        taskCard.className = "task-card";

        if (task.completed) {

            taskCard.classList.add("completed");

        }


        taskCard.innerHTML = `

            <div class="task-info">

                <h3>${task.subject}</h3>

                <p>📖 ${task.task}</p>

                <p>📅 ${task.date} &nbsp; ⏰ ${task.time}</p>

                <p class="priority ${task.priority.toLowerCase()}">
                    Priority: ${task.priority}
                </p>

            </div>


            <div class="task-actions">

                <button
                    class="complete-btn"
                    onclick="completeTask(${task.id})">
                    ${task.completed ? "↩️ Undo" : "✅ Done"}
                </button>

                <button
                    class="delete-btn"
                    onclick="deleteTask(${task.id})">
                    🗑️ Delete
                </button>

            </div>

        `;


        taskList.appendChild(taskCard);

    });


    updateStats();

}


// Complete Task

function completeTask(id) {

    tasks = tasks.map(task => {

        if (task.id === id) {

            task.completed = !task.completed;

        }

        return task;

    });

    saveTasks();

    displayTasks();

}


// Delete Task

function deleteTask(id) {

    tasks = tasks.filter(task => task.id !== id);

    saveTasks();

    displayTasks();

}


// Save Tasks

function saveTasks() {

    localStorage.setItem("studyTasks", JSON.stringify(tasks));

}


// Update Statistics

function updateStats() {

    const total = tasks.length;

    const completed = tasks.filter(task => task.completed).length;

    const pending = total - completed;


    document.getElementById("totalTasks").textContent = total;

    document.getElementById("completedTasks").textContent = completed;

    document.getElementById("pendingTasks").textContent = pending;

}


// Filters

document.querySelectorAll(".filter-btn").forEach(button => {

    button.addEventListener("click", function() {

        document.querySelectorAll(".filter-btn")
            .forEach(btn => btn.classList.remove("active"));

        this.classList.add("active");

        currentFilter = this.dataset.filter;

        displayTasks();

    });

});


// Dark Mode

document.getElementById("themeBtn").addEventListener("click", function() {

    document.body.classList.toggle("dark");

    if (document.body.classList.contains("dark")) {

        this.textContent = "☀️";

    } else {

        this.textContent = "🌙";

    }

});


// Load Tasks

displayTasks();
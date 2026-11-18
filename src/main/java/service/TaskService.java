
package com.example.todolistcrud.service;

import com.example.todolistcrud.entity.Task;
import com.example.todolistcrud.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Create task
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get one task
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Update task
    public Task updateTask(Long id, Task newTask) {

        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {

            task.setTitle(newTask.getTitle());
            task.setDescription(newTask.getDescription());
            task.setDueDate(newTask.getDueDate());
            task.setCompleted(newTask.isCompleted());

            return taskRepository.save(task);
        }

        return null;
    }

    // Delete task
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}

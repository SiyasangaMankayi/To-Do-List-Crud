

package com.example.todolistcrud.dto;

import com.example.todolistcrud.dto.TaskRequestDTO;
import com.example.todolistcrud.dto.TaskResponseDTO;

import com.example.todolistcrud.entity.Task;
import com.example.todolistcrud.service.TaskService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Create task
    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setCompleted(request.isCompleted());

        return convertToDTO(taskService.createTask(task));
    }


    // Get all tasks
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // Get one task
    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return convertToDTO(taskService.getTaskById(id));
    }
    // Update task
    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO request) {

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setCompleted(request.isCompleted());

        return convertToDTO(taskService.updateTask(id, task));
    }

    // Delete task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
    private TaskResponseDTO convertToDTO(Task task) {

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.isCompleted()
        );
    }

}
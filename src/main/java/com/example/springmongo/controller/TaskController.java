package com.example.springmongo.controller;

import com.example.springmongo.model.Task;
import com.example.springmongo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable String taskId) {
        return taskService.findTaskById(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Task> getTaskBySeverity(@PathVariable int severity) {
        return taskService.findTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee) {
        return taskService.findTaskByAssignee(assignee);
    }

    @PutMapping
    public Task updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }

}

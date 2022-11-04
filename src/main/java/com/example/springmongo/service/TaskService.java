package com.example.springmongo.service;

import com.example.springmongo.model.Task;
import com.example.springmongo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findTaskBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> findTaskByAssignee(String assignee) {
        return taskRepository.getTaskByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest) {
        Task existingTask = this.findTaskById(taskRequest.getTaskId());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setDesc(taskRequest.getDesc());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        existingTask.setSeverity(taskRequest.getSeverity());
        return taskRepository.save(existingTask);
    }

    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return taskId+"task deleted";
    }

}

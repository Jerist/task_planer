package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.storage.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TaskService {
    private static final Logger logger = LogManager.getLogger(TaskService.class);
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(String title, int priority) {
        logger.info("Creating task: {}", title);
        return repository.save(title, priority);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public boolean completeTask(int id) {
        logger.debug("Completing task {}", id);
        return repository.updateStatus(id, "DONE");
    }
}
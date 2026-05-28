package com.taskmanager.storage;

import com.taskmanager.model.Task;
import java.util.*;

public class TaskRepository {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1;

    public Task save(String title, int priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.put(task.getId(), task);
        return task;
    }

    public Optional<Task> findById(int id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public boolean updateStatus(int id, String status) {
        Task t = tasks.get(id);
        if (t == null) return false;
        t.setStatus(status);
        return true;
    }
}
package com.taskmanager.model;

public class Task {
    private int id;
    private String title;
    private String status;
    private int priority;

    public Task(int id, String title, int priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = "NEW";
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public String toString() {
        return String.format("[%d] %s (priority=%d, status=%s)", id, title, priority, status);
    }
}
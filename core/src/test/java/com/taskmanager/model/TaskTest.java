package com.taskmanager.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testConstructorAndGetters() {
        Task task = new Task(1, "Test Task", 3);
        assertEquals(1, task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals(3, task.getPriority());
        assertEquals("NEW", task.getStatus()); // статус по умолчанию
    }

    @Test
    void testSetters() {
        Task task = new Task(2, "Another", 2);
        task.setStatus("DONE");
        assertEquals("DONE", task.getStatus());

        task.setPriority(5);
        assertEquals(5, task.getPriority());
    }

    @Test
    void testToString() {
        Task task = new Task(3, "Learn Java", 4);
        task.setStatus("IN_PROGRESS");
        String str = task.toString();
        assertTrue(str.contains("3"));
        assertTrue(str.contains("Learn Java"));
        assertTrue(str.contains("priority=4"));
        assertTrue(str.contains("status=IN_PROGRESS"));
    }
}
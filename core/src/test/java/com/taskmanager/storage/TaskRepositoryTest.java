package com.taskmanager.storage;

import com.taskmanager.model.Task;
import org.junit.jupiter.api.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryTest {

    private TaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TaskRepository();
        System.out.println("Before each test: repository is fresh");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test: no cleanup needed");
    }

    @Test
    void testSaveAndFind() {
        Task saved = repository.save("Купить молоко", 2);
        assertNotNull(saved.getId());
        assertEquals("Купить молоко", saved.getTitle());
        assertEquals(2, saved.getPriority());

        Optional<Task> found = repository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    void testUpdateStatus() {
        Task task = repository.save("Сделать практику", 5);
        boolean updated = repository.updateStatus(task.getId(), "DONE");
        assertTrue(updated);
        assertEquals("DONE", task.getStatus());

        // пробуем обновить несуществующую задачу
        boolean fakeUpdate = repository.updateStatus(999, "DONE");
        assertFalse(fakeUpdate);
    }

    @Test
    void testFindAll() {
        repository.save("Task A", 1);
        repository.save("Task B", 2);
        assertEquals(2, repository.findAll().size());
    }
}
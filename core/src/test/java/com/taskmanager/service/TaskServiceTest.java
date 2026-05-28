package com.taskmanager.service;

import com.taskmanager.model.Task;
import com.taskmanager.storage.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // включает поддержку Mockito в JUnit 5
class TaskServiceMockTest {

    @Mock
    private TaskRepository repository;  // создаётся мок-объект

    @InjectMocks
    private TaskService service;        // в этот сервис автоматически будет внедрён мок репозитория

    @Test
    void testCreateTaskWithMock() {
        Task dummyTask = new Task(1, "Учёба", 5);
        when(repository.save("Учёба", 5)).thenReturn(dummyTask);

        Task created = service.createTask("Учёба", 5);

        assertEquals("Учёба", created.getTitle());
        verify(repository, times(1)).save("Учёба", 5);
    }

    @Test
    void testCompleteTaskWithMock() {
        when(repository.updateStatus(10, "DONE")).thenReturn(true);

        boolean result = service.completeTask(10);

        assertTrue(result);
        verify(repository).updateStatus(10, "DONE");
    }

    @Test
    void testGetAllTasksWithMock() {
        when(repository.findAll()).thenReturn(List.of(new Task(1, "A", 1), new Task(2, "B", 2)));

        assertEquals(2, service.getAllTasks().size());
        verify(repository).findAll();
    }
}



class TaskServiceTest {

    private TaskService service;
    private TaskRepository repository;

    @BeforeEach
    void init() {
        repository = new TaskRepository();
        service = new TaskService(repository);
    }

    @Test
    void createTaskShouldIncreaseSize() {
        Task task = service.createTask("Позвонить маме", 3);
        assertNotNull(task);
        assertEquals(1, service.getAllTasks().size());
    }

    @Test
    void completeTaskShouldChangeStatus() {
        Task task = service.createTask("Завершить отчёт", 4);
        boolean result = service.completeTask(task.getId());
        assertTrue(result);
        assertEquals("DONE", task.getStatus());
    }

    @Test
    void completeNonExistingTaskShouldReturnFalse() {
        boolean result = service.completeTask(999);
        assertFalse(result);
    }
}
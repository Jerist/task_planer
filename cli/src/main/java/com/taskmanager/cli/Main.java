package com.taskmanager.cli;

import com.taskmanager.model.Task;
import com.taskmanager.service.TaskService;
import com.taskmanager.storage.TaskRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    /*
     * ОПИСАНИЕ ЗАДАЧИ:
     * Консольное приложение для управления задачами.
     * Возможности: создание задачи с названием и приоритетом (1-5),
     * просмотр всех задач, отметка задачи выполненной.
     * Данные в памяти. Логирование через Log4j2.
     * Сборка – Maven, многомодульный проект, assembly для fat JAR.
     * Автор: Дедов Д.В., группа 14.
     */
    public static void main(String[] args) {
        logger.info("Task Planner started");
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);

        while (true) {
            ConsoleHelper.printMenu();
            int choice = ConsoleHelper.readInt();
            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = ConsoleHelper.readLine();
                    System.out.print("Priority (1-5): ");
                    int priority = ConsoleHelper.readInt();
                    if (priority < 1) priority = 1;
                    if (priority > 5) priority = 5;
                    Task t = service.createTask(title, priority);
                    System.out.println("Created: " + t);
                    break;
                case 2:
                    service.getAllTasks().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Task ID: ");
                    int id = ConsoleHelper.readInt();
                    if (service.completeTask(id)) {
                        System.out.println("Completed.");
                    } else {
                        System.out.println("Not found.");
                    }
                    break;
                case 0:
                    logger.info("Exit");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}// temporary code
// temporary code

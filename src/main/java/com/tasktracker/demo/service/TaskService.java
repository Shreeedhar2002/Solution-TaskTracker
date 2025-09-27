package com.tasktracker.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasktracker.demo.Model.Task;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final String filename = "./tasks.json";

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Task> loadTasks() {
        try {
            File file  = new File(filename);

            if (!file.exists()){
                file.createNewFile();

                mapper.readValue(file, new TypeReference<List<Task>>() {
                });
            }
        } catch (Exception e) {
            System.err.println("Error Loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
        return List.of();
    }
    public void saveTasks(List<Task> tasks){
        try{
            mapper.writeValue(new File(filename),tasks);
        }
        catch (Exception e) {
            System.err.println("Error Saving tasks: " + e.getMessage());
        }
    }
    public void addTask(String desc) {
        List<Task> tasks = loadTasks();
        int newId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
        String now = LocalDateTime.now().toString();
        Task t = new Task(newId, desc, "todo", now, now);
        tasks.add(t);
        saveTasks(tasks);
        System.out.println("Added task id: " + newId);
    }

    public void updateTask(int id, String desc) {
        List<Task> tasks = loadTasks();
        boolean updated = false;
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDescription(desc);
                t.setModdat(LocalDateTime.now().toString());
                updated = true;
                break;
            }
        }
        saveTasks(tasks);
        System.out.println(updated ? "Task updated." : "Task not found.");
    }

    public void deleteTask(int id) {
        List<Task> tasks = loadTasks();
        boolean removed = tasks.removeIf(t -> t.getId() == id);
        saveTasks(tasks);
        System.out.println(removed ? "Task deleted." : "Task not found.");
    }

    public void changeStatus(int id, String newStatus) {
        List<Task> tasks = loadTasks();
        boolean found = false;
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(newStatus);
                t.setModdat(LocalDateTime.now().toString());
                found = true;
                break;
            }
        }
        saveTasks(tasks);
        System.out.println(found ? "Status changed." : "Task not found.");
    }

    public void listTasks(String filter) {
        List<Task> tasks = loadTasks();
        for (Task t : tasks) {
            boolean show = true;
            if (filter != null) {
                switch (filter) {
                    case "done": show = t.getStatus().equals("done"); break;
                    case "notdone": show = !t.getStatus().equals("done"); break;
                    case "in-progress": show = t.getStatus().equals("in-progress"); break;
                    default: show = true;
                }
            }
            if (show) {
                System.out.printf("[%d] %s (%s) - Created: %s Updated: %s ",
                        t.getId(), t.getDescription(), t.getStatus(), t.getCredat(), t.getModdat());
            }
        }
    }
}

package com.app.quickTask.service;

import com.app.quickTask.entity.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TaskService {

    private static List<Task> tasks = new ArrayList<>();
    private static int countId = 1;
    static {
        tasks.add(new Task(countId++,"esha", "Complete website", LocalDate.now().plusMonths(2), false));
        tasks.add(new Task(countId++,"esha", "Practice LeetCode", LocalDate.now().plusMonths(12), true));

    }
    public List<Task> getTasks(){
        return tasks;
    }
    public List<Task >getTasksByusername(String username){
        return tasks.stream().filter(task -> task.getUsername().equalsIgnoreCase(username)).toList();
    }

    public void addTask(Task task, String username){
        Task newTask = new Task(countId++, username,task.getDescription(), LocalDate.now().plusMonths(1), task.isComplete());
        tasks.add(newTask);
    }

    public void removeTask(int id){
        Predicate<? super Task> predicate = todo -> todo.getId() == id;
        tasks.removeIf(predicate);
    }

    public Task getTaskById(int id){
        for (Task task: tasks) {
            if (task.getId() == id){
                return task;
            }
        }
        return new Task();
    }

    public void updateTask(Task task){
        for (Task t: tasks) {
            if (t.getId() == task.getId()){
                t.setDescription(task.getDescription());
                t.setComplete(task.isComplete());
                t.setCompletionDate(task.getCompletionDate());
            }
        }
    }

}

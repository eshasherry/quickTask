package com.app.quickTask.controller;

import com.app.quickTask.entity.Task;
import com.app.quickTask.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("username")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/")
    public String listTasksHome( ModelMap map){
        List<Task> tasks = taskService.getTasks();
        map.put("tasks", tasks);
        return "listTasks";
    }
    @RequestMapping(value = "/listTasks")
    public String listTasks( ModelMap map){
        List<Task> tasks = taskService.getTasks();
        map.put("tasks", tasks);
        return "listTasks";
    }
    @RequestMapping(value = "addTask", method = RequestMethod.GET)
    public String addTask(ModelMap map){
        Task task = new Task("", false);
        map.put("task", task);
        return "newTask";
    }

    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public String addTask(@Valid Task task, BindingResult result){
        if(result.hasErrors()){
            return "newTask";
        }
        taskService.addTask(task.getDescription(), task.isComplete());
        return "redirect:listTasks";
    }

    @RequestMapping(value = "removeTask", method = RequestMethod.GET)
    public String removeTask(@RequestParam int id){
        taskService.removeTask(id);
        return "redirect:listTasks";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.GET)
    public String updateTask(@RequestParam int id, ModelMap map){
       Task task =  taskService.getTaskById(id);
       map.put("task", task);
        return "newTask";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.POST)
    public String updateTask(@Valid Task task, BindingResult result){
        if(result.hasErrors()){
            return "newTask";
        }
        taskService.updateTask(task);
        return "redirect:listTasks";
    }
}

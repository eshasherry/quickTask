package com.app.quickTask.controller;

import com.app.quickTask.entity.Task;
import com.app.quickTask.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@SessionAttributes("username")
public class TaskController {
  //  @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/listTasks")
    public String listTasks( ModelMap map){
        String username = getLoggenInUsername();
        List<Task> tasks = taskService.getTasksByusername(username);
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
        String username = getLoggenInUsername();
        taskService.addTask(task, username);
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
    private String getLoggenInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

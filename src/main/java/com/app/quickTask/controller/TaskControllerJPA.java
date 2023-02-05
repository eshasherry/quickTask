package com.app.quickTask.controller;

import com.app.quickTask.entity.Task;
import com.app.quickTask.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("username")
public class TaskControllerJPA {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/listTasks")
    public String listTasks( ModelMap map){
        String username = getLoggenInUsername();
        List<Task> tasks = taskRepository.getTasksByusername(username);
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
        task.setUsername(username);
        taskRepository.save(task);
        return "redirect:listTasks";
    }

    @RequestMapping(value = "removeTask", method = RequestMethod.GET)
    public String removeTask(@RequestParam int id){
        taskRepository.deleteById(id);
        return "redirect:listTasks";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.GET)
    public String updateTask(@RequestParam int id, ModelMap map){
        Optional<Task> task = taskRepository.findById(id);
        map.put("task", task);
        return "newTask";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.POST)
    public String updateTask(@Valid Task task, BindingResult result){
        if(result.hasErrors()){
            return "newTask";
        }
        task.setUsername(getLoggenInUsername());
        taskRepository.save(task);
        return "redirect:listTasks";
    }
    private String getLoggenInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

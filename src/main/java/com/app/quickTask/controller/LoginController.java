package com.app.quickTask.controller;

import com.app.quickTask.entity.Task;
import com.app.quickTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("username")
public class LoginController {
    @Autowired
    private TaskService taskService;
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/")
    public String listTasksHome( ModelMap map){
        String username = getLoggenInUsername();
        List<Task> tasks = taskService.getTasksByusername(username);
        map.put("tasks", tasks);
        map.put("username", username);
        return "listTasks";
    }
    private String getLoggenInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

package com.loginapp.demo.controller;

import com.loginapp.demo.dao.TaskDao;
import com.loginapp.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskDao taskDao;

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task) {
        return taskDao.save(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable long id) {
        Task task = taskDao.findById(id);
        return task;
    }

    @GetMapping("/get")
    public List<Task> getAllTasks() {
         List<Task> tasks = taskDao.findAll();
        return tasks;
    }

    @PutMapping("/update")
    public Task updateTask(@RequestBody Task task) {
        taskDao.save(task);
        return task;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable long id) {
        taskDao.deleteById(id);
    }

}

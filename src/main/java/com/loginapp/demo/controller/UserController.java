package com.loginapp.demo.controller;

import com.loginapp.demo.dao.TaskDao;
import com.loginapp.demo.dao.UserDao;
import com.loginapp.demo.model.Task;
import com.loginapp.demo.model.User;
import com.loginapp.demo.request.UsernameTaskCreateRequest;
import com.loginapp.demo.request.UserTasksRequest;
import com.loginapp.demo.request.UsernameTaskUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @GetMapping("/get")
    public List<User> getUsers() {
        List<User> users = userDao.findAll();
        return users;
    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userDao.findByUsername(username);
        return user;
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        userDao.save(user);
        return user;
    }

    @PutMapping("/task/add")
    public User addTask(@RequestBody UsernameTaskCreateRequest usernameTaskCreateRequest) {
        User result = userDao.findByUsername(usernameTaskCreateRequest.getUsername());
        List<Task> tasks = result.getTasks();
        Task task = taskDao.save(new Task(usernameTaskCreateRequest.getTask()));
        tasks.add(task);
        result.setTasks(tasks);
        userDao.save(result);
        return result;
    }

    // You can only save the new task in task controller (because they are joined)
    /*@PutMapping("/task/update")
    public User updateTask(@RequestBody UsernameTaskUpdateRequest usernameTaskUpdateRequest) {
        User result = userDao.findByUsername(usernameTaskUpdateRequest.getUsername());
        List<Task> tasks = result.getTasks();
        Task task = taskDao.save(new Task(usernameTaskUpdateRequest.getTask()));
        tasks.add(task);
        result.setTasks(tasks);
        userDao.save(result);
        return result;
    }*/

    @PutMapping("/task/update/all")
    public User updateAllTasks(@RequestBody UserTasksRequest userTasksRequest) {
        User result = userDao.findByUsername(userTasksRequest.getUsername());
        List<Task> tasks = result.getTasks();
        result.setTasks(tasks);
        userDao.save(result);
        return result;
    }

    @GetMapping("/task/get/{username}")
    public List<Task> getTasks(@PathVariable String username) {
        User user = userDao.findByUsername(username);
        List<Task> tasks = user.getTasks();
        return tasks;
    }

    @PutMapping("/task/delete")
    public List<Task> deleteTask(@RequestBody UsernameTaskCreateRequest request) {
        User user = userDao.findByUsername(request.getUsername());
        List<Task> tasks = user.getTasks();
        tasks.remove(request.getTask());
        user.setTasks(tasks);
        userDao.save(user);
        return user.getTasks();
    }
}

package com.loginapp.demo.controller;

import com.loginapp.demo.dao.TaskDao;
import com.loginapp.demo.dao.UserDao;
import com.loginapp.demo.model.Task;
import com.loginapp.demo.model.User;
import com.loginapp.demo.request.AddTaskRequest;
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
    public User addTask(@RequestBody AddTaskRequest addTaskRequest) {
        User result = userDao.findById(addTaskRequest.getUserId());
        List<Task> tasks = result.getTasks();
        tasks.add(addTaskRequest.getTask());
        result.setTasks(tasks);
        userDao.save(result);
        return result;
    }

}

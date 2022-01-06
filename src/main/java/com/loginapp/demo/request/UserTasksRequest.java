package com.loginapp.demo.request;

import com.loginapp.demo.model.Task;

public class UserTasksRequest {
    private String username;
    private Task[] tasks;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserTasksRequest(String username, Task[] tasks){
        this.username = username;
        this.tasks = tasks;
    }
}
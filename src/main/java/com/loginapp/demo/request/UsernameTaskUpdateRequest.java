package com.loginapp.demo.request;

import com.loginapp.demo.model.Task;

public class UsernameTaskUpdateRequest {

    private String username;

    private Task task;

    public UsernameTaskUpdateRequest(String username, Task task) {
        this.username = username;
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

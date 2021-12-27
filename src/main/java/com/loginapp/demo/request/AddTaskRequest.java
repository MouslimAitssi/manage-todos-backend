package com.loginapp.demo.request;

import com.loginapp.demo.model.Task;

public class AddTaskRequest {
    private long userId;
    private Task task;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

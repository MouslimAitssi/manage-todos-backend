package com.loginapp.demo.dao;

import com.loginapp.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {
    Task findById(long id);
}

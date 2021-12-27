package com.loginapp.demo.dao;

import com.loginapp.demo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findById(long id);

    @Override
    User save(User user);

    @Override
    List<User> findAll();
}

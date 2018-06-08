package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {
    void save(User user);

    User findByName(String name);
    User findOne(Long id);
    List<User> findAll();
}
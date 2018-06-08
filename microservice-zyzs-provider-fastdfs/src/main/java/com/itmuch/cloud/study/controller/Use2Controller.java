package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.entity.User;
import com.itmuch.cloud.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Use2Controller {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/test")
    public User findById() {
        User findOne = this.userRepository.findOne(1L);
        return findOne;
    }
}

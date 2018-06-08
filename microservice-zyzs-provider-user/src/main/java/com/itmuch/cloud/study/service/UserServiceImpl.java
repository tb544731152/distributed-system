package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.User;
import com.itmuch.cloud.study.repository.UserMonogDBRepository;
import com.itmuch.cloud.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements  UserService{
    @Autowired
    private UserRepository userDBRepository;
    @Autowired
    private UserMonogDBRepository userMGRepository;
    @Override
    public void save(User user) {
        userMGRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return this.userMGRepository.findByName(name);
    }
    @Override
    public List<User> findAll() {
        return this.userDBRepository.findAll();
    }
    @Override
    public User findOne(Long id) {
        return userDBRepository.findOne(id);
    }
}

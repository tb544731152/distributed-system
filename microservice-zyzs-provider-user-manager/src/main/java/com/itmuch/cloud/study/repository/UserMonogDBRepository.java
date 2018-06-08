package com.itmuch.cloud.study.repository;

import com.itmuch.cloud.study.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMonogDBRepository  extends MongoRepository<User, String> {
    User findByName(String name);
}

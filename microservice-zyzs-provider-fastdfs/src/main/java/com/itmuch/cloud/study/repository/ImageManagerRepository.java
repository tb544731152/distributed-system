package com.itmuch.cloud.study.repository;

import com.itmuch.cloud.study.entity.ImageManager;
import com.itmuch.cloud.study.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageManagerRepository extends JpaRepository<ImageManager, Long> {
}

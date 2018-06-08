package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.PublicNum;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublicNumService {
    List<PublicNum> findAll();
}
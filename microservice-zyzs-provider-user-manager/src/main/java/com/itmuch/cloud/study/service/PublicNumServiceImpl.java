package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.PublicNum;
import com.itmuch.cloud.study.repository.PublicNumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicNumServiceImpl implements  PublicNumService{
    @Autowired
    private PublicNumRepository publicNumRepository;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<PublicNum> findAll() {
        return publicNumRepository.findAll();
    }
}

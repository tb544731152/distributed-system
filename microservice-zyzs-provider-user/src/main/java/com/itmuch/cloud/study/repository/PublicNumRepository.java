package com.itmuch.cloud.study.repository;

import com.itmuch.cloud.study.entity.PublicNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicNumRepository extends JpaRepository<PublicNum, String> {
    @Query(value = "select * from oo_public_num ", nativeQuery = true)
    List<PublicNum> findAll();
}

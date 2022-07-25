package com.schoolfeespayment.dao;

import com.schoolfeespayment.POJO.Parent;
import com.schoolfeespayment.wrapper.ParentWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ParentDao extends JpaRepository<Parent, Integer> {

    Parent findByEmailId(@Param("email") String email);

    List<ParentWrapper> getAllParent();


    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    Parent findByEmail(String email);
}

package com.school.app.Schoolfeesapp.dao;

import com.school.app.Schoolfeesapp.POJO.Parent;
import com.school.app.Schoolfeesapp.wrapper.ParentWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ParentDao extends JpaRepository<Parent, Integer> {

    Parent findByEmailId(@Param("email") String email);

    List<ParentWrapper> getAllParent();


    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    Parent findByEmail(String email);
}

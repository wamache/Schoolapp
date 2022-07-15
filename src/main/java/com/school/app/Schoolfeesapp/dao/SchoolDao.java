package com.school.app.Schoolfeesapp.dao;

import com.school.app.Schoolfeesapp.POJO.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SchoolDao extends JpaRepository<School, Integer> {


    School findByEmailId(@Param("email") String email);
}

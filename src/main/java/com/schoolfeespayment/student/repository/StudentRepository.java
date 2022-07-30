package com.schoolfeespayment.student.repository;

import com.schoolfeespayment.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


public interface StudentRepository extends JpaRepository <Student, Long>{
    @Transactional
    @Modifying
    @Query("delete from Student s ")
    void deleteStudentByRegNumber(String regNumber);

    //Map<String, String> findByRegNumber(String regNumber);
}

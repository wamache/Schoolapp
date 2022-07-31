package com.schoolfeespayment.dependants.repository;

import com.schoolfeespayment.dependants.entity.Dependants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface DependantsRepository extends JpaRepository <Dependants, Long>{
    @Transactional
    @Modifying
    @Query("delete from Dependants d ")
    void deleteStudentByRegNumber(String regNumber);

    //Map<String, String> findByRegNumber(String regNumber);
}

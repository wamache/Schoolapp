package com.schoolfeespayment.dependants.entity;

import com.schoolfeespayment.POJO.Parent;
import com.schoolfeespayment.student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name="dependants")
    public class Dependants {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column(name="schoolname", nullable=false)
        private String schoolName;
        @Column(name="regnumber", nullable=false)
        private String regNumber;

        @ManyToOne
        @JoinColumn(
                nullable = false,
                name = "student_id"
        )

        private Student student;











    }


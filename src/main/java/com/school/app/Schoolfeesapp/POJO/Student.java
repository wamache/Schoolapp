package com.school.app.Schoolfeesapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="student")

public class Student implements Serializable {

        private static final long serialVersionUID =1L;
        @Id
        @SequenceGenerator(
                name = "sequence-name",
                sequenceName = "sequence-name",
                allocationSize = 1
        )
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Integer id;
        @Column(name="student_name", nullable=false)
        private String studentName;

        @Column(name="reg_number", nullable=false)
        private String regNumber;

        @Column(name="parent_id", nullable=false)
        private String parent_id;

        @Column(name="role")
        private String role;

        @Column(name="status")
        private String status;

}

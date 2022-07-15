package com.school.app.Schoolfeesapp.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name= "School.findByEmailId", query="select s from School s where s.email=:email")
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="school")

public class School implements Serializable {

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
    @Column(name="school_code", nullable=false)
    private String schoolCode;

    @Column(name="school_name", nullable=false)
    private String schoolName;

    @Column(name="user_name", nullable=false)
    private String userName;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="contact_number", nullable=false)
    private String contactNumber;

    @Column(name="password", nullable=false)
    private String password;

    private String confirmPassword;

    @Column(name="role")
    private String role;

    @Column(name="status")
    private String status;


}

package com.schoolfeespayment.POJO;

//import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;


@NamedQuery(name= "Parent.findByEmailId", query="select p from Parent p where p.email=:email")
@NamedQuery(name= "Parent.getAllParent", query="select new com.schoolfeespayment.wrapper.ParentWrapper(p.id,p.firstName,p.lastName,p.userName,p.idNumber,p.email, p.contactNumber, p.status ) from Parent p where p.role='parent'")


@NamedQuery(name = "Parent.updateStatus", query = "update Parent p set p.status=:status where p.id=:id ")
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="parent")
public class Parent implements Serializable {

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
    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name="user_name", nullable=false)
    private String userName;

    @Column(name="id_number", nullable=false)
    private String idNumber;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="contact_number", nullable=false)
    private String contactNumber;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="status")
    private String status;
//
//    @Column(name = "verification_code", length = 64)
//    private String verificationCode;
//
//    private boolean enabled;



}


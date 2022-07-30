package com.schoolfeespayment.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParentWrapper {

    //ParentWrapper parent = new ParentWrapper();

    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String idNumber;
    private String email;
    private String contactNumber;
    private String status;


//    public ParentWrapper(String verificationCode, boolean enabled) {
//        this.verificationCode = verificationCode;
//        this.enabled = enabled;
//    }

    public ParentWrapper(Integer id, String firstName, String lastName, String userName, String idNumber, String email, String contactNumber, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.idNumber = idNumber;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;

    }


}



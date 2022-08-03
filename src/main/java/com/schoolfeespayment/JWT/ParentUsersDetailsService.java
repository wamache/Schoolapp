package com.schoolfeespayment.JWT;

import com.schoolfeespayment.POJO.Parent;
import com.schoolfeespayment.dao.ParentDao;
import com.schoolfeespayment.rest.ParentRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ParentUsersDetailsService implements UserDetailsService {

    @Autowired
    ParentDao parentDao;


    private com.schoolfeespayment.POJO.Parent userDetail;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}", username);
        userDetail = parentDao.findByEmailId(username);
        if (!Objects.isNull(userDetail)){
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }




    public  com.schoolfeespayment.POJO.Parent getUserDetail(){
//        com.school.app.Schoolfeesapp.POJO.Parent parent = userDetail;
//        parent.setPassword(null);
//        return parent;
        return userDetail;
    }

//    public com.school.app.Schoolfeesapp.POJO.Parent isEnabled() {
//        com.school.app.Schoolfeesapp.POJO.Parent parent = userDetail;
//        parent.isEnabled();
//        return parent;
//    }


}

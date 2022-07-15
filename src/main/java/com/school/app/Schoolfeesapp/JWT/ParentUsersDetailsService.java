package com.school.app.Schoolfeesapp.JWT;

import com.school.app.Schoolfeesapp.dao.ParentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Slf4j
@Service
public class ParentUsersDetailsService implements UserDetailsService {

    @Autowired
    ParentDao parentDao;

    private com.school.app.Schoolfeesapp.POJO.Parent userDetail;

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

    public  com.school.app.Schoolfeesapp.POJO.Parent getUserDetail(){
        //com.school.app.Schoolfeesapp.POJO.Parent parent = userDetail;
        //parent.setPassword(null);
        //return parent;
        return userDetail;
    }
}

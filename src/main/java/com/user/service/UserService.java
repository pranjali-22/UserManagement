package com.user.service;

import com.user.dao.UserDao;

import com.user.model.Userentity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<Userentity> getAllUsers() {
        return userDao.findAll();
    }
    public Userentity getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
    public String addUser(Userentity userentity) {
        userDao.save(userentity);
        return "success";
    }
    @Modifying
    public String updateUser(Userentity userentity){
        userDao.save(userentity);
        return "success";
    }
    @Transactional
    public String deleteUserByEmail(String email) {
        userDao.deleteByEmail(email);
        return "success";
    }



}

package com.user.service;

import com.user.dao.UserDao;

import com.user.model.Userentity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<Userentity> getAllUsers() {
        return userDao.findAll();
    }
}

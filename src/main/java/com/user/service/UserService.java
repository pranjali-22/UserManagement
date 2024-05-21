package com.user.service;

import com.user.dao.UserDao;

import com.user.model.Userentity;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public ResponseEntity<List<Userentity>> getAllUsers() {
        try{
            return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Userentity> getUserByEmail(String email) {
        try{
            return new ResponseEntity<>(userDao.findUserByEmail(email),HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> addUser(Userentity userentity) {
        try{
            userDao.save(userentity);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }
    @Modifying
    public String updateUser(Userentity userentity){
        userDao.save(userentity);
        return "success";
    }
    @Transactional
    public ResponseEntity<String> deleteUserByEmail(String email) {
        try{
            userDao.deleteByEmail(email);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}

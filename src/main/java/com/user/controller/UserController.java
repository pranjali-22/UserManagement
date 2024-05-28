package com.user.controller;


import com.user.exceptions.InvalidDataException;
import com.user.exceptions.UserNotFoundException;
import com.user.model.Userprofile;
import com.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<Userprofile>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("email/{email}")
    public ResponseEntity<Userprofile> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody Userprofile userProfile) {
        return userService.addUser(userProfile);
    }
    @Modifying
    @PutMapping("update/{email}")
    public String updateUser(@PathVariable String email, @RequestBody Userprofile userProfile) {
        return userService.updateUser(email, userProfile);
    }
    @Transactional
    @DeleteMapping("delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }
}

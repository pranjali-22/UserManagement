package com.user.controller;


import com.user.model.Userentity;
import com.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public List<Userentity> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("email/{email}")
    public Userentity getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @PostMapping("add")
    public String addUser(@RequestBody Userentity userentity) {
        return userService.addUser(userentity);
    }
    @Transactional
    @DeleteMapping("delete/{email}")
    public String deleteUser(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }




}

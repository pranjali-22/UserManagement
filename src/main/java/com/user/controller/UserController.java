package com.user.controller;


import com.user.model.Userentity;
import com.user.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/allUsers")
    public ResponseEntity<List<Userentity>> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("email/{email}")
    public ResponseEntity<Userentity> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @PostMapping("add")
    public ResponseEntity<String> addUser(@RequestBody Userentity userentity) {
        return userService.addUser(userentity);
    }
    @Modifying
    @PutMapping("update/email/{email}")
    public String updateEmail(@RequestBody String inputEmail,@PathVariable String email){
        Userentity ue = userService.getUserByEmail(email).getBody();
        ue.setEmail(inputEmail);
        return userService.updateUser(ue);
    }
    @PutMapping("update/name/{email}")
    public String updateName(@RequestBody String name,@PathVariable String email){
        Userentity ue = userService.getUserByEmail(email).getBody();
        ue.setName(name);
        return userService.updateUser(ue);
    }
    @PutMapping("update/dob/{email}")
    public String updateDate(@RequestBody Date date, @PathVariable String email){
        Userentity ue = userService.getUserByEmail(email).getBody();
        ue.setDob(date);
        return userService.updateUser(ue);
    }
    @PutMapping("update/phone/{email}")
    public String updatePhone(@RequestBody String phone, @PathVariable String email){
        Userentity ue = userService.getUserByEmail(email).getBody();
        ue.setPhone(phone);
        return userService.updateUser(ue);
    }
    @Transactional
    @DeleteMapping("delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        return userService.deleteUserByEmail(email);
    }
}

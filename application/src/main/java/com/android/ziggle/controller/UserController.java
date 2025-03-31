package com.android.ziggle.controller;

import com.android.ziggle.dto.UserDto;
import com.android.ziggle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firebase")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getNextUsers")
    public ResponseEntity<Object> getNextUsers(@RequestParam String lastUserKey, @RequestParam int pageSize) {
        try {
            List<UserDto> data = userService.getNextUsers(lastUserKey, pageSize);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {
        try {
            userService.addUser(userDto);
            return ResponseEntity.ok().body("User added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding user: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Object> deleteUser(@RequestParam String uid) {
        try {
            userService.deleteUser(uid);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<Object> getUserById(@RequestParam String uid) {
        try {
            UserDto data = userService.getUserById(uid);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }

    @PutMapping("/updateUserName")
    public ResponseEntity<Object> updateUserName(@RequestParam String uid, @RequestParam String name) {
        try {
            userService.updateUserName(uid, name);
            return ResponseEntity.ok().body("User name updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user name: " + e.getMessage());
        }
    }

    @PutMapping("/updateUserSwipeRightBy")
    public ResponseEntity<Object> updateUserSwipeRightBy(@RequestParam String uid, @RequestParam String rightSwipeByUid) {
        try {
            userService.updateUserSwipeRightBy(uid, rightSwipeByUid);
            return ResponseEntity.ok().body("User name updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating user name: " + e.getMessage());
        }
    }
}


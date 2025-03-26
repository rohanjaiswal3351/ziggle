package com.android.ziggle.controller;

import com.android.ziggle.dto.UserDto;
import com.android.ziggle.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firebase")
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<Object> readData() {
        try {
            List<UserDto> data = firebaseService.getAllUsers();
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }
}

package com.android.ziggle.service;

import com.android.ziggle.dto.UserDto;
import com.android.ziggle.repository.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirebaseService {

    @Autowired
    private FirebaseRepository firebaseRepository;

    public List<UserDto> getAllUsers() {
        return firebaseRepository.getAllUsers();
    }

}

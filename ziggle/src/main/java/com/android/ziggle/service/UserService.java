package com.android.ziggle.service;

import com.android.ziggle.dto.UserDto;
import com.android.ziggle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getNextUsers(String lastUserKey, int pageSize) {
        return userRepository.getNextUsers(lastUserKey, pageSize);
    }

    public void addUser(UserDto userDto) {
        userRepository.addUser(userDto);
    }

    public void deleteUser(String uid) {
        userRepository.deleteUser(uid);
    }

    public UserDto getUserById(String uid) {
        return userRepository.getUserById(uid);
    }

    public void updateUserName(String uid, String name) {
        userRepository.updateUserName(uid, name);
    }

    public void updateUserSwipeRightBy(String uid, String rightSwipeByUid) {
        userRepository.updateUserSwipeRightBy(uid, rightSwipeByUid);
    }

}

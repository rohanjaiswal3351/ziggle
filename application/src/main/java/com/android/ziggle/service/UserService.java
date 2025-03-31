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

    public void updateUserLikeNotify(String uid){
        userRepository.updateUserLikeNotify(uid);
    }

    public void updateUserCity(String uid, String city){
        userRepository.updateUserCity(uid, city);
    }

    public void updateUserGender(String uid, String gender){
        userRepository.updateUserGender(uid, gender);
    }

    public void updateUserStar(String uid, String star){
        userRepository.updateUserStar(uid, star);
    }

    public void updateUserImage(String uid, String image){
        userRepository.updateUserImage(uid, image);
    }

    public void updateUserImage1(String uid, String image){
        userRepository.updateUserImage1(uid, image);
    }

    public void deleteUserImage1(String uid){
        userRepository.deleteUserImage1(uid);
    }

    public void updateUserImage2(String uid, String image){
        userRepository.updateUserImage2(uid, image);
    }

    public void deleteUserImage2(String uid){
        userRepository.deleteUserImage2(uid);
    }

    public void updateUserImage3(String uid, String image){
        userRepository.updateUserImage3(uid, image);
    }

    public void deleteUserImage3(String uid){
        userRepository.deleteUserImage3(uid);
    }

    public void updateUserAge(String uid, String age){
        userRepository.updateUserAge(uid, age);
    }

    public void updateUserBio(String uid, String bio) {
        userRepository.updateUserBio(uid, bio);
    }

    public void updateUserSwipeRightBy(String uid, String rightSwipeByUid) {
        userRepository.updateUserSwipeRightBy(uid, rightSwipeByUid);
    }

    public void updateUserHeight(String uid, String height) {
        userRepository.updateUserHeight(uid, height);
    }

    public void updateUserExercise(String uid, String exercise) {
        userRepository.updateUserExercise(uid, exercise);
    }

    public void updateUserEducation(String uid, String education) {
        userRepository.updateUserEducation(uid, education);
    }

    public void updateUserInstaId(String uid, String instaId) {
        userRepository.updateUserInstaId(uid, instaId);
    }

    public void updateUserSnapId(String uid, String snapId) {
        userRepository.updateUserSnapId(uid, snapId);
    }

}


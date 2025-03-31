package com.android.ziggle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String uid;
    private String fcmToken;
    private String email;
    private String name;
    private String city;
    private String gender;
    private String star;
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private String age;
    private String bio;
    private ArrayList<String> rightSwipeBy;
    private String height;
    private String exercise;
    private String education;
    private String instaId;
    private String snapId;
    private ArrayList<String> matches;
    private ArrayList<String> interests;
    private ArrayList<String> interact;
    private ArrayList<String> blockedUsers;
    private String likeNotify = "No";
}

package com.android.ziggle.repository;

import com.android.ziggle.dto.UserDto;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class UserRepository {

    private final String COLLECTION_NAME = "users";
    private final DatabaseReference ref;

    public UserRepository(){
        ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
    }

    public List<UserDto> getNextUsers(String lastUserKey, int pageSize) {
        CompletableFuture<List<UserDto>> future = new CompletableFuture<>();
        List<UserDto> userList = new ArrayList<>();

        Query query;

        if (lastUserKey == null || lastUserKey.isEmpty()) {
            query = ref.orderByKey().limitToFirst(pageSize);
        } else {
            query = ref.orderByKey().startAt(lastUserKey).limitToFirst(pageSize);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserDto userDto = ds.getValue(UserDto.class);
                    userList.add(userDto);
                    System.out.println(userDto);
                }

                future.complete(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new RuntimeException("The read failed: " + databaseError.getMessage()));
            }
        });

        try {
            return future.join();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
    }

    public void addUser(UserDto userDto) {
        ApiFuture<Void> future = ref.child(userDto.getUid()).setValueAsync(userDto);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void deleteUser(String id){
        ApiFuture<Void> future = ref.child(id).removeValueAsync();
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public UserDto getUserById(String uid){
        CompletableFuture<UserDto> future = new CompletableFuture<>();
        ref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto userDto = dataSnapshot.getValue(UserDto.class);
                future.complete(userDto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new RuntimeException("The read failed: " + databaseError.getMessage()));
            }
        });

        try {
            return future.join();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }
    }

    public void updateUserName(String uid, String name) {
        ApiFuture<Void> future = ref.child(uid).child("name").setValueAsync(name);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserLikeNotify(String uid) {
        ApiFuture<Void> future = ref.child(uid).child("likeNotify").setValueAsync("No");
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserCity(String uid, String city) {
        ApiFuture<Void> future = ref.child(uid).child("city").setValueAsync(city);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserGender(String uid, String gender) {
        ApiFuture<Void> future = ref.child(uid).child("gender").setValueAsync(gender);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserStar(String uid, String star) {
        ApiFuture<Void> future = ref.child(uid).child("star").setValueAsync(star);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserImage(String uid, String image) {
        ApiFuture<Void> future = ref.child(uid).child("image").setValueAsync(image);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserImage1(String uid, String image) {
        ApiFuture<Void> future = ref.child(uid).child("image1").setValueAsync(image);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void deleteUserImage1(String uid) {
        ApiFuture<Void> future = ref.child(uid).child("image1").setValueAsync(null);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserImage2(String uid, String image) {
        ApiFuture<Void> future = ref.child(uid).child("image2").setValueAsync(image);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void deleteUserImage2(String uid) {
        ApiFuture<Void> future = ref.child(uid).child("image2").setValueAsync(null);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserImage3(String uid, String image) {
        ApiFuture<Void> future = ref.child(uid).child("image3").setValueAsync(image);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void deleteUserImage3(String uid) {
        ApiFuture<Void> future = ref.child(uid).child("image3").setValueAsync(null);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserAge(String uid, String age) {
        ApiFuture<Void> future = ref.child(uid).child("age").setValueAsync(age);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserBio(String uid, String bio) {
        ApiFuture<Void> future = ref.child(uid).child("bio").setValueAsync(bio);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserSwipeRightBy(String uid, String rightSwipeByUid, int check) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> rightSwipeByList;

                if (user.getRightSwipeBy() != null) {
                    rightSwipeByList = user.getRightSwipeBy();
                }else{
                    rightSwipeByList = new ArrayList<>();
                }

                if(check == 0){
                    rightSwipeByList.add(rightSwipeByUid);
                    user.setLikeNotify("Yes");
                }else{
                    rightSwipeByList.remove(rightSwipeByUid);
                }

                user.setRightSwipeBy(rightSwipeByList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }

    public void updateUserSwipeRightByAndMatches(String uid, String rightSwipeByUid) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> rightSwipeByList;
                ArrayList<String> matchesList;

                if (user.getRightSwipeBy() != null) {
                    rightSwipeByList = user.getRightSwipeBy();
                }else{
                    rightSwipeByList = new ArrayList<>();
                }

                if(user.getMatches() != null){
                    matchesList = user.getMatches();
                }else{
                    matchesList = new ArrayList<>();
                }

                rightSwipeByList.remove(rightSwipeByUid);
                matchesList.add(rightSwipeByUid);

                user.setRightSwipeBy(rightSwipeByList);
                user.setMatches(matchesList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }

    public void updateUserHeight(String uid, String height) {
        ApiFuture<Void> future = ref.child(uid).child("height").setValueAsync(height);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserExercise(String uid, String exercise) {
        ApiFuture<Void> future = ref.child(uid).child("exercise").setValueAsync(exercise);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserEducation(String uid, String education) {
        ApiFuture<Void> future = ref.child(uid).child("education").setValueAsync(education);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserInstaId(String uid, String instaId) {
        ApiFuture<Void> future = ref.child(uid).child("instaId").setValueAsync(instaId);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserSnapId(String uid, String snapId) {
        ApiFuture<Void> future = ref.child(uid).child("snapId").setValueAsync(snapId);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserFcmToken(String uid, String fcmToken) {
        ApiFuture<Void> future = ref.child(uid).child("fcmToken").setValueAsync(fcmToken);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        future.addListener(() -> {
            try {
                future.get();
                completableFuture.complete(null);
            } catch (Exception e) {
                completableFuture.completeExceptionally(e);
            }
        }, Runnable::run);

        completableFuture.join();
    }

    public void updateUserMatches(String uid, String matchUserUid, int check) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> matchesList;

                if (user.getMatches() != null) {
                    matchesList = user.getMatches();
                }else{
                    matchesList = new ArrayList<>();
                }

                if(check == 0){
                    matchesList.add(matchUserUid);
                }else{
                    matchesList.remove(matchUserUid);
                }

                user.setMatches(matchesList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }

    public void updateUserInterests(String uid, String interest, int check) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> interestList;

                if (user.getInterests() != null) {
                    interestList = user.getInterests();
                }else{
                    interestList = new ArrayList<>();
                }

                if(check == 0){
                    interestList.add(interest);
                }else{
                    interestList.remove(interest);
                }

                user.setInterests(interestList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }

    public void updateUserBlocked(String uid, String blockedUserUid) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> blockedList;

                if (user.getBlockedUsers() != null) {
                    blockedList = user.getBlockedUsers();
                }else{
                    blockedList = new ArrayList<>();
                }

                blockedList.add(blockedUserUid);
                user.setBlockedUsers(blockedList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }

    public void updateUserInteract(String uid, String interactUserUid, int check) {
        DatabaseReference userRef = ref.child(uid);
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserDto user = dataSnapshot.getValue(UserDto.class);
                ArrayList<String> interactList;

                if (user.getInteract() != null) {
                    interactList = user.getInteract();
                }else{
                    interactList = new ArrayList<>();
                }

                if(check == 0){
                    interactList.add(interactUserUid);
                }else{
                    interactList.remove(interactUserUid);
                }


                user.setInteract(interactList);

                ApiFuture<Void> future = userRef.setValueAsync(user);
                future.addListener(() -> {
                    try {
                        future.get();
                        completableFuture.complete(null);
                    } catch (Exception e) {
                        completableFuture.completeExceptionally(e);
                    }
                }, Runnable::run);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                completableFuture.completeExceptionally(new RuntimeException("Firebase operation cancelled: "
                        + databaseError.getMessage()));
            }
        });

        completableFuture.join();
    }
}


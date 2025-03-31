package com.android.ziggle.repository;

import com.android.ziggle.dto.UserDto;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRepository {

    private String COLLECTION_NAME = "users";

    public List<UserDto> getNextUsers(String lastUserKey, int pageSize) {
        CompletableFuture<List<UserDto>> future = new CompletableFuture<>();
        List<UserDto> userList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);

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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
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

    public void updateUserSwipeRightBy(String uid, String rightSwipeByUid) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
        DatabaseReference rightSwipeByRef = ref.child(uid).child("rightSwipeBy");
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();

        rightSwipeByRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> rightSwipeByList = null;

                if (dataSnapshot.exists()) {
                    rightSwipeByList = (List<String>) dataSnapshot.getValue();
                }

                if (rightSwipeByList == null) {
                    rightSwipeByList = new ArrayList<>();
                }

                rightSwipeByList.add(rightSwipeByUid);

                ApiFuture<Void> future = rightSwipeByRef.setValueAsync(rightSwipeByList);
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


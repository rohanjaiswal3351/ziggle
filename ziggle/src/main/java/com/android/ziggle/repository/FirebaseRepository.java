package com.android.ziggle.repository;

import com.android.ziggle.dto.UserDto;
import com.google.firebase.database.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FirebaseRepository {

    private String COLLECTION_NAME = "users";

    public List<UserDto> getAllUsers() {
        CompletableFuture<List<UserDto>> future = new CompletableFuture<>();
        List<UserDto> userDtos = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(COLLECTION_NAME);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserDto userDto = ds.getValue(UserDto.class);
                    userDtos.add(userDto);
                    System.out.println(userDto);
                }

                future.complete(userDtos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new RuntimeException("The read failed: " + databaseError.getMessage()));
            }
        });

        // Wait for the future to complete and return the result
        try {
            return future.join();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
    }
}

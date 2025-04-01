package com.android.ziggle.repository;

import com.android.ziggle.dto.MessageDto;
import com.google.api.core.ApiFuture;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;

@Repository
public class MessageRepository {

    private final String COLLECTION_NAME = "chats";
    private final DatabaseReference ref;

    public MessageRepository(){
        ref = FirebaseDatabase.getInstance().getReference(COLLECTION_NAME);
    }

    public void addNewMessageRoom(MessageDto messageDto){
        messageDto.setKey(ref.getKey());

        ApiFuture<Void> future = ref.child(messageDto.getChatId())
                                    .child(messageDto.getKey()).setValueAsync(messageDto);
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

    public MessageDto getMessageChatRoomById(String chatId, String key){
        CompletableFuture<MessageDto> future = new CompletableFuture<>();
        ref.child(chatId).child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MessageDto messageDto = dataSnapshot.getValue(MessageDto.class);
                future.complete(messageDto);
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

    public void updateIsSeen(String chatId, String key) {
        ApiFuture<Void> future = ref.child(chatId).child(key).child("isSeen").setValueAsync("true");
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
}

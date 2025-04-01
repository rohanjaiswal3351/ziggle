package com.android.ziggle.controller;

import com.android.ziggle.dto.MessageDto;
import com.android.ziggle.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/addNewMessageRoom")
    public ResponseEntity<Object> addNewMessageRoom(@RequestBody MessageDto messageDto) {
        try {
            messageService.addNewMessageRoom(messageDto);
            return ResponseEntity.ok().body("Add Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }

    @GetMapping("/getMessageChatRoomById")
    public ResponseEntity<Object> getMessageChatRoomById(@RequestParam String chatId,
                                                         @RequestParam String key){
        try {
            MessageDto messageDto = messageService.getMessageChatRoomById(chatId, key);
            return ResponseEntity.ok().body(messageDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }

    @PutMapping("/updateIsSeen")
    public ResponseEntity<Object> updateIsSeen(@RequestParam String chatId,
                                               @RequestParam String key){
        try {
            messageService.updateIsSeen(chatId, key);
            return ResponseEntity.ok().body("Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error reading data: " + e.getMessage());
        }
    }


}

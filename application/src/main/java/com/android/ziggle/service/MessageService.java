package com.android.ziggle.service;

import com.android.ziggle.dto.MessageDto;
import com.android.ziggle.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageService {

    private final MessageRepository messageRepository;

    public void addNewMessageRoom(MessageDto messageDto) {
        messageRepository.addNewMessageRoom(messageDto);
    }

    public MessageDto getMessageChatRoomById(String chatId, String key) {
        return messageRepository.getMessageChatRoomById(chatId, key);
    }

    public void updateIsSeen(String chatId, String key) {
        messageRepository.updateIsSeen(chatId, key);
    }

}

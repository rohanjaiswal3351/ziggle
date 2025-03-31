package com.android.ziggle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String chatId;
    private String key;
    private String senderId;
    private String message;
    private String currentTime;
    private String currentDate;
    private String media;
    private String isSeen;
}

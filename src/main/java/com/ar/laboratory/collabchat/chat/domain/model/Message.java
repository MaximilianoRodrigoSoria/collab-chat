package com.ar.laboratory.collabchat.chat.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Mensaje persistido de un canal. */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private UUID id;
    private UUID channelId;
    private UUID senderId;
    private String content;
    private Instant createdAt;

    public static Message create(UUID channelId, UUID senderId, String content, Instant now) {
        return Message.builder()
                .id(UUID.randomUUID())
                .channelId(channelId)
                .senderId(senderId)
                .content(content)
                .createdAt(now)
                .build();
    }
}

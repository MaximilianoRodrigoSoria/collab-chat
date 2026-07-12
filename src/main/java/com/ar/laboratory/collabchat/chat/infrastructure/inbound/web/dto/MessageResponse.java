package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Vista de un mensaje (también es el payload difundido por WebSocket). */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private UUID id;
    private UUID channelId;
    private UUID senderId;
    private String content;
    private Instant createdAt;
}

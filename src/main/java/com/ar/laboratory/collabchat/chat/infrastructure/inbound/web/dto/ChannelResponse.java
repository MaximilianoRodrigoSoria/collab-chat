package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Vista de un canal. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelResponse {
    private UUID id;
    private String name;
    private UUID createdBy;
    private Instant createdAt;
}

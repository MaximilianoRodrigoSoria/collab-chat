package com.ar.laboratory.collabchat.chat.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Canal público de conversación. */
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Channel {

    private UUID id;
    private String name;
    private UUID createdBy;
    private Instant createdAt;

    public static Channel create(String name, UUID createdBy, Instant now) {
        return Channel.builder()
                .id(UUID.randomUUID())
                .name(name)
                .createdBy(createdBy)
                .createdAt(now)
                .build();
    }
}

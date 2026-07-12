package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto;

import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Presencia: cantidad y lista de usuarios conectados. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresenceResponse {
    private int onlineCount;
    private Set<UUID> onlineUserIds;
}

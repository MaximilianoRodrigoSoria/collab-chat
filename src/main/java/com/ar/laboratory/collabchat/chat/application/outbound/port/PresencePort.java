package com.ar.laboratory.collabchat.chat.application.outbound.port;

import java.util.Set;
import java.util.UUID;

/** Puerto de salida para la presencia de usuarios conectados. */
public interface PresencePort {
    void markOnline(UUID userId);

    void markOffline(UUID userId);

    Set<UUID> onlineUserIds();
}

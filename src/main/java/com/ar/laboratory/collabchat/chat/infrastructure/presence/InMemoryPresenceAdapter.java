package com.ar.laboratory.collabchat.chat.infrastructure.presence;

import com.ar.laboratory.collabchat.chat.application.outbound.port.PresencePort;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/** Registro de presencia en memoria (single-instance). Escala con Redis Pub/Sub + TTL. */
@Component
public class InMemoryPresenceAdapter implements PresencePort {

    private final Set<UUID> online = ConcurrentHashMap.newKeySet();

    @Override
    public void markOnline(UUID userId) {
        online.add(userId);
    }

    @Override
    public void markOffline(UUID userId) {
        online.remove(userId);
    }

    @Override
    public Set<UUID> onlineUserIds() {
        return Set.copyOf(online);
    }
}

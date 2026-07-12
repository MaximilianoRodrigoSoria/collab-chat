package com.ar.laboratory.collabchat.chat.infrastructure.websocket;

import com.ar.laboratory.collabchat.chat.application.outbound.port.PresencePort;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/** Actualiza la presencia según los eventos de sesión WebSocket. */
@Component
@RequiredArgsConstructor
public class WebSocketPresenceListener {

    private final PresencePort presence;

    @EventListener
    public void onConnected(SessionConnectedEvent event) {
        userId(event.getUser()).ifPresent(presence::markOnline);
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        userId(event.getUser()).ifPresent(presence::markOffline);
    }

    private Optional<UUID> userId(Principal principal) {
        if (principal == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(UUID.fromString(principal.getName()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}

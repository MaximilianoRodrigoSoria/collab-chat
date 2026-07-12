package com.ar.laboratory.collabchat.chat.infrastructure.websocket;

import com.ar.laboratory.collabchat.chat.application.inbound.command.PostMessageCommand;
import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/** Controlador STOMP: recibe en {@code /app/channels/{channelId}/send}, persiste y difunde. */
@Controller
@RequiredArgsConstructor
public class StompMessageController {

    private final PostMessageCommand postMessageCommand;

    @MessageMapping("/channels/{channelId}/send")
    public void send(
            @DestinationVariable UUID channelId, SendMessagePayload payload, Principal principal) {
        postMessageCommand.execute(
                channelId, UUID.fromString(principal.getName()), payload.getContent());
    }
}

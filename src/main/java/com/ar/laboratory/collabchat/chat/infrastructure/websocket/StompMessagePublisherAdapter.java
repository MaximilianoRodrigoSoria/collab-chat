package com.ar.laboratory.collabchat.chat.infrastructure.websocket;

import com.ar.laboratory.collabchat.chat.application.outbound.port.MessagePublisherPort;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/** Difunde el mensaje a {@code /topic/channels/{channelId}} vía STOMP. */
@Component
@RequiredArgsConstructor
public class StompMessagePublisherAdapter implements MessagePublisherPort {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void publish(Message message) {
        MessageResponse payload =
                MessageResponse.builder()
                        .id(message.getId())
                        .channelId(message.getChannelId())
                        .senderId(message.getSenderId())
                        .content(message.getContent())
                        .createdAt(message.getCreatedAt())
                        .build();
        messagingTemplate.convertAndSend("/topic/channels/" + message.getChannelId(), payload);
    }
}

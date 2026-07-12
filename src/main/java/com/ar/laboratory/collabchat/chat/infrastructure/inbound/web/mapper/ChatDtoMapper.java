package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.mapper;

import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.ChannelResponse;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.MessageResponse;
import org.springframework.stereotype.Component;

/** Conversión a DTOs de la API de chat. */
@Component
public class ChatDtoMapper {
    public ChannelResponse toChannelResponse(Channel c) {
        return ChannelResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .createdBy(c.getCreatedBy())
                .createdAt(c.getCreatedAt())
                .build();
    }

    public MessageResponse toMessageResponse(Message m) {
        return MessageResponse.builder()
                .id(m.getId())
                .channelId(m.getChannelId())
                .senderId(m.getSenderId())
                .content(m.getContent())
                .createdAt(m.getCreatedAt())
                .build();
    }
}

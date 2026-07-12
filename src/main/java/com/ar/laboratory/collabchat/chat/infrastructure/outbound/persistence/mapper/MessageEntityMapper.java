package com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.mapper;

import com.ar.laboratory.collabchat.chat.domain.model.Message;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.entity.MessageEntity;
import org.springframework.stereotype.Component;

/** Conversión MessageEntity ↔ Message. */
@Component
public class MessageEntityMapper {
    public Message toDomain(MessageEntity e) {
        if (e == null) {
            return null;
        }
        return Message.builder()
                .id(e.getId())
                .channelId(e.getChannelId())
                .senderId(e.getSenderId())
                .content(e.getContent())
                .createdAt(e.getCreatedAt())
                .build();
    }

    public MessageEntity toEntity(Message m) {
        return MessageEntity.builder()
                .id(m.getId())
                .channelId(m.getChannelId())
                .senderId(m.getSenderId())
                .content(m.getContent())
                .createdAt(m.getCreatedAt())
                .build();
    }
}

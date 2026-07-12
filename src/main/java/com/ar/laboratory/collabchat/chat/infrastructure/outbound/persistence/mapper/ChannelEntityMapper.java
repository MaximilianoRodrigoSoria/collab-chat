package com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.mapper;

import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.entity.ChannelEntity;
import org.springframework.stereotype.Component;

/** Conversión ChannelEntity ↔ Channel. */
@Component
public class ChannelEntityMapper {
    public Channel toDomain(ChannelEntity e) {
        if (e == null) {
            return null;
        }
        return Channel.builder()
                .id(e.getId())
                .name(e.getName())
                .createdBy(e.getCreatedBy())
                .createdAt(e.getCreatedAt())
                .build();
    }

    public ChannelEntity toEntity(Channel c) {
        return ChannelEntity.builder()
                .id(c.getId())
                .name(c.getName())
                .createdBy(c.getCreatedBy())
                .createdAt(c.getCreatedAt())
                .build();
    }
}

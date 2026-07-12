package com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.adapter;

import com.ar.laboratory.collabchat.chat.application.outbound.port.MessageRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.mapper.MessageEntityMapper;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.repository.MessageJpaRepository;
import com.ar.laboratory.collabchat.shared.infrastructure.exception.InfrastructureException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/** Adaptador de persistencia de mensajes. */
@Component
@RequiredArgsConstructor
public class MessagePersistenceAdapter implements MessageRepositoryPort {

    private final MessageJpaRepository repository;
    private final MessageEntityMapper mapper;

    @Override
    public Message save(Message message) {
        try {
            return mapper.toDomain(repository.save(mapper.toEntity(message)));
        } catch (Exception e) {
            throw new InfrastructureException("Error guardando mensaje", e);
        }
    }

    @Override
    public Page<Message> findByChannel(UUID channelId, Pageable pageable) {
        return repository.findByChannelId(channelId, pageable).map(mapper::toDomain);
    }
}

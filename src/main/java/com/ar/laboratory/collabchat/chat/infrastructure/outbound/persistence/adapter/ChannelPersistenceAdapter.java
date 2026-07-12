package com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.adapter;

import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.mapper.ChannelEntityMapper;
import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.repository.ChannelJpaRepository;
import com.ar.laboratory.collabchat.shared.infrastructure.exception.InfrastructureException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/** Adaptador de persistencia de canales. */
@Component
@RequiredArgsConstructor
public class ChannelPersistenceAdapter implements ChannelRepositoryPort {

    private final ChannelJpaRepository repository;
    private final ChannelEntityMapper mapper;

    @Override
    public Channel save(Channel channel) {
        try {
            return mapper.toDomain(repository.save(mapper.toEntity(channel)));
        } catch (Exception e) {
            throw new InfrastructureException("Error guardando canal", e);
        }
    }

    @Override
    public Optional<Channel> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Channel> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }
}

package com.ar.laboratory.collabchat.chat.application.outbound.port;

import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Puerto de salida para la persistencia de canales. */
public interface ChannelRepositoryPort {
    Channel save(Channel channel);

    Optional<Channel> findById(UUID id);

    Page<Channel> findAll(Pageable pageable);
}

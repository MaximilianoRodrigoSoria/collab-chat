package com.ar.laboratory.collabchat.chat.application.outbound.port;

import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Puerto de salida para la persistencia de mensajes. */
public interface MessageRepositoryPort {
    Message save(Message message);

    Page<Message> findByChannel(UUID channelId, Pageable pageable);
}

package com.ar.laboratory.collabchat.chat.application.inbound.command;

import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Puerto de entrada: historial de un canal. */
public interface ListMessagesCommand {
    Page<Message> execute(UUID channelId, Pageable pageable);
}

package com.ar.laboratory.collabchat.chat.application.usecase;

import com.ar.laboratory.collabchat.chat.application.inbound.command.ListMessagesCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessageRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.exception.ChannelNotFoundException;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Historial de un canal. POJO sin framework. */
@RequiredArgsConstructor
public class ListMessagesUseCase implements ListMessagesCommand {

    private final ChannelRepositoryPort channels;
    private final MessageRepositoryPort messages;

    @Override
    public Page<Message> execute(UUID channelId, Pageable pageable) {
        channels.findById(channelId).orElseThrow(() -> new ChannelNotFoundException(channelId));
        return messages.findByChannel(channelId, pageable);
    }
}

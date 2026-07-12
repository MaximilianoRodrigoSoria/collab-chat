package com.ar.laboratory.collabchat.chat.application.usecase;

import com.ar.laboratory.collabchat.chat.application.inbound.command.PostMessageCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessagePublisherPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessageRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.exception.ChannelNotFoundException;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

/** Publica un mensaje en un canal: persiste y difunde en tiempo real. POJO sin framework. */
@RequiredArgsConstructor
public class PostMessageUseCase implements PostMessageCommand {

    private final ChannelRepositoryPort channels;
    private final MessageRepositoryPort messages;
    private final MessagePublisherPort publisher;

    @Override
    public Message execute(UUID channelId, UUID senderId, String content) {
        channels.findById(channelId).orElseThrow(() -> new ChannelNotFoundException(channelId));
        Message saved = messages.save(Message.create(channelId, senderId, content, Instant.now()));
        publisher.publish(saved);
        return saved;
    }
}

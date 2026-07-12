package com.ar.laboratory.collabchat.chat.application.usecase;

import com.ar.laboratory.collabchat.chat.application.inbound.command.CreateChannelCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

/** Crea un canal. POJO sin framework. */
@RequiredArgsConstructor
public class CreateChannelUseCase implements CreateChannelCommand {

    private final ChannelRepositoryPort channels;

    @Override
    public Channel execute(String name, UUID creator) {
        return channels.save(Channel.create(name, creator, Instant.now()));
    }
}

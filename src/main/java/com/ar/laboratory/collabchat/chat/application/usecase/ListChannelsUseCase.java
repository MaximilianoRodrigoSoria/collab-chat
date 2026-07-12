package com.ar.laboratory.collabchat.chat.application.usecase;

import com.ar.laboratory.collabchat.chat.application.inbound.command.ListChannelsCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Lista canales. POJO sin framework. */
@RequiredArgsConstructor
public class ListChannelsUseCase implements ListChannelsCommand {

    private final ChannelRepositoryPort channels;

    @Override
    public Page<Channel> execute(Pageable pageable) {
        return channels.findAll(pageable);
    }
}

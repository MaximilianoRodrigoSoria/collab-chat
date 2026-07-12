package com.ar.laboratory.collabchat.chat.infrastructure.config;

import com.ar.laboratory.collabchat.chat.application.inbound.command.CreateChannelCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.ListChannelsCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.ListMessagesCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.PostMessageCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessagePublisherPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessageRepositoryPort;
import com.ar.laboratory.collabchat.chat.application.usecase.CreateChannelUseCase;
import com.ar.laboratory.collabchat.chat.application.usecase.ListChannelsUseCase;
import com.ar.laboratory.collabchat.chat.application.usecase.ListMessagesUseCase;
import com.ar.laboratory.collabchat.chat.application.usecase.PostMessageUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Wiring de los casos de uso del chat. */
@Configuration
public class ChatConfig {

    @Bean
    public CreateChannelCommand createChannelCommand(ChannelRepositoryPort channels) {
        return new CreateChannelUseCase(channels);
    }

    @Bean
    public ListChannelsCommand listChannelsCommand(ChannelRepositoryPort channels) {
        return new ListChannelsUseCase(channels);
    }

    @Bean
    public PostMessageCommand postMessageCommand(
            ChannelRepositoryPort channels,
            MessageRepositoryPort messages,
            MessagePublisherPort publisher) {
        return new PostMessageUseCase(channels, messages, publisher);
    }

    @Bean
    public ListMessagesCommand listMessagesCommand(
            ChannelRepositoryPort channels, MessageRepositoryPort messages) {
        return new ListMessagesUseCase(channels, messages);
    }
}

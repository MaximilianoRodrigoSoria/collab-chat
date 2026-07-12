package com.ar.laboratory.collabchat.chat.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ar.laboratory.collabchat.chat.application.outbound.port.ChannelRepositoryPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessagePublisherPort;
import com.ar.laboratory.collabchat.chat.application.outbound.port.MessageRepositoryPort;
import com.ar.laboratory.collabchat.chat.domain.exception.ChannelNotFoundException;
import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Casos de uso de chat")
class ChatUseCasesTest {

    @Mock private ChannelRepositoryPort channels;
    @Mock private MessageRepositoryPort messages;
    @Mock private MessagePublisherPort publisher;

    @Test
    @DisplayName("create canal delega en el repositorio")
    void createChannel() {
        when(channels.save(any(Channel.class))).thenAnswer(inv -> inv.getArgument(0));
        Channel c = new CreateChannelUseCase(channels).execute("general", UUID.randomUUID());
        assertThat(c.getName()).isEqualTo("general");
    }

    @Test
    @DisplayName("post message persiste y difunde")
    void postMessage() {
        UUID channelId = UUID.randomUUID();
        when(channels.findById(channelId))
                .thenReturn(Optional.of(Channel.builder().id(channelId).build()));
        when(messages.save(any(Message.class))).thenAnswer(inv -> inv.getArgument(0));

        Message m =
                new PostMessageUseCase(channels, messages, publisher)
                        .execute(channelId, UUID.randomUUID(), "hola");

        assertThat(m.getContent()).isEqualTo("hola");
        verify(publisher).publish(any(Message.class));
    }

    @Test
    @DisplayName("post en canal inexistente → ChannelNotFound y no difunde")
    void postMissingChannel() {
        UUID channelId = UUID.randomUUID();
        when(channels.findById(channelId)).thenReturn(Optional.empty());
        assertThatThrownBy(
                        () ->
                                new PostMessageUseCase(channels, messages, publisher)
                                        .execute(channelId, UUID.randomUUID(), "x"))
                .isInstanceOf(ChannelNotFoundException.class);
        verify(publisher, never()).publish(any());
    }
}

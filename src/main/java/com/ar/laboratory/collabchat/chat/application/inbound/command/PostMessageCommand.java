package com.ar.laboratory.collabchat.chat.application.inbound.command;

import com.ar.laboratory.collabchat.chat.domain.model.Message;
import java.util.UUID;

/** Puerto de entrada: publicar un mensaje (persiste y difunde). */
public interface PostMessageCommand {
    Message execute(UUID channelId, UUID senderId, String content);
}

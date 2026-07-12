package com.ar.laboratory.collabchat.chat.application.inbound.command;

import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import java.util.UUID;

/** Puerto de entrada: crear un canal. */
public interface CreateChannelCommand {
    Channel execute(String name, UUID creator);
}

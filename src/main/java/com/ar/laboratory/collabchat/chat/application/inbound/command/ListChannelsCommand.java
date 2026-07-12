package com.ar.laboratory.collabchat.chat.application.inbound.command;

import com.ar.laboratory.collabchat.chat.domain.model.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Puerto de entrada: listar canales. */
public interface ListChannelsCommand {
    Page<Channel> execute(Pageable pageable);
}

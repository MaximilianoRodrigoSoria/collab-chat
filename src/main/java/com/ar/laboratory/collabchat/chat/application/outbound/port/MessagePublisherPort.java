package com.ar.laboratory.collabchat.chat.application.outbound.port;

import com.ar.laboratory.collabchat.chat.domain.model.Message;

/** Puerto de salida para difundir un mensaje en tiempo real. */
public interface MessagePublisherPort {
    void publish(Message message);
}

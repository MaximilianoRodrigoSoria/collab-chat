package com.ar.laboratory.collabchat.chat.domain.exception;

import java.util.UUID;

/** No se encontró el canal. */
public class ChannelNotFoundException extends RuntimeException {
    public ChannelNotFoundException(UUID id) {
        super("Canal no encontrado: " + id);
    }
}

package com.ar.laboratory.collabchat.chat.infrastructure.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Payload STOMP para enviar un mensaje a un canal. */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendMessagePayload {
    private String content;
}

package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Envío de mensaje. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMessageRequest {
    @NotBlank(message = "El contenido es obligatorio")
    @Size(max = 4000)
    private String content;
}

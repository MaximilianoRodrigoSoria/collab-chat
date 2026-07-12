package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Alta de canal. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150)
    private String name;
}

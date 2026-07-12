package com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.controller;

import com.ar.laboratory.collabchat.chat.application.inbound.command.CreateChannelCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.ListChannelsCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.ListMessagesCommand;
import com.ar.laboratory.collabchat.chat.application.inbound.command.PostMessageCommand;
import com.ar.laboratory.collabchat.chat.application.outbound.port.PresencePort;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.ChannelResponse;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.CreateChannelRequest;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.MessageResponse;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.PostMessageRequest;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.dto.PresenceResponse;
import com.ar.laboratory.collabchat.chat.infrastructure.inbound.web.mapper.ChatDtoMapper;
import com.ar.laboratory.collabchat.shared.infrastructure.web.dto.PageResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** API REST de canales y mensajes. La identidad llega en {@code X-User-Id}. */
@Tag(name = "Channels", description = "Canales, mensajes y presencia")
@RestController
@RequestMapping("/api/v1/channels")
@RequiredArgsConstructor
@RateLimiter(name = "channels-api")
public class ChannelController {

    private static final String USER_HEADER = "X-User-Id";

    private final CreateChannelCommand createChannelCommand;
    private final ListChannelsCommand listChannelsCommand;
    private final PostMessageCommand postMessageCommand;
    private final ListMessagesCommand listMessagesCommand;
    private final PresencePort presence;
    private final ChatDtoMapper mapper;

    @PostMapping
    public ResponseEntity<ChannelResponse> create(
            @RequestHeader(USER_HEADER) UUID userId,
            @Valid @RequestBody CreateChannelRequest request) {
        var channel = createChannelCommand.execute(request.getName(), userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toChannelResponse(channel));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ChannelResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        var result =
                listChannelsCommand
                        .execute(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                        .map(mapper::toChannelResponse);
        return ResponseEntity.ok(PageResponse.of(result));
    }

    @PostMapping("/{id}/messages")
    public ResponseEntity<MessageResponse> postMessage(
            @PathVariable UUID id,
            @RequestHeader(USER_HEADER) UUID userId,
            @Valid @RequestBody PostMessageRequest request) {
        var message = postMessageCommand.execute(id, userId, request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toMessageResponse(message));
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<PageResponse<MessageResponse>> messages(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        var result =
                listMessagesCommand
                        .execute(id, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")))
                        .map(mapper::toMessageResponse);
        return ResponseEntity.ok(PageResponse.of(result));
    }

    @GetMapping("/presence")
    public ResponseEntity<PresenceResponse> presence() {
        Set<UUID> online = presence.onlineUserIds();
        return ResponseEntity.ok(new PresenceResponse(online.size(), online));
    }
}

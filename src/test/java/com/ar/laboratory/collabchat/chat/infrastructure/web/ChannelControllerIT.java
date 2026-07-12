package com.ar.laboratory.collabchat.chat.infrastructure.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/** Tests de integración de la API REST de chat con PostgreSQL real. */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.jpa.hibernate.ddl-auto=validate"})
@ActiveProfiles("test")
@Testcontainers(disabledWithoutDocker = true)
@DisplayName("ChannelController - Integration Tests")
class ChannelControllerIT {

    private static final String BASE = "/collab-chat/api/v1/channels";

    @Container @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @LocalServerPort private int port;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
    }

    private UUID createChannel(UUID user) throws Exception {
        byte[] bytes =
                client.post()
                        .uri(BASE)
                        .header("X-User-Id", user.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("name", "general"))
                        .exchange()
                        .expectStatus()
                        .isCreated()
                        .expectBody()
                        .returnResult()
                        .getResponseBodyContent();
        return UUID.fromString(objectMapper.readTree(bytes).get("id").asText());
    }

    @Test
    @DisplayName("crear canal → postear → historial")
    void flow() throws Exception {
        UUID user = UUID.randomUUID();
        UUID channelId = createChannel(user);

        client.post()
                .uri(BASE + "/" + channelId + "/messages")
                .header("X-User-Id", user.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("content", "hola mundo"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.content")
                .isEqualTo("hola mundo");

        client.get()
                .uri(BASE + "/" + channelId + "/messages")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.totalElements")
                .isEqualTo(1);
    }

    @Test
    @DisplayName("postear en canal inexistente → 404")
    void postMissing() {
        client.post()
                .uri(BASE + "/" + UUID.randomUUID() + "/messages")
                .header("X-User-Id", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("content", "x"))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    @DisplayName("presencia → 200 con contador")
    void presence() {
        client.get()
                .uri(BASE + "/presence")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.onlineCount")
                .isEqualTo(0);
    }

    @Test
    @DisplayName("crear canal sin nombre → 400")
    void invalid() {
        client.post()
                .uri(BASE)
                .header("X-User-Id", UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("name", ""))
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}

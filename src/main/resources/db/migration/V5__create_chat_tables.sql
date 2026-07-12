CREATE TABLE IF NOT EXISTS app.channels (
    id         UUID         PRIMARY KEY,
    name       VARCHAR(150) NOT NULL,
    created_by UUID         NOT NULL,
    created_at TIMESTAMP    NOT NULL
);
CREATE TABLE IF NOT EXISTS app.messages (
    id         UUID      PRIMARY KEY,
    channel_id UUID      NOT NULL REFERENCES app.channels(id) ON DELETE CASCADE,
    sender_id  UUID      NOT NULL,
    content    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_messages_channel ON app.messages(channel_id, created_at);
COMMENT ON TABLE app.channels IS 'Canales de conversacion';
COMMENT ON TABLE app.messages IS 'Mensajes persistidos por canal';

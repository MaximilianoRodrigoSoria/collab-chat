package com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.repository;

import com.ar.laboratory.collabchat.chat.infrastructure.outbound.persistence.entity.ChannelEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repositorio JPA de canales. */
public interface ChannelJpaRepository extends JpaRepository<ChannelEntity, UUID> {}

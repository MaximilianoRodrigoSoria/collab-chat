package com.ar.laboratory.collabchat.example.application.inbound.command;

import com.ar.laboratory.collabchat.example.domain.model.Example;

/** Puerto de entrada para crear un Example */
public interface CreateExampleCommand {

    Example execute(Example example);
}

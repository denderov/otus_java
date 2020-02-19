package ru.otus.web.messagesystem;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Optional;

public interface RequestHandler {
  Optional<Message> handle(Message msg) throws JsonProcessingException;
}

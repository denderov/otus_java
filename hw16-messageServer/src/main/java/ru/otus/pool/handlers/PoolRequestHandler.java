package ru.otus.pool.handlers;

import java.util.Optional;
import lombok.SneakyThrows;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.pool.PoolService;


public class PoolRequestHandler implements RequestHandler {
  private final PoolService poolService;

  public PoolRequestHandler(PoolService poolService) {
    this.poolService = poolService;
  }

  @SneakyThrows
  @Override
  public Optional<Message> handle(Message msg) {
    return Optional
        .of(new Message(msg.getFrom(), poolService.pollNextDbServiceClient(), msg.getId(),
            msg.getType(), msg.getPayload()));
  }
}

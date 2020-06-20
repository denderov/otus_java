package ru.otus.front;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.MsClient;

@Service
public class FrontendServiceImpl implements FrontendService {
  private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

  private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
  private final MsClient msClient;
  private final String databaseServiceClientName;

  public FrontendServiceImpl(@Lazy @Qualifier("frontendMsClient") MsClient msClient, @Value("databaseService") String databaseServiceClientName) {
    this.msClient = msClient;
    this.databaseServiceClientName = databaseServiceClientName;
  }

  @Override
  public void getAllUserData( Consumer<String> dataConsumer) {
    Message outMsg = msClient.produceMessage(databaseServiceClientName, null, MessageType.ALL_USERS_DATA);
    consumerMap.put(outMsg.getId(), dataConsumer);
    msClient.sendMessage(outMsg);
  }

  @Override
  public void createUser(String userJson, Consumer<Long> dataConsumer) {
    Message outMsg = msClient.produceMessage(databaseServiceClientName, userJson, MessageType.USER_DATA);
    consumerMap.put(outMsg.getId(), dataConsumer);
    msClient.sendMessage(outMsg);
  }

  @Override
  public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
    Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
    if (consumer == null) {
      logger.warn("consumer not found for:{}", sourceMessageId);
      return Optional.empty();
    }
    return Optional.of(consumer);
  }
}

package ru.otus.pool;

import static java.lang.Thread.sleep;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.springframework.stereotype.Service;

@Service
public class PoolServiceImpl implements PoolService {

  private final ConcurrentLinkedQueue<String> dbPool;

  PoolServiceImpl() {
    this.dbPool = new ConcurrentLinkedQueue<>();
    offerDbServiceClient("databaseService1");
    offerDbServiceClient("databaseService2");
  }

  @Override
  public String pollNextDbServiceClient() {
    while (dbPool.isEmpty()) {
      try {
        sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    String nextDbServiceClient = dbPool.poll();
    offerDbServiceClient(nextDbServiceClient);
    return nextDbServiceClient;
  }

  @Override
  public void offerDbServiceClient(String clientName) {
    dbPool.offer(clientName);
  }


}

package ru.otus.pool;

public interface PoolService {

  String pollNextDbServiceClient();

  void offerDbServiceClient(String clientName);

}

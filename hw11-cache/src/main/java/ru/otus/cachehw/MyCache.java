package ru.otus.cachehw;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {

  private final Map<K, V> cache;
  private final Queue<K> keysOrderedByInsertion;
  private int limit;

  public MyCache(int limit) {
    this.limit = limit;
    this.cache = new HashMap<>();
    this.keysOrderedByInsertion = new LinkedList<>();
  }

  public MyCache() {
    this.limit = 10;
    this.cache = new HashMap<>();
    this.keysOrderedByInsertion = new LinkedList<>();
  }

  @Override
  public void put(K key, V value) {
    compactCache(limit - 1);
    cache.put(key, value);
    keysOrderedByInsertion.add(key);
  }

  @Override
  public void remove(K key) {
    cache.remove(key);
  }

  @Override
  public V get(K key) {
    return cache.get(key);
  }

  @Override
  public void addListener(HwListener listener) {

  }

  @Override
  public void removeListener(HwListener listener) {

  }

  private void compactCache(int sizeToCompact) {
    while (cache.size() > sizeToCompact) {
      K keyForRemove = keysOrderedByInsertion.poll();
      cache.remove(keyForRemove);
    }
  }

  public int getActualSize() {
    return cache.size();
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

}

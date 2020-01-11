package ru.otus.cachehw;

import java.lang.ref.WeakReference;
import java.util.*;

/**
 * @author sergey
 * created on 14.12.18.
 * modified by Alexander Fedorov
 */
public class MyCache<K, V> implements HwCache<K, V> {

  private final Set<WeakReference<HwListener<K,V>>> listeners;
  private final Map<K, V> cache;
  private final Queue<K> keysOrderedByInsertion;
  private int limit;
  
  public MyCache(int limit) {
    this.limit = limit;
    this.cache = new HashMap<>();
    this.keysOrderedByInsertion = new LinkedList<>();
    this.listeners =  Collections.newSetFromMap(
            new WeakHashMap<>());
  }

  public MyCache() {
    this(10);
  }

  @Override
  public void put(K key, V value) {
    compactCache(limit - 1);
    cache.put(key, value);
    keysOrderedByInsertion.add(key);
    notifyEach(key, value, "PUT");
  }

  @Override
  public void remove(K key) {
    V value = cache.remove(key);
    keysOrderedByInsertion.remove(key);
    notifyEach(key, value, "REMOVE");
  }

  @Override
  public V get(K key) {
    V value = cache.get(key);
    notifyEach(key, value, "GET");
    return value;
  }

  @Override
  public void addListener(HwListener listener) {
    listeners.add(new WeakReference<HwListener<K, V>>(listener));
  }

  @Override
  public void removeListener(HwListener listener) {
    for (WeakReference<HwListener<K, V>> listenerRef :
            listeners) {
      if (listener == listenerRef.get()) {
        listeners.remove(listenerRef);
      }
    }
  }

  private void compactCache(int sizeToCompact) {
    while (cache.size() > sizeToCompact) {
      K keyForRemove = keysOrderedByInsertion.poll();
      cache.remove(keyForRemove);
    }
  }

  private void notifyEach(K key, V value, String action) {
    for (WeakReference<HwListener<K, V>> listener :
            listeners) {
      Objects.requireNonNull(listener.get()).notify(key, value, action);
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

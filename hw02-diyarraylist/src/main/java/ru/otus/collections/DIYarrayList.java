package ru.otus.collections;

import java.util.*;
import java.util.function.Consumer;

public class DIYarrayList<E> implements List<E> {

    private int size;

    private int capacity;

    private int prevCapacity;

    private static final int DEFAULT_PREV_CAPACITY = 5;

    private static final int DEFAULT_CAPACITY = 8;

    private E[] data;

    public DIYarrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DIYarrayList(int initialCapacity) {
        this.size = 0;
        this.prevCapacity = DEFAULT_PREV_CAPACITY;
        if (initialCapacity > 0) {
            this.data = (E[]) new Object[initialCapacity];
            this.capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.data = (E[]) new Object[DEFAULT_CAPACITY];
            this.capacity = DEFAULT_CAPACITY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        size++;
        if (size == data.length)
            data = grow();
        data[size-1] = e;
        return true;
    }

    private E[] grow() {
        capacity += prevCapacity;
        prevCapacity = capacity - prevCapacity;
        return data = Arrays.copyOf(data,
                capacity);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: "+index);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);
        return new ListItr(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DIYarrayList<?> that = (DIYarrayList<?>) o;
        return size == that.size &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int hash = hashCodeRange(0, size);
        return hash;
    }

    int hashCodeRange(int from, int to) {
        final Object[] es = data;
        int hashCode = 1;
        for (int i = from; i < to; i++) {
            Object e = es[i];
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    private class Itr implements Iterator<E> {
        int cursor;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = DIYarrayList.this.data;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            DIYarrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {

        }
    }

    private class ListItr extends Itr implements ListIterator<E> {
        public ListItr(int i) {
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}

package Framewok.Collection.Queue;

import Framewok.Collection.List.LinkedList;

public class MyQueue<E> implements Queue<E>{
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public boolean offer(E e) {
        return list.add(e);
    }

    @Override
    public E poll() {
        if (list.isEmpty()) return null;
        return list.remove(0);
    }

    @Override
    public E remove() {
        if (list.isEmpty()) throw new RuntimeException("Queue is empty");
        return list.remove(0);
    }

    @Override
    public E peek() {
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    @Override
    public E element() {
        if (list.isEmpty()) throw new RuntimeException("Queue is empty");
        return list.get(0);
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public boolean contains(E e) { return list.contains(e); }

    @Override
    public boolean add(E e) { return offer(e); }

    @Override
    public boolean remove(E e) { return list.remove(e); }

    @Override
    public void clearAll() { list.clearAll(); }

    @Override
    public E[] toArray() { return list.toArray(); }
}

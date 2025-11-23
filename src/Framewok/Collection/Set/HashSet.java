package Framewok.Collection.Set;

public class HashSet<E> implements Set<E>{
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private Node<E>[] buckets;
    private int size = 0;
    private int threshold;
    private static class Node<E> {
        E value;
        Node<E> next;
        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
    public HashSet() {
        buckets = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }
    @Override
    public boolean add(E e) {
        if (contains(e)) return false;
        int index = hash(e);
        Node<E> newNode = new Node<>(e, buckets[index]);
        buckets[index] = newNode;
        size++;
        if (size > threshold) resize();
        return true;
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e);
        Node<E> curr = buckets[index];
        while (curr != null) {
            if (equals(e, curr.value)) return true;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        int index = hash(e);
        Node<E> curr = buckets[index];
        Node<E> prev = null;
        while (curr != null) {
            if (equals(e, curr.value)) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }
    @Override
    public void clearAll() {
        buckets = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public E[] toArray() {
        Object[] arr = new Object[size];
        int idx = 0;
        for (Node<E> head : buckets) {
            Node<E> curr = head;
            while (curr != null) {
                arr[idx++] = curr.value;
                curr = curr.next;
            }
        }
        return (E[]) arr;
    }

    private int hash(E e) {
        int h = (e == null) ? 0 : e.hashCode();
        return (h & 0x7fffffff) % buckets.length; // positive mod
    }

    private boolean equals(E a, E b) {
        return (a == b) || (a != null && a.equals(b));
    }

    private void resize() {
        Node<E>[] oldBuckets = buckets;
        int newCapacity = oldBuckets.length * 2;
        Node<E>[] newBuckets = (Node<E>[]) new Node[newCapacity];
        for (Node<E> head : oldBuckets) {
            Node<E> curr = head;
            while (curr != null) {
                Node<E> next = curr.next;
                int index = (curr.value == null ? 0 :
                        (curr.value.hashCode() & 0x7fffffff) % newCapacity);
                curr.next = newBuckets[index];
                newBuckets[index] = curr;
                curr = next;
            }
        }
        buckets = newBuckets;
        threshold = (int)(newCapacity * LOAD_FACTOR);
    }
}

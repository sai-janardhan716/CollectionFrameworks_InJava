package Framework.Collection.Set;

public class LinkedHashSet<E> implements Set<E> {
    private static class Node<E> {
        E value;
        Node<E> bucketNext;
        Node<E> before;
        Node<E> after;
        Node(E value) {
            this.value = value;
        }
    }
    private Node<E>[] buckets;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    private int threshold;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public LinkedHashSet() {
        buckets = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private int hash(E e) {
        int h = (e == null) ? 0 : e.hashCode();
        return (h & 0x7fffffff) % buckets.length;
    }

    private boolean equals(E a, E b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public boolean add(E e) {
        int index = hash(e);
        Node<E> curr = buckets[index];
        while (curr != null) {
            if (equals(e, curr.value)) return false;
            curr = curr.bucketNext;
        }

        Node<E> newNode = new Node<>(e);
        newNode.bucketNext = buckets[index];
        buckets[index] = newNode;
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.after = newNode;
            newNode.before = tail;
            tail = newNode;
        }
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
            curr = curr.bucketNext;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        int index = hash(e);

        // Remove from hash bucket chain
        Node<E> curr = buckets[index];
        Node<E> prevBucket = null;

        while (curr != null) {
            if (equals(e, curr.value)) {
                if (prevBucket == null)
                    buckets[index] = curr.bucketNext;
                else
                    prevBucket.bucketNext = curr.bucketNext;
                unlinkFromList(curr);
                size--;
                return true;
            }
            prevBucket = curr;
            curr = curr.bucketNext;
        }
        return false;
    }

    private void unlinkFromList(Node<E> node) {
        Node<E> before = node.before;
        Node<E> after = node.after;
        if (before == null) head = after;
        else before.after = after;
        if (after == null) tail = before;
        else after.before = before;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldBuckets = buckets;
        int newCapacity = oldBuckets.length * 2;
        buckets = (Node<E>[]) new Node[newCapacity];
        threshold = (int)(newCapacity * LOAD_FACTOR);
        Node<E> curr = head;
        while (curr != null) {
            int index = (curr.value == null ? 0 :
                    (curr.value.hashCode() & 0x7fffffff) % newCapacity);
            curr.bucketNext = buckets[index];
            buckets[index] = curr;
            curr = curr.after;
        }
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clearAll() {
        head = tail = null;
        size = 0;
        Node<E>[] newBuckets = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        buckets = newBuckets;
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    @Override
    public E[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        Node<E> curr = head;
        while (curr != null) {
            arr[i++] = curr.value;
            curr = curr.after;
        }
        return (E[]) arr;
    }
}
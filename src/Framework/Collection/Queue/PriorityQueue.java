package Framework.Collection.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityQueue<E> implements Queue<E>{
    private Object[] heap;
    private int size = 0;
    private Comparator<? super E> comparator;

    public PriorityQueue() {
        this(11, null);
    }

    public PriorityQueue(int initialCapacity) {
        this(initialCapacity, null);
    }

    public PriorityQueue(Comparator<? super E> comparator) {
        this(11, comparator);
    }

    public PriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        heap = new Object[initialCapacity];
        this.comparator = comparator;
    }

    @Override
    public boolean offer(E e) {
        if (size == heap.length) grow();
        heap[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) return null;
        E root = (E) heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        siftDown(0);
        return root;
    }

    @Override
    public E remove() {
        E result = poll();
        if (result == null) throw new RuntimeException("PriorityQueue is empty");
        return result;
    }

    @Override
    public E peek() {
        return (size == 0) ? null : (E) heap[0];
    }

    @Override
    public E element() {
        if (size == 0) throw new RuntimeException("PriorityQueue is empty");
        return (E) heap[0];
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(heap[i])) return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) { return offer(e); }

    @Override
    public boolean remove(E e) {
        for (int i = 0; i < size; i++) {
            if (heap[i].equals(e)) {
                heap[i] = heap[size - 1];
                heap[size - 1] = null;
                size--;
                siftDown(i);
                siftUp(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearAll() {
        Arrays.fill(heap, 0, size, null);
        size = 0;
    }

    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(heap, size);
    }

    private void grow() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    private void siftUp(int index) {
        Object target = heap[index];
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (compare((E) target, (E) heap[parent]) >= 0) break;
            heap[index] = heap[parent];
            index = parent;
        }
        heap[index] = target;
    }

    private void siftDown(int index) {
        Object target = heap[index];
        int half = size / 2;
        while (index < half) {
            int left = index * 2 + 1;
            int right = left + 1;
            int smallest = left;
            if (right < size && compare((E) heap[right], (E) heap[left]) < 0) {
                smallest = right;
            }
            if (compare((E) heap[smallest], (E) target) >= 0) break;
            heap[index] = heap[smallest];
            index = smallest;
        }
        heap[index] = target;
    }

    private int compare(E a, E b) {
        if (comparator != null) return comparator.compare(a, b);
        return ((Comparable<? super E>) a).compareTo(b);
    }
}

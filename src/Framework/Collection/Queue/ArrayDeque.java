package Framework.Collection.Queue;
import java.util.Arrays;
public class ArrayDeque<E> implements Deque<E>{
    private Object[] elements;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    public ArrayDeque() {
        elements = new Object[8];
    }
    private void grow() {
        int newCap = elements.length * 2;
        Object[] newArr = new Object[newCap];
        for (int i = 0; i < size; i++) {
            newArr[i] = elements[(head + i) % elements.length];
        }
        elements = newArr;
        head = 0;
        tail = size;
    }

    @Override
    public void addFirst(E e) {
        if (size == elements.length) grow();
        head = (head - 1 + elements.length) % elements.length;
        elements[head] = e;
        size++;
    }

    @Override
    public void addLast(E e) {
        if (size == elements.length) grow();
        elements[tail] = e;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public boolean offerFirst(E e) { addFirst(e); return true; }

    @Override
    public boolean offerLast(E e) { addLast(e); return true; }

    @Override
    public E removeFirst() {
        if (size == 0) throw new RuntimeException("Deque empty");
        E e = (E) elements[head];
        head = (head + 1) % elements.length;
        size--;
        return e;
    }

    @Override
    public E removeLast() {
        if (size == 0) throw new RuntimeException("Deque empty");
        tail = (tail - 1 + elements.length) % elements.length;
        E e = (E) elements[tail];
        size--;
        return e;
    }

    @Override
    public E pollFirst() { return size == 0 ? null : removeFirst(); }

    @Override
    public E pollLast() { return size == 0 ? null : removeLast(); }

    @Override
    public E getFirst() {
        if (size == 0) throw new RuntimeException("Deque empty");
        return (E) elements[head];
    }

    @Override
    public E getLast() {
        if (size == 0) throw new RuntimeException("Deque empty");
        int idx = (tail - 1 + elements.length) % elements.length;
        return (E) elements[idx];
    }

    @Override
    public E peekFirst() { return size == 0 ? null : (E) elements[head]; }

    @Override
    public E peekLast() {
        if (size == 0) return null;
        int idx = (tail - 1 + elements.length) % elements.length;
        return (E) elements[idx];
    }

    @Override
    public boolean offer(E e) { return offerLast(e); }

    @Override
    public E poll() { return pollFirst(); }

    @Override
    public E remove() { return removeFirst(); }

    @Override
    public E peek() { return peekFirst(); }

    @Override
    public E element() { return getFirst(); }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty(){ return size == 0; }

    @Override
    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (elements[(head + i) % elements.length].equals(e)) return true;
        }
        return false;
    }

    @Override
    public boolean add(E e){ addLast(e); return true; }

    @Override
    public boolean remove(E e){
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearAll() {
        Arrays.fill(elements, null);
        head = tail = size = 0;
    }

    @Override
    public E[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = elements[(head + i) % elements.length];
        }
        return (E[]) arr;
    }
}

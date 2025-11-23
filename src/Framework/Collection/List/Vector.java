package Framework.Collection.List;
import java.util.Arrays;

public class Vector<E> implements List<E> {
    private Object[] elements;
    private int size;
    public Vector() {
        elements = new Object[10];
    }
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = List.manualCopyOf(elements, newCapacity);
        }
    }
    @Override
    public boolean add(E e) {
        ensureCapacity();
        elements[size++] = e;
        return true;
    }
    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }
    @Override
    public synchronized E remove(int index) {
        rangeCheck(index);
        E removed = (E) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }
    @Override
    public synchronized boolean remove(E e) {
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }
    @Override
    public synchronized int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elements[i] == null) return i;
            } else {
                if (element.equals(elements[i])) return i;
            }
        }
        return -1;
    }
    @Override
    public synchronized int size() {
        return size;
    }
    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }
    @Override
    public synchronized boolean contains(E e) {
        return indexOf(e) != -1;
    }
    @Override
    public synchronized void clearAll() {
        Arrays.fill(elements, null);
        size = 0;
    }
    @Override
    public synchronized E[] toArray() {
        return (E[]) Arrays.copyOf(elements, size);
    }
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }
}

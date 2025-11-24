package Framework.Collection.List;
import java.util.Arrays;

public class ArrayList<E> implements List<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;
    public ArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
    }
    public ArrayList(int size){
        elements = new Object[size];
    }
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = List.manualCopyOf(elements, newCapacity);
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
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }
    @Override
    public boolean add(E e) {
        ensureCapacity();
        elements[size++] = e;
        return true;
    }
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index+1, size-index);
        elements[index] = element;
        size++;
    }
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E old = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index+1, elements, index, numMoved);
        }
        elements[--size] = null;
        return old;
    }
    @Override
    public boolean remove(E e) {
        int idx = indexOf(e);
        if (idx >= 0) {
            remove(idx);
            return true;
        }
        return false;
    }
    @Override
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elements, size, elements.getClass());
    }
    @Override
    public E get(int index) {
        rangeCheck(index);
        E e = (E) elements[index];
        return e;
    }
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E old = (E) elements[index];
        elements[index] = element;
        return old;
    }
    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return -1;
    }
    @Override
    public void clearAll() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
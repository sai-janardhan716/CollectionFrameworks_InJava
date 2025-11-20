package Framewok.Collection.List;

public class LinkedList<E> implements List<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        Node(E data) {
            this.data = data;
        }
    }
    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>(e);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndexForAdd(index);
        if (index == size) {
            add(element);
            return;
        }
        Node<E> node = new Node<>(element);
        Node<E> curr = getNode(index);
        Node<E> prevNode = curr.prev;
        node.next = curr;
        curr.prev = node;
        if (prevNode != null)
            prevNode.next = node;
        else
            head = node;
        node.prev = prevNode;
        size++;
    }
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    private void checkIndex(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).data;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = getNode(index);
        E old = node.data;
        node.data = element;
        return old;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> node = getNode(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if (prevNode != null) prevNode.next = nextNode;
        else head = nextNode;
        if (nextNode != null) nextNode.prev = prevNode;
        else tail = prevNode;
        size--;
        return node.data;
    }

    @Override
    public boolean remove(E e) {
        Node<E> curr = head;
        while (curr != null) {
            if (e == curr.data || (e != null && e.equals(curr.data))) {
                Node<E> prevNode = curr.prev;
                Node<E> nextNode = curr.next;
                if (prevNode != null) prevNode.next = nextNode;
                else head = nextNode;
                if (nextNode != null) nextNode.prev = prevNode;
                else tail = prevNode;
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public int indexOf(E element) {
        Node<E> curr = head;
        int idx = 0;
        while (curr != null) {
            if (element == curr.data || (element != null && element.equals(curr.data))) {
                return idx;
            }
            curr = curr.next;
            idx++;
        }
        return -1;
    }

    private Node<E> getNode(int index) {
        Node<E> curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) curr = curr.next;
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) curr = curr.prev;
        }
        return curr;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean contains(E e) { return indexOf(e) != -1; }

    @Override
    public void clearAll() {
        head = tail = null;
        size = 0;
    }
    @Override
    public E[] toArray() {
        Object[] arr = new Object[size];
        Node<E> curr = head;
        int i = 0;
        while (curr != null) {
            arr[i++] = curr.data;
            curr = curr.next;
        }
        return (E[]) arr;
    }
}

package Framewok;
public interface Collection<E>{
        int size();
        boolean isEmpty();
        boolean contains(E e);
        boolean add(E e);
        boolean remove(E e);
        void clear();
        E[] toArray();
}
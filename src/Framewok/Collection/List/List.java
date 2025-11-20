package Framewok.Collection.List;

import Framewok.Collection.Collection;

// List is a first main Interface inherits the Collection interface
/*
    Collection
    |__List (List interface extends Collection Interface)
         |__ArrayList (ArrayList Class Implements List Interface)
         |__Vector ( Vector Class Implements List Interface )
         |     |
         |     |__Stack (Stack class Extends Vector Class)
         |__LinkedList ( LinkedList Class Implements List Interface )
                       ( LinkedList Implements Deque Interface too )
*/
public interface List<E> extends Collection<E> {
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    E remove(int index);
    int indexOf(E element);
    static Object[] manualCopyOf(Object[] oldArray, int newLength) {
        Object[] newArray = new Object[newLength];
        int len = Math.min(oldArray.length, newLength);
        for (int i = 0; i < len; i++) {
            newArray[i] = oldArray[i];
        }
        return newArray;
    }
}
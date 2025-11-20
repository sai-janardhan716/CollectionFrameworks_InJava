package Framewok.Collection.Queue;
import Framewok.Collection.Collection;

public interface Queue<E> extends Collection<E> {
    boolean offer(E e);   // add element safely
    E poll();             // remove head, return null if empty
    E remove();           // remove head, throw exception if empty
    E peek();             // view head, return null if empty
    E element();          // view head, throw exception if empty
}

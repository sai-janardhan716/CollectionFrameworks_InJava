package Implementation;
import Framewok.Collection.List.Vector;
public class Stack<E> extends Vector<E> {
    public E push(E item) {
        add(item);
        return item;
    }
    public E pop() {
        int size = size();
        if (size == 0) throw new EmptyStackException();
        return remove(size - 1);
    }
    public E peek() {
        int size = size();
        if (size == 0) throw new EmptyStackException();
        return get(size - 1);
    }
    public boolean empty() {
        return size() == 0;
    }
    public int search(E element) {
        for (int i = size() - 1; i >= 0; i--) {
            if (element.equals(get(i))) {
                return size() - i;
            }
        }
        return -1;
    }
    public static class EmptyStackException extends RuntimeException{}
}

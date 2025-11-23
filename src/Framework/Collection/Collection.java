package Framework.Collection;
// First Main interface in Collection Framework
public interface Collection<E>{
        int size();
        boolean isEmpty();
        boolean contains(E e);
        boolean add(E e);
        boolean remove(E e);
        void clearAll();
        E[] toArray();
}
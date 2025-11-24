import Framework.Collection.Set.Set;

import java.util.LinkedHashMap;

public class LinkedHashSet<E> implements Set<E> {

    private static final Object PRESENT = new Object();

    private LinkedHashMap<E, Object> map;

    public LinkedHashSet() {
        map = new LinkedHashMap<>();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean remove(E e) {
        return map.remove(e) == PRESENT;
    }

    @Override
    public boolean contains(E e) {
        return map.containsKey(e);
    }

    @Override
    public void clearAll() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public E[] toArray() {
        return (E[]) map.keySet().toArray();
    }
}

package Framework.Collection.Set;

import java.util.HashMap;
import java.util.Map;

public class HashSet<E> implements Set<E> {

    private static final Object PRESENT = new Object();
    private final Map<E, Object> map;

    public HashSet() {
        map = new HashMap<>();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public boolean contains(E e) {
        return map.containsKey(e);
    }

    @Override
    public boolean remove(E e) {
        return map.remove(e) == PRESENT;
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

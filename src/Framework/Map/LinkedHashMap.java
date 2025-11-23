package Framework.Map;

public class LinkedHashMap<K,V> implements Map<K, V> {
    static class Node<K,V> {
        final K key;
        V value;
        Node<K,V> next;
        Node<K,V> before;
        Node<K, V> after;
        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K,V>[] table;
    private Node<K, V> head;
    private Node<K,V> tail;
    private int size;
    private int threshold;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public LinkedHashMap() {
        table = new Node[DEFAULT_CAPACITY];
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private int hash(K key) {
        return (key == null) ? 0 : (key.hashCode() & 0x7fffffff);
    }

    private int indexFor(int hash) {
        return hash % table.length;
    }

    private boolean equals(K a, K b) {
        return (a == b) || (a != null && a.equals(b));
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> current = table[index];
        while (current != null) {
            if (equals(current.key, key)) {
                V old = current.value;
                current.value = value;
                return old;
            }
            current = current.next;
        }
        Node<K, V> newNode = new Node<>(key, value, table[index]);
        table[index] = newNode;
        linkLast(newNode);
        size++;
        if (size > threshold) resize();
        return null;
    }

    private void linkLast(Node<K,V> node) {
        if (head == null) {
            head = tail = node;
        } else {
            tail.after = node;
            node.before = tail;
            tail = node;
        }
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> curr = table[index];
        while (curr != null) {
            if (equals(curr.key, key)) return curr.value;
            curr = curr.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> curr = table[index];
        Node<K, V> prev = null;
        while (curr != null) {
            if (equals(curr.key, key)) {
                if (prev == null) table[index] = curr.next;
                else prev.next = curr.next;
                unlink(curr);
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    private void unlink(Node<K,V> node) {
        Node<K,V> b = node.before;
        Node<K,V> a = node.after;
        if (b == null) head = a;
        else b.after = a;
        if (a == null) tail = b;
        else a.before = b;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        Node<K,V> curr = head;
        while (curr != null) {
            if ((value == curr.value) || (value != null && value.equals(curr.value)))
                return true;
            curr = curr.after;
        }
        return false;
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
    public void clearAll() {
        table = new Node[DEFAULT_CAPACITY];
        head = tail = null;
        size = 0;
        threshold = (int)(DEFAULT_CAPACITY * LOAD_FACTOR);
    }

    private void resize() {
        Node<K, V>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        Node<K, V>[] newTable = new Node[newCapacity];
        Node<K,V> curr = head;
        while (curr != null) {
            int newIndex = (curr.key == null ? 0 :
                    (curr.key.hashCode() & 0x7fffffff) % newCapacity);
            curr.next = newTable[newIndex];
            newTable[newIndex] = curr;
            curr = curr.after;
        }
        table = newTable;
        threshold = (int)(newCapacity * LOAD_FACTOR);
    }
}

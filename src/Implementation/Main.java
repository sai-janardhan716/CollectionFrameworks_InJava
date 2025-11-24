package Framework;
import Framework.Collection.List.*;
import Framework.Collection.Queue.*;
import Framework.Collection.Set.*;
import Framework.Map.*;

import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        System.out.println("ArrayList: " + arrayList.get(0) + " " + arrayList.get(1) + " " + arrayList.get(2));

        List<String> linkedList = new LinkedList<>();
        linkedList.add("X");
        linkedList.add("Y");
        linkedList.add("Z");
        System.out.println("LinkedList: " + linkedList.get(0) + " " + linkedList.get(1) + " " + linkedList.get(2));

        List<Integer> vector = new Vector<>();
        vector.add(10);
        vector.add(20);
        vector.add(30);
        System.out.println("Vector: " + vector.get(0) + " " + vector.get(1) + " " + vector.get(2));

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Stack pop: " + stack.pop() + ", top: " + stack.peek());

        Deque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(10);
        arrayDeque.addLast(20);
        arrayDeque.addLast(30);
        System.out.println("ArrayDeque removeFirst: " + arrayDeque.removeFirst());

        Queue<String> myQueue = new MyQueue<>();
        myQueue.offer("A");
        myQueue.offer("B");
        myQueue.offer("C");
        System.out.println("MyQueue poll: " + myQueue.poll());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(50);
        pq.offer(10);
        pq.offer(40);
        System.out.println("PriorityQueue poll: " + pq.poll());

        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        System.out.println("HashSet contains Banana: " + hashSet.contains("Banana"));

        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2); hashMap.put("C", 3);
        System.out.println("HashMap get B: " + hashMap.get("B"));
        hashMap.remove("B");
        System.out.println("HashMap contains B: " + hashMap.containsKey("B"));

        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(10, "TEN");
        linkedHashMap.put(20, "TWENTY");
        linkedHashMap.put(30, "THIRTY");
        System.out.println("LinkedHashMap get 20: " + linkedHashMap.get(20));
        linkedHashMap.remove(20);
        System.out.println("LinkedHashMap contains 20: " + linkedHashMap.containsKey(20));
    }
}

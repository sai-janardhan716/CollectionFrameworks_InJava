# Custom Java Collection Framework  
A complete, from-scratch implementation of core Java Collection Framework data structures — including List, Queue, Set, Map, Hash-based structures, Linked structures, and PriorityQueue.  
This project does **not** use Java’s built-in collection classes internally. Every data structure is manually implemented for learning.

---

## Project Structure
```
src/
└── Framework/
├── Collection/
│ ├── List/
│ │ ├── ArrayList.java
│ │ ├── LinkedList.java
│ │ ├── Vector.java
│ │ ├── Stack.java
│ │ └── List.java
│ │
│ ├── Queue/
│ │ ├── ArrayDeque.java
│ │ ├── Deque.java
│ │ ├── MyQueue.java
│ │ ├── PriorityQueue.java
│ │ └── Queue.java
│ │
│ └── Set/
│ ├── HashSet.java
│ ├── LinkedHashSet.java
│ └── Set.java
│
├── Map/
│ ├── Map.java
│ ├── MyHashMap.java
│ └── MyLinkedHashMap.java
│
└── Implementation/
└── Main.java

```

---

## Features Implemented

# **List Implementations**
- ArrayList  
- LinkedList  
- Vector  
- Stack  

# **Queue Implementations**
- MyQueue (custom FIFO queue)  
- ArrayDeque  
- PriorityQueue (Binary Min Heap)  

# **Set Implementations**
- HashSet (custom buckets + chaining)  
- LinkedHashSet (hash table + doubly linked list)  

# **Map Implementations**
- MyHashMap (buckets + chaining, load factor, resizing)  
- MyLinkedHashMap (insertion-order map like Java LinkedHashMap)  

---

# Purpose of This Project

- Deep understanding of internal data structure mechanics
- Prepare for Java interviews
- Strengthen core CS concepts
- Build a portfolio-ready real-world project
- Learn how ArrayList, HashMap, LinkedHashMap, PriorityQueue actually work internally
- Improve algorithmic thinking

# Author
Alapatu G P S Sai Janardhan

# Contribute
Pull requests and improvements are welcome!

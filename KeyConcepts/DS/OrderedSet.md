# TreeSet and TreeMap in Java

---

## ✅ TreeSet in Java

### 📌 Key Concepts:
- **Implements**: `NavigableSet`, `SortedSet`
- **Backed by**: A **Red-Black Tree** (self-balancing binary search tree)
- Maintains elements in **sorted (ascending) order** by default.
- Does **not allow duplicates**.
- Can use **custom Comparator** for custom sorting.
- Operations like `add()`, `remove()`, `contains()` take **O(log n)** time.

### 🔧 Basic Usage:
```java
import java.util.TreeSet;

TreeSet<Integer> set = new TreeSet<>();
set.add(10);
set.add(5);
set.add(20);

System.out.println(set); // Output: [5, 10, 20]
```

### 🔑 Important Methods:
- `add(E e)` – Adds an element.
- `remove(Object o)` – Removes an element.
- `contains(Object o)` – Checks if element exists.
- `first()` – Returns the lowest element.
- `last()` – Returns the highest element.
- `ceiling(E e)` – Smallest ≥ e.
- `floor(E e)` – Largest ≤ e.
- `higher(E e)` – Strictly greater.
- `lower(E e)` – Strictly smaller.
- `pollFirst()` – Retrieves and removes the first (lowest) element.
- `pollLast()` – Retrieves and removes the last (highest) element.
- `iterator()` – Ascending order iterator.
- `descendingIterator()` – Descending order iterator.

### 🎯 LeetCode Examples:
- [ContainsDuplicateIII_220.java ](https://leetcode.com/problems/contains-duplicate-iii/) 
- [SlidingWindowMedian_480.java](https://leetcode.com/problems/sliding-window-median/)
- [MyCalendarI_729](https://leetcode.com/problems/my-calendar-i/description/)
---

## ✅ TreeMap in Java

### 📌 Key Concepts:
- **Implements**: `NavigableMap`, `SortedMap`
- **Backed by**: A **Red-Black Tree**
- Stores **key-value** pairs in **sorted order of keys**.
- Keys must be **unique**.
- Can use **custom Comparator** for key sorting.
- Operations like `put()`, `remove()`, `get()` are **O(log n)**.

### 🔧 Basic Usage:
```java
import java.util.TreeMap;

TreeMap<String, Integer> map = new TreeMap<>();
map.put("apple", 3);
map.put("banana", 5);
map.put("orange", 2);

System.out.println(map); // Output: {apple=3, banana=5, orange=2}
```

### 🔑 Important Methods:
- `put(K key, V value)` – Inserts a key-value pair.
- `get(Object key)` – Retrieves the value for the given key.
- `remove(Object key)` – Removes the key-value pair.
- `containsKey(Object key)` – Checks if the map contains a key.
- `firstKey()` / `lastKey()` – First/last keys in sorted order.
- `ceilingKey(K key)` – Smallest key ≥ given key.
- `floorKey(K key)` – Largest key ≤ given key.
- `higherKey(K key)` – Strictly greater key.
- `lowerKey(K key)` – Strictly smaller key.
- `pollFirstEntry()` / `pollLastEntry()` – Removes and returns the first/last entry.
- `keySet()` – Returns a `Set` view of keys.
- `values()` – Returns a `Collection` view of values.
- `entrySet()` – Returns a `Set` of key-value mappings.

---

### 🎯 LeetCode Examples:
- [FindingMKAverage_1825](https://leetcode.com/problems/finding-mk-average/)
- [MyCalendarI_729](https://leetcode.com/problems/my-calendar-i/description/)

## 🧠 When to Use
| Use Case             | Use `TreeSet` or `TreeMap`? |
|----------------------|-----------------------------|
| Unique sorted elements | `TreeSet`                  |
| Sorted key-value pairs | `TreeMap`                  |
| Need range queries (e.g., floor/ceiling) | Both     |

---

## 📎 Comparator Example
```java
TreeSet<String> set = new TreeSet<>((a, b) -> b.compareTo(a)); // Descending
set.add("apple");
set.add("banana");
set.add("cherry");
System.out.println(set); // [cherry, banana, apple]
```

```java
TreeMap<String, Integer> map = new TreeMap<>((a, b) -> b.compareTo(a)); // Descending keys
map.put("a", 1);
map.put("b", 2);
System.out.println(map); // {b=2, a=1}
```
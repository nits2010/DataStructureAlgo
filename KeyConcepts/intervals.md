
# 📘 Guide to Solving Interval-Based Questions

Interval problems are a popular category in coding interviews. These questions involve ranges or intervals (often representing time), and test your ability to reason about overlaps, gaps, and efficient interval processing.

---

## 🧠 Key Concepts

### 1. **Sorting by Start Time**
Most interval problems start with sorting intervals by their `start` time. This helps us process them in order and is often required before merging or checking overlaps.

```java
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
```

---

## 🧩 Common Interval Problem Types

### ✅ 1. Merge Intervals
- **Problem:** Merge all overlapping intervals.
- **Logic:** After sorting, merge current with previous if they overlap.
- **Key Condition:** If `curr.start <= prev.end`, they overlap.

---

### ✅ 2. Insert Interval
- **Problem:** Insert a new interval and merge if needed.
- **Logic:** Use merge logic and insert at correct position.
- **Tip:** Watch for cases where the new interval overlaps multiple existing ones.

---

### ✅ 3. Interval Intersection
- **Problem:** Find common overlapping intervals between two lists.
- **Logic:** Use two pointers. Move the one with the smaller end.
- **Condition:** If `A[i].start <= B[j].end` and `B[j].start <= A[i].end`

---

### ✅ 4. Employee Free Time
- **Problem:** Find common free time for all employees given their schedules.
- **Logic:** Merge all busy intervals → Find gaps between them.

---

## 🚀 Line Sweep Algorithm

Line Sweep is a powerful technique used in problems involving intervals and events on a timeline.

### 📌 How It Works:
1. Represent each interval as two events: `start` (+1), `end` (-1).
2. Sort events by time. If equal, process `end` before `start`.
3. Sweep through time, maintaining a count of "active" intervals.

### ✅ Use Cases:
- Employee Free Time
- Meeting Room II
- Maximum Overlapping Intervals
- Skyline Problem

### 🧮 Pseudocode:

```java
List<int[]> events = new ArrayList<>();
for (Interval interval : intervals) {
    events.add(new int[]{interval.start, 1});  // Start event
    events.add(new int[]{interval.end, -1});   // End event
}

events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

int active = 0;
for (int[] event : events) {
    active += event[1];
    // Use active to detect gaps, max concurrency, etc.
}
```

### ⏱ Time Complexity:
- Sorting: `O(N log N)`
- Sweep: `O(N)`
- Overall: `O(N log N)`

---

## 🧠 Tips for Interval Problems

- Always **sort** the intervals first.
- Consider **edge cases**: empty input, fully overlapping, disjoint intervals.
- Think about **merge, overlap, and gap** logic.
- **Line Sweep** is great for concurrency, free time, or overlap counts.
- Use **TreeMap** or **PriorityQueue** for dynamic tracking when needed.

---

## 📚 Practice Problems

- [Pattern 04_Merge Intervals.md](../DSA_Pattern/Pattern%2004_Merge%20Intervals.md)
---

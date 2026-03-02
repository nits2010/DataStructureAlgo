Cycle detection is a fundamental problem in graph theory. Whether you are checking for deadlocks in an operating system or ensuring there are no circular dependencies in a software build system, knowing how to spot a loop is essential.

Since different types of graphs require different logical approaches, we’ll break this down by **Directed** and **Undirected** graphs.

---

## 1. Undirected Graphs: The "Already Visited" Strategy

In an undirected graph, a cycle exists if you encounter a vertex that has already been visited and is **not** the immediate parent of the current vertex.

### The Algorithm (DFS)

We use Depth First Search (DFS). For every vertex $v$, we keep track of its parent. If we move to an adjacent vertex $u$ that is already visited and $u \neq \text{parent}(v)$, we’ve found a cycle.

### Python Implementation
- All implementations [python](https://github.com/nits2010/DataStructureAlgo/blob/preparation-2025/src/main/java/DataStructureAlgo/Java/LeetCode2025/ProblemSet/Graph/cycle_detection/undirected_graph_cycle_detection_algo.py)

```python
def has_cycle_undirected(adj, v, visited, parent):
    visited[v] = True
    
    for neighbor in adj[v]:
        if not visited[neighbor]:
            if has_cycle_undirected(adj, neighbor, visited, v):
                return True
        # If neighbor is visited and not the parent, it's a cycle
        elif neighbor != parent:
            return True
            
    return False

# Example Usage
graph = {0: [1, 2], 1: [0, 2], 2: [0, 1]} # A triangle (cycle)
visited = [False] * 3
print(f"Cycle detected: {has_cycle_undirected(graph, 0, visited, -1)}")

```

---

## 2. Directed Graphs: The "Recursion Stack" Strategy

In directed graphs, simply visiting a node twice doesn't mean there is a cycle (it could be two different paths leading to the same node). A cycle only exists if we hit a node that is currently in our **active recursion stack**. This is known as a **Back Edge**.

### The Algorithm (DFS)

We maintain two sets: `visited` (to avoid redundant work) and `rec_stack` (to track nodes in the current path).

### Python Implementation
- All implementations [python](https://github.com/nits2010/DataStructureAlgo/blob/preparation-2025/src/main/java/DataStructureAlgo/Java/LeetCode2025/ProblemSet/Graph/cycle_detection/directed_graph_cycle_detection_algo.py)

```python
def has_cycle_directed(adj, v, visited, rec_stack):
    visited[v] = True
    rec_stack[v] = True
    
    for neighbor in adj[v]:
        if not visited[neighbor]:
            if has_cycle_directed(adj, neighbor, visited, rec_stack):
                return True
        elif rec_stack[neighbor]:
            return True
            
    rec_stack[v] = False # Backtrack
    return False

```

---

## 3. The Breadth-First Approach: Kahn’s Algorithm

For directed graphs, you can also use **Kahn’s Algorithm** (originally for Topological Sorting).

* **Logic:** A Directed Acyclic Graph (DAG) must have at least one node with an in-degree of 0. If we repeatedly remove such nodes, and we end up with nodes left over that we can't "process," a cycle exists.

---

## 4. Complexity Analysis

The complexity for cycle detection is generally the same across these methods:

| Metric | Complexity | Reasoning |
| --- | --- | --- |
| **Time Complexity** | $O(V + E)$ | We visit every vertex ($V$) once and traverse every edge ($E$) exactly once. |
| **Space Complexity** | $O(V)$ | We store the `visited` array/stack, which grows linearly with the number of vertices. |

### How do we arrive at $O(V+E)$?

In both DFS and BFS, the algorithm explores the graph systematically.

1. **Initialization:** Setting up the `visited` array takes $O(V)$.
2. **Exploration:** Each vertex is pushed/popped from the stack/queue once. For each vertex, we iterate through its adjacency list. Summing up the lengths of all adjacency lists equals the total number of edges $E$ (or $2E$ for undirected).
3. **Total:** $V + E$ operations.

---

## Summary Table

| Graph Type | Key Indicator | Best Algorithm |
| --- | --- | --- |
| **Undirected** | Visited node $\neq$ Parent | DFS / Union-Find |
| **Directed** | Back Edge (node in current stack) | DFS with Recursion Stack |
| **Directed** | Inability to complete Topo-sort | Kahn's Algorithm (BFS) |

Would you like me to show you how to implement the **Union-Find** version for undirected graphs? It's often more efficient for "on-the-fly" cycle detection!
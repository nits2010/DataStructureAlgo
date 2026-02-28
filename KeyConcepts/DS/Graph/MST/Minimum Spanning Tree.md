

# Minimum Spanning Trees (MST): Connecting the Dots Efficiently

In a graph interview, an **MST** problem usually sounds like this: *"Connect all cities with the minimum amount of cable"* or *"Build a road network such that every house is reachable with the lowest total construction cost."*

An MST is a subset of edges that:

1. **Connects all vertices** together.
2. Contains **no cycles**.
3. Has the **minimum possible total edge weight**.

### How many edges does a minimum spanning tree has?
A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.


### What are the applications of Minimum Spanning Tree?
- [Applications of MST](https://www.geeksforgeeks.org/applications-of-minimum-spanning-tree/).
---

## 1. The Strategy: Kruskal vs. Prim

While both are greedy algorithms, their "mental models" are different.

| Algorithm | Mental Model | Best For | Key Data Structure |
| --- | --- | --- | --- |
| **Kruskal’s** | **Edge-Centric.** Pick the cheapest edges first, wherever they are. | Sparse Graphs (fewer edges). | **Union-Find (DSU)** |
| **Prim’s** | **Node-Centric.** Grow a "cloud" from a starting point. | Dense Graphs (many edges). | **Priority Queue** |

---


### 🖇️ Kruskal’s Algorithm: The "Forest Merger"

Kruskal’s is an **edge-centric** approach. You sort *all* edges in the entire graph from cheapest to most expensive. You then pick the cheapest edge—no matter where it is—as long as it doesn't create a cycle.

* **Best for:** **Sparse Graphs** (where the number of edges $E$ is relatively small, closer to $V$).
* **Key Tool:** **Disjoint Set Union (DSU)** / Union-Find (to detect cycles quickly).
* **Behavior:** It grows a "forest." You might have several disconnected mini-trees all over the graph that eventually merge into one giant MST at the very end.

### Implementations: 
- KruskalMinimumSpanningTree.java


**The Logic:**

1. Sort all edges by weight.
2. Iterate through sorted edges: if the two nodes aren't already connected, add the edge to the MST and "Union" them.
3. Stop when you have $V - 1$ edges.

```python
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        
    def find(self, i):
        if self.parent[i] == i:
            return i
        self.parent[i] = self.find(self.parent[i]) # Path compression
        return self.parent[i]

    def union(self, i, j):
        root_i = self.find(i)
        root_j = self.find(j)
        if root_i != root_j:
            self.parent[root_i] = root_j
            return True
        return False

def kruskals(n, edges):
    # edges: list of (weight, u, v)
    edges.sort() 
    uf = UnionFind(n)
    mst_weight = 0
    edges_count = 0
    
    for w, u, v in edges:
        if uf.union(u, v):
            mst_weight += w
            edges_count += 1
            if edges_count == n - 1:
                break
                
    return mst_weight

```

---


### 🌲 Prim’s Algorithm: The "Growing Tree"

Prim’s is like a **localized search**. You start at one vertex (the seed) and grow your tree outward by always grabbing the cheapest edge that connects your tree to an "outside" vertex.

* **Best for:** **Dense Graphs** (where the number of edges $E$ is close to the number of vertices squared, $V^2$).
* **Key Tool:** A **Priority Queue** (to find the next cheapest vertex).
* **Behavior:** It always maintains a single, connected component. At every step, you have a valid (but incomplete) tree.

### Implementation
- PrimMinimumSpanningTree.java
---

Prim’s is very similar to Dijkstra’s. Instead of updating distances from a *start node*, you update the distance from the *growing MST*.

**The Logic:**

1. Start with an arbitrary node.
2. Use a Priority Queue to track the cheapest edge that connects a node *inside* the MST to a node *outside* the MST.
3. Add that edge and repeat.

```python
import heapq

def prims(n, adj):
    # adj: list of lists [ (neighbor, weight), ... ]
    pq = [(0, 0)] # (weight, node)
    visited = [False] * n
    mst_weight = 0
    nodes_count = 0
    
    while pq and nodes_count < n:
        w, u = heapq.heappop(pq)
        
        if visited[u]:
            continue
            
        visited[u] = True
        mst_weight += w
        nodes_count += 1
        
        for v, weight in adj[u]:
            if not visited[v]:
                heapq.heappush(pq, (weight, v))
                
    return mst_weight if nodes_count == n else -1

```

---

## 4. Complexity Breakdown

| Algorithm | Complexity | Why? |
| --- | --- | --- |
| **Kruskal’s** | $O(E \log E)$ | Dominant factor is sorting the edges. |
| **Prim’s** | $O(E \log V)$ | Dominant factor is Priority Queue operations. |

---

## 5. Summary: Which one should you code?

### 📊 Quick Comparison Table

| Feature | Prim’s Algorithm | Kruskal’s Algorithm |
| --- | --- | --- |
| **Philosophy** | Start from a node, grow outward. | Start from edges, merge components. |
| **Complexity** | $O(E log V)$ (or $O(E + V log V)$ with Fibonacci Heap) | $O(E log E)$ or $O(E log V)$ (due to sorting) |
| **Graph Type** | Best for **Dense** graphs ($E  V^2$). | Best for **Sparse** graphs ($E  V$). |
| **Data Structure** | Priority Queue + Adjacency List. | Union-Find + Edge List. |
| **Connectivity** | Tree is always connected. | Tree is disconnected until the end (Forest). |

---

### 🚀 When to use what?

1. **Use Prim's if:**
* The graph is **dense** (lots of edges).
* You are already using an adjacency list/matrix and don't want to extract and sort all edges.
* You need the tree to be connected at every stage of the algorithm.


2. **Use Kruskal's if:**
* The graph is **sparse** (few edges).
* Edges are already sorted or easy to sort.
* You find the **Union-Find** data structure easier to implement than managing a Priority Queue with vertex updates.

### Common Interview "Twist":

If the interviewer says, "What if some edges **must** be included in the MST?", the answer is simple: Start your algorithm by adding those edges first (Union them in Kruskal's or add them to the MST set in Prim's) and then proceed normally.

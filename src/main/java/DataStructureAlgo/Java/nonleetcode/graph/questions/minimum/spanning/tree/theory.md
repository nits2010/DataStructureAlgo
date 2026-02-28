### What is Minimum Spanning Tree?
Given a connected and undirected graph, a spanning tree of that graph is a subgraph that is a tree and connects all the vertices together.
A single graph can have many different spanning trees. A minimum spanning tree (MST) or minimum weight spanning tree for a weighted,
connected and undirected graph is a spanning tree with weight less than or equal to the weight of every other spanning tree.
The weight of a spanning tree is the sum of weights given to each edge of the spanning tree.
=> https://www.youtube.com/watch?v=4ZlRH0eK-qQ&ab_channel=AbdulBari

### How many edges does a minimum spanning tree has?
A minimum spanning tree has (V – 1) edges where V is the number of vertices in the given graph.

### What are the applications of Minimum Spanning Tree?
See https://www.geeksforgeeks.org/applications-of-minimum-spanning-tree/ for applications of MST.


---

### 🌲 Prim’s Algorithm: The "Growing Tree"

Prim’s is like a **localized search**. You start at one vertex (the seed) and grow your tree outward by always grabbing the cheapest edge that connects your tree to an "outside" vertex.

* **Best for:** **Dense Graphs** (where the number of edges $E$ is close to the number of vertices squared, $V^2$).
* **Key Tool:** A **Priority Queue** (to find the next cheapest vertex).
* **Behavior:** It always maintains a single, connected component. At every step, you have a valid (but incomplete) tree.

---

### 🖇️ Kruskal’s Algorithm: The "Forest Merger"

Kruskal’s is an **edge-centric** approach. You sort *all* edges in the entire graph from cheapest to most expensive. You then pick the cheapest edge—no matter where it is—as long as it doesn't create a cycle.

* **Best for:** **Sparse Graphs** (where the number of edges $E$ is relatively small, closer to $V$).
* **Key Tool:** **Disjoint Set Union (DSU)** / Union-Find (to detect cycles quickly).
* **Behavior:** It grows a "forest." You might have several disconnected mini-trees all over the graph that eventually merge into one giant MST at the very end.

---

### 📊 Quick Comparison Table

| Feature | Prim’s Algorithm | Kruskal’s Algorithm |
| --- | --- | --- |
| **Philosophy** | Start from a node, grow outward. | Start from edges, merge components. |
| **Complexity** | $O(E \log V)$ (or $O(E + V \log V)$ with Fibonacci Heap) | $O(E \log E)$ or $O(E \log V)$ (due to sorting) |
| **Graph Type** | Best for **Dense** graphs ($E \approx V^2$). | Best for **Sparse** graphs ($E \approx V$). |
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



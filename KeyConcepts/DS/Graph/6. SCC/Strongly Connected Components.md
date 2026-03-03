# Strongly Connected Components (SCC): Finding the Loops

In a directed graph, a **Strongly Connected Component (SCC)** is a portion of the graph where every node is reachable from every other node in that same portion. Think of it as a "mutual friendship" circle: if you're in the circle, you can reach anyone else, and they can reach you.
- Finding Strongly Connected Components (SCCs) is like identifying "friend groups" in a social network where everyone can eventually reach everyone else through a chain of follows. In technical terms, an SCC is a maximal subgraph where every vertex is reachable from every other vertex.

This is a high-level interview topic, often used for problems involving **social networks** (finding tight-knit communities) or **web crawlers** (finding sets of pages that link back and forth).

Two algorithms
- Kosaraju’s Algorithm
- Tarjan’s Algorithm
---

## 1. The Strategy: Kosaraju’s Algorithm

While Tarjan’s algorithm exists, **Kosaraju’s** is the preferred interview choice because it uses two standard DFS passes, making it easier to explain and debug.
- [Video](https://www.youtube.com/watch?v=QlGuaHT1lzA)
**The "Magic" Logic:**
If you reverse all the edges in a graph, the SCCs remain exactly the same. However, the connections *between* different SCCs are reversed. We use this to "trap" our DFS within one SCC at a time.

---

## 2. Kosaraju’s Algorithm (3-Step Implementation)

1. **Step 1:** Run DFS to get the "Finishing Order" (put nodes on a stack as they finish).
2. **Step 2:** Reverse the entire graph (Transpose).
3. **Step 3:** Pop from the stack and run DFS on the reversed graph. Each DFS traversal marks one full SCC.

```python
def get_sccs(n, adj):
    # Step 1: DFS to get finish times (Stack)
    visited = [False] * n
    stack = []

    def dfs_stack(u):
        visited[u] = True
        for v in adj[u]:
            if not visited[v]:
                dfs_stack(v)
        stack.append(u)

    for i in range(n):
        if not visited[i]:
            dfs_stack(i)

    # Step 2: Transpose the graph (Reverse edges)
    adj_rev = [[] for _ in range(n)]
    for u in range(n):
        for v in adj[u]:
            adj_rev[v].append(u)

    # Step 3: DFS on reversed graph in order of Stack
    visited = [False] * n
    sccs = []

    def dfs_collect(u, current_scc):
        visited[u] = True
        current_scc.append(u)
        for v in adj_rev[u]:
            if not visited[v]:
                dfs_collect(v, current_scc)

    while stack:
        u = stack.pop()
        if not visited[u]:
            component = []
            dfs_collect(u, component)
            sccs.append(component)

    return sccs

```

---

## 3. The Mental Model: "The Sink Trap"

Imagine two SCCs, **A** and **B**, with an edge pointing from **A $\to$ B**.

* In the first DFS, **B** will finish and get put on the stack *after* **A** (or effectively "below" A's starting point).
* When we reverse the graph, the edge becomes **B $\to$ A**.
* When we pop **A** from the stack and start a DFS, we can no longer reach **B** because the arrow is reversed. We are "trapped" in **A**. This is how we isolate them!

---

## 4. Complexity and Constraints

| Metric | Complexity | Why? |
| --- | --- | --- |
| **Time** | $O(V + E)$ | Two passes of DFS + one pass to reverse edges. |
| **Space** | $O(V + E)$ | Storing the reversed graph and the recursion stack. |

---
### 5.  The Strategy: Tarjan’s Algorithm 

Tarjan’s relies on the concept of **DFS Trees**. As we traverse the graph, we keep track of when we first visit a node and the "highest" node (closest to the root) it can reach.
- [Video](https://www.youtube.com/watch?v=_1TDxihjtoE)
### Key Variables

* **Discovery Time:** The order in which a node was first visited.
* **Low Link Value:** The smallest discovery time reachable from that node (including itself) in the DFS tree, using back-edges to nodes still in the current exploration stack.
* **Stack:** A list of nodes currently being explored that could potentially form an SCC.

### The Logic

1. Start a DFS for every unvisited node.
2. Upon visiting a node, set its discovery time and low-link value to the current timer. Add it to the stack.
3. For every neighbor:
* If it hasn't been visited, recurse. After returning, update the current node's low-link value: `low[u] = min(low[u], low[v])`.
* If it's already on the stack, it's a back-edge! Update: `low[u] = min(low[u], disc[v])`.


4. If a node’s **low-link value equals its discovery time**, it is the "root" of an SCC. Pop everything off the stack until you reach that node—those popped nodes form one complete SCC.

---

## Python Implementation

Here is a clean, modular implementation of Tarjan's.

```python
from collections import defaultdict

class TarjanSCC:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = defaultdict(list)
        self.time = 0
        self.sccs = []

    def add_edge(self, u, v):
        self.graph[u].append(v)

    def find_sccs(self):
        # Discovery time and Low-link values initialized to -1
        disc = [-1] * self.V
        low = [-1] * self.V
        stack_member = [False] * self.V
        st = []

        for i in range(self.V):
            if disc[i] == -1:
                self._scc_util(i, low, disc, stack_member, st)
        
        return self.sccs

    def _scc_util(self, u, low, disc, stack_member, st):
        disc[u] = self.time
        low[u] = self.time
        self.time += 1
        st.append(u)
        stack_member[u] = True

        for v in self.graph[u]:
            # If v is not visited, recurse
            if disc[v] == -1:
                self._scc_util(v, low, disc, stack_member, st)
                low[u] = min(low[u], low[v])
            
            # If v is in the stack, it's a back-edge
            elif stack_member[v]:
                low[u] = min(low[u], disc[v])

        # If u is a root node, pop the stack and generate an SCC
        if low[u] == disc[u]:
            current_scc = []
            while True:
                w = st.pop()
                stack_member[w] = False
                current_scc.append(w)
                if u == w:
                    break
            self.sccs.append(current_scc)

# Example Usage:
g = TarjanSCC(5)
g.add_edge(1, 0)
g.add_edge(0, 2)
g.add_edge(2, 1)
g.add_edge(0, 3)
g.add_edge(3, 4)

print("Strongly Connected Components:")
print(g.find_sccs())

```

---

## Complexity Analysis

| Metric | Complexity | Reason |
| --- | --- | --- |
| **Time** | $O(V + E)$ | Each node and edge is visited exactly once during the DFS. |
| **Space** | $O(V)$ | Required for the recursion stack, the SCC stack, and the tracking arrays. |

### Why use Tarjan's over Kosaraju's?

While both have the same asymptotic complexity, Kosaraju’s requires **two** DFS passes (one on the original graph and one on the transposed graph). Tarjan’s does it in **one**, making it slightly faster in practice and easier to implement if you want to avoid "reversing" the entire graph.

---


## 5. Summary: SCC Interview Cheat Sheet

* **When to use:** "Find groups where everyone can reach everyone," "Detect cycles in a directed graph," or "Group web pages that reference each other."
* **Directed only:** SCCs don't exist in undirected graphs (those are just "Connected Components").
* **Component Graph:** If you treat each SCC as a single node, the resulting graph is always a **DAG** (Directed Acyclic Graph).

### Final Tip for the Interview

If the interviewer asks for a one-pass solution, mention **Tarjan’s Algorithm**. It uses a single DFS and "Low-Link" values to find SCCs. However, unless specifically asked, stick to Kosaraju’s—it is much more intuitive to explain and less prone to "off-by-one" errors in your logic.

---

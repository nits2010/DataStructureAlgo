# Strongly Connected Components (SCC): Finding the Loops

In a directed graph, a **Strongly Connected Component (SCC)** is a portion of the graph where every node is reachable from every other node in that same portion. Think of it as a "mutual friendship" circle: if you're in the circle, you can reach anyone else, and they can reach you.

This is a high-level interview topic, often used for problems involving **social networks** (finding tight-knit communities) or **web crawlers** (finding sets of pages that link back and forth).

---

## 1. The Strategy: Kosaraju’s Algorithm

While Tarjan’s algorithm exists, **Kosaraju’s** is the preferred interview choice because it uses two standard DFS passes, making it easier to explain and debug.

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

## 5. Summary: SCC Interview Cheat Sheet

* **When to use:** "Find groups where everyone can reach everyone," "Detect cycles in a directed graph," or "Group web pages that reference each other."
* **Directed only:** SCCs don't exist in undirected graphs (those are just "Connected Components").
* **Component Graph:** If you treat each SCC as a single node, the resulting graph is always a **DAG** (Directed Acyclic Graph).

### Final Tip for the Interview

If the interviewer asks for a one-pass solution, mention **Tarjan’s Algorithm**. It uses a single DFS and "Low-Link" values to find SCCs. However, unless specifically asked, stick to Kosaraju’s—it is much more intuitive to explain and less prone to "off-by-one" errors in your logic.

---
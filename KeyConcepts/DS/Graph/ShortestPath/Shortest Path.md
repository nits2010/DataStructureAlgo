# Master the Shortest Path: From BFS to A*

In technical interviews, "Shortest Path" problems are rarely as simple as "find the shortest distance." They come disguised as "minimum number of hops," "least amount of fuel," or "fastest route through a grid."

The key to winning is choosing the right algorithm based on two factors: **Edge Weights** and **Knowledge of the Destination.**

---

## 1. The Strategy Table

| Algorithm | Edge Weights | Best For | Logic |
| --- | --- | --- | --- |
| **BFS** | **None / Constant** | Minimum hops/steps. | Level-order expansion. |
| **Dijkstra** | **Positive** | Most weighted path problems. | Greedy exploration via Priority Queue. |
| **A*** | **Positive** | Maps/Grids with a target. | Dijkstra + Heuristic "guess." |
| **Bellman-Ford** | **Negative** | Finance/Arbitrage. | Relaxing all edges $V-1$ times. |

---
For Java implementations; check here 
- IShortestPath.java

## 2. Unweighted: Breadth-First Search (BFS)

**Mental Model:** A ripple in a pond. It moves one unit in all directions simultaneously. The first time you "touch" the target, you are guaranteed to have used the fewest edges.
**Time complexity:** ```O(V + E)```

```python
from collections import deque

def bfs_shortest_path(graph, start, goal):
    # queue stores (current_node, distance)
    queue = deque([(start, 0)])
    visited = {start}
    
    while queue:
        node, dist = queue.popleft()
        
        if node == goal:
            return dist
            
        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append((neighbor, dist + 1))
    return -1 # Path not found

```

---

## 3. Weighted (Positive): Dijkstra's Algorithm

**Mental Model:** A "Greedy" explorer. Instead of moving one *hop* at a time, it always explores the node that is *currently* the cheapest to reach from the start.
**Time complexity:** ```O(E.log V)```
```python
import heapq

def dijkstra(graph, start, goal):
    # pq stores (distance_to_node, node)
    pq = [(0, start)]
    distances = {node: float('inf') for node in graph}
    distances[start] = 0
    
    while pq:
        curr_dist, curr_node = heapq.heappop(pq)
        
        if curr_node == goal:
            return curr_dist
            
        # If we found a longer path already, skip
        if curr_dist > distances[curr_node]:
            continue
            
        for neighbor, weight in graph[curr_node].items():
            distance = curr_dist + weight
            
            # If this new path is shorter, update and push to PQ
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(pq, (distance, neighbor))
                
    return -1

```

---

## 1️⃣ When to Use `visited`

Use `visited` when:

* You only care about the **first time a node is finalized**
* After popping from heap, cost is guaranteed minimal
* You don’t need to relax nodes multiple times

Typical:
* Classic Dijkstra with no need to update better paths later

`visited` = finalization marker

---

## 2️⃣ When to Use `dist` (Distance Map)

Use `dist` when:

* A node might be reached multiple times with **better cost later**
* You must compare `newCost < dist[node]`
* You want standard Dijkstra relaxation

Typical:

* Sum-based shortest path
* Graphs with many alternative paths

`dist` = best-known cost tracker

---

### Key Distinction

If correctness depends on comparing old vs new costs → use `dist`.

If first pop guarantees optimal → `visited` alone is enough.

---

---

## 4. Weighted (Targeted): A* Search

**Mental Model:** Dijkstra with a "Compass." It calculates $f(n) = g(n) + h(n)$.
**Time complexity:** ```O(E.log V)```

* $g(n)$: Distance from start.
* $h(n)$: Estimated distance to goal (Heuristic).

```python
import heapq

def heuristic(a, b):
    # Manhattan distance for grid movement
    return abs(a[0] - b[0]) + abs(a[1] - b[1])

def a_star(grid, start, goal):
    pq = [(0 + heuristic(start, goal), 0, start)] # (f_score, g_score, node)
    g_scores = {start: 0}
    
    while pq:
        f, g, curr = heapq.heappop(pq)
        
        if curr == goal:
            return g
            
        for neighbor in get_neighbors(curr, grid):
            new_g = g + 1 # Assuming cost 1 per move
            if new_g < g_scores.get(neighbor, float('inf')):
                g_scores[neighbor] = new_g
                f_score = new_g + heuristic(neighbor, goal)
                heapq.heappush(pq, (f_score, new_g, neighbor))
    return -1

```

---

## 5. Negative Weights: Bellman-Ford

**Mental Model:** The "Pessimist." It assumes any edge could be improved, so it relaxes every single edge in the graph $V-1$ times. If it can still relax an edge on the $V^{th}$ try, you have a **Negative Cycle** (infinite money loop).
**Time complexity:** ```O(V \times E)```

```python
def bellman_ford(vertices, edges, start):
    dist = {v: float('inf') for v in vertices}
    dist[start] = 0
    
    # Relax edges V-1 times
    for _ in range(len(vertices) - 1):
        for u, v, weight in edges:
            if dist[u] != float('inf') and dist[u] + weight < dist[v]:
                dist[v] = dist[u] + weight
                
    # Check for negative cycles
    for u, v, weight in edges:
        if dist[u] != float('inf') and dist[u] + weight < dist[v]:
            return "Graph contains negative weight cycle"
            
    return dist

```

---

## Summary for the Interviewer

1. **Unweighted?** BFS (Standard Queue).
2. **Weighted?** Dijkstra (Priority Queue).
3. **Heuristic available?** A* (Priority Queue + Heuristic).
4. **Negative Edges?** Bellman-Ford.

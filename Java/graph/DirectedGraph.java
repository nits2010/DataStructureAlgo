package Java.graph;

import java.util.*;
import java.util.stream.Stream;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/02/19
 * Description:
 */
public class DirectedGraph implements IGraph {


    //To hold the edges
    private final List<Integer>[] adjancyList;
    private final int vertices;

    //initiate the graph
    public DirectedGraph(int vertices) {

        adjancyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++)
            adjancyList[i] = new LinkedList<>();

        this.vertices = vertices;
    }

    @Override
    public void addEdge(int source, int destination) {
        adjancyList[source].add(destination);

    }

    @Override
    public List<Integer>[] getAdjList() {
        return this.adjancyList;
    }

    @Override
    public int getVertices() {
        return this.vertices;
    }

    /**
     * This works for both, Directed Graph
     * and Un-Directed Graph
     *
     * @return
     */
    @Override
    public List<Integer> topologicalSort() {

        boolean visited[] = new boolean[this.vertices];

        Arrays.fill(visited, false);

        //visit every vertices one by one;

        Stack<Integer> stack = new Stack<>();

        Stream<Integer> verticesStream = Stream.iterate(0, x -> x + 1).limit(this.vertices);

        verticesStream
                .filter(x -> !visited[x])
                .forEach(x -> topologicalSort(visited, stack, x));


        final List<Integer> sorted = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            sorted.add(stack.pop());
        }


        return sorted;
    }


    private void topologicalSort(boolean[] visited, Stack<Integer> stack, int x) {

        visited[x] = true;

        List<Integer> edges = this.adjancyList[x];


        edges.stream()
                .filter(i -> !visited[i])
                .forEach(i -> topologicalSort(visited, stack, i));
        stack.push(x);

    }

    /**
     * This works for only, Directed Graph
     * because A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
     *
     * @return
     */
    @Override
    public List<Integer> topologicalSortKhanAlgo() {
        List<Integer> top = new ArrayList<>(this.vertices);


        //count inDegree
        int[] inDegree = new int[this.vertices];


        for (int i = 0; i < this.vertices; i++) {

            for (Integer adj : adjancyList[i]) {
                inDegree[adj]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        //push all 0 degree elements
        for (int i = 0; i < this.vertices; i++)
            if (inDegree[i] == 0)
                queue.offer(i);


        //Process one by one
        while (!queue.isEmpty()) {

            int current = queue.poll();
            top.add(current);

            for (Integer adj : adjancyList[current]) {
                inDegree[adj]--;
                if (inDegree[adj] == 0)
                    queue.offer(adj);

            }
        }

        if (top.size() != this.vertices) {
            System.out.println("Cycle exists");
            return new ArrayList<>();
        }

        return top;
    }

    @Override
    public boolean detectCycleKhanAlgo() {
        List<Integer> top = new ArrayList<>(this.vertices);


        //count inDegree
        int[] inDegree = new int[this.vertices];


        for (int i = 0; i < this.vertices; i++) {

            for (Integer adj : adjancyList[i]) {
                inDegree[adj]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        //push all 0 degree elements
        for (int i = 0; i < this.vertices; i++)
            if (inDegree[i] == 0)
                queue.offer(i);


        //Process one by one
        while (!queue.isEmpty()) {

            int current = queue.poll();
            top.add(current);

            for (Integer adj : adjancyList[current]) {
                inDegree[adj]--;
                if (inDegree[adj] == 0)
                    queue.offer(adj);

            }
        }


        return top.size() != this.vertices;

    }

    @Override
    public boolean detectCycleDFS() {

        return detectCycle(adjancyList, this.vertices);
    }


    private boolean detectCycle(List<Integer>[] adjList, int vertices) {

        int visited[] = new int[vertices]; //initially all are false;

        /**
         * Visit each vertices
         */
        for (int c = 0; c < vertices; c++) {
            //Visit those which has not visit yet

            if (visited[c] != -1 && detectCycle(adjList, c, visited))
                return false;

        }
        return true;
    }

    /**
     * DFS
     *
     * @param adjList
     * @param vertex
     * @param visited
     * @return
     */
    private boolean detectCycle(List<Integer>[] adjList, int vertex, int[] visited) {

        /**
         * If visited already, then there is a cycle
         */
        if (visited[vertex] == 1)
            return true;

        /**
         * This means, that this vertex has been covered
         */
        if (visited[vertex] == -1)
            return false;

        visited[vertex] = 1;

        for (Integer c : adjList[vertex])
            if (detectCycle(adjList, c, visited))
                return true;

        /**
         * Mark this vertex as covered
         */
        visited[vertex] = -1;

        return false;
    }


}

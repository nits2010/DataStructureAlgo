package Java.graph;

import java.util.*;
import java.util.stream.Stream;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-23
 * Description:
 */
public class TopologicalSorts {

    /**
     * This works for both, Directed Graph
     * and Un-Directed Graph
     * DFS
     *
     * @return
     */
    public static List<Integer> topologicalSortDFS(final int vertices, final List<Integer>[] adjancyList) {

        boolean visited[] = new boolean[vertices];

        Arrays.fill(visited, false);

        //visit every vertices one by one;

        Stack<Integer> stack = new Stack<>();

        Stream<Integer> verticesStream = Stream.iterate(0, x -> x + 1).limit(vertices);

        verticesStream
                .filter(x -> !visited[x])
                .forEach(x -> topologicalSortDFS(adjancyList, visited, stack, x));


        final List<Integer> sorted = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            sorted.add(stack.pop());
        }


        return sorted;
    }


    private static void topologicalSortDFS(final List<Integer>[] adjancyList, boolean[] visited, Stack<Integer> stack, int x) {

        visited[x] = true;

        List<Integer> edges = adjancyList[x];


        edges.stream()
                .filter(i -> !visited[i])
                .forEach(i -> topologicalSortDFS(adjancyList, visited, stack, i));
        stack.push(x);

    }

    /**
     * This works for only, Directed Graph
     * because A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
     *
     * @return
     */
    public static List<Integer> topologicalSortKhanAlgo(final List<Integer>[] adjancyList, final int vertices) {
        List<Integer> top = new ArrayList<>(vertices);


        //count inDegree
        int[] inDegree = new int[vertices];


        for (int i = 0; i < vertices; i++) {

            for (Integer adj : adjancyList[i]) {
                inDegree[adj]++;
            }
        }


        Queue<Integer> queue = new LinkedList<>();

        //push all 0 degree elements
        for (int i = 0; i < vertices; i++)
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

        if (top.size() != vertices) {
            System.out.println("Cycle exists");
            return new ArrayList<>();
        }

        return top;
    }

}

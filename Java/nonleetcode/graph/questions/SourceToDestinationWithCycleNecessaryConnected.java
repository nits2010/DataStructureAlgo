package Java.nonleetcode.graph.questions;

import Java.nonleetcode.graph.graph.types.DirectedGraph;
import Java.nonleetcode.graph.graph.IGraph;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 12/04/19
 * Description:
 */
public class SourceToDestinationWithCycleNecessaryConnected {

    public static void main(String []args) {

        int vertices = 9;
        IGraph directedGraph = new DirectedGraph(vertices);

        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(2, 4);
        directedGraph.addEdge(3, 4);
        directedGraph.addEdge(3, 6);
        directedGraph.addEdge(3, 5);
        directedGraph.addEdge(4, 5);
        directedGraph.addEdge(4, 2); // Cycle
        directedGraph.addEdge(4, 6);
        directedGraph.addEdge(5, 4); // Cycle
        directedGraph.addEdge(5, 7);
        directedGraph.addEdge(7, 8);

        System.out.println(isNecessaryConnected(directedGraph, vertices, 2, 8));


        IGraph directedGraph2 = new DirectedGraph(vertices);

        directedGraph2.addEdge(2, 3);
        directedGraph2.addEdge(2, 1);
        directedGraph2.addEdge(2, 4);
        directedGraph2.addEdge(3, 4);
        directedGraph2.addEdge(3, 6);
        directedGraph2.addEdge(3, 5);
        directedGraph2.addEdge(4, 5);
        directedGraph2.addEdge(4, 2); // Cycle
        directedGraph2.addEdge(4, 6);
        directedGraph2.addEdge(5, 4); // Cycle
        directedGraph2.addEdge(5, 7);
        directedGraph2.addEdge(7, 8);

        System.out.println(isNecessaryConnected(directedGraph2, vertices, 2, 8));

    }

    private static boolean isNecessaryConnected(IGraph directedGraph, int vertices, int source, int destination) {
        if (source > vertices || destination > vertices || source < 0 || destination < 0)
            return false;

        Set<Integer> visited = new HashSet<>();
        boolean path[] = new boolean[vertices];

        visited.add(source);
        dfs(directedGraph, visited, path, destination, source);

        for (int node : directedGraph.getAdjList()[source]) {
            if (path[node] == false)
                return false;
        }
        return true;
    }

    private static boolean dfs(IGraph directedGraph, Set<Integer> visited, boolean[] path, int destination, int current) {

        //if this path already been computed
        if (path[current])
            return true;

        for (int node : directedGraph.getAdjList()[current]) {

            if (!visited.contains(node)) {

                //add this node so that we won't visit again (to avoid cycle)
                visited.add(node);

                if (node == destination)
                    path[node] = true;

                if (dfs(directedGraph, visited, path, destination, node)) {
                    path[current] = true;
                    return true;
                }

                visited.remove(node);
            }
        }


        return false;
    }
}

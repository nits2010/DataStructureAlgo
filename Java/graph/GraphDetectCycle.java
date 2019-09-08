package Java.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-23
 * Description:
 */
public class GraphDetectCycle {

    public static boolean detectCycleKhanAlgo(final int vertices, final List<Integer>[] adjancyList) {
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


        return top.size() != vertices;

    }

    public static boolean detectCycleDFS(final int vertices, final List<Integer>[] adjancyList) {

        int visited[] = new int[vertices]; //initially all are false;

        /**
         * Visit each vertices
         */
        for (int c = 0; c < vertices; c++) {
            //Visit those which has not visit yet

            if (visited[c] != -1 && detectCycleDFS(adjancyList, c, visited))
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
    private static boolean detectCycleDFS(List<Integer>[] adjList, int vertex, int[] visited) {

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
            if (detectCycleDFS(adjList, c, visited))
                return true;

        /**
         * Mark this vertex as covered
         */
        visited[vertex] = -1;

        return false;
    }

}

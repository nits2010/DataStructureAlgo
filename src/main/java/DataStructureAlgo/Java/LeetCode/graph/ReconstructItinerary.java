package DataStructureAlgo.Java.LeetCode.graph;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/reconstruct-itinerary/
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * <p>
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 * <p>
 * <p>
 * Full explanation: https://leetcode.com/problems/reconstruct-itinerary/discuss/359942/Awesome-question-or-new-algo-to-learn-or-Full-explanation-or-Code
 * <p>
 * <p>
 * This problem is based on Eulerian Path in graph
 * Eulerian path
 * * In graph theory, an Eulerian trail (or Eulerian path) is a trail in a finite graph that visits every edge exactly once (allowing for revisiting vertices).
 * * Similarly, an Eulerian circuit or Eulerian cycle is an Eulerian trail that starts and ends on the same vertex. They were first discussed by Leonhard Euler while solving the
 * * famous Seven Bridges of Königsberg problem in 1736. The problem can be stated mathematically like this:
 * * <p>
 * * Given the graph in the image, is it possible to construct a path (or a cycle, i.e. a path starting and ending on the same vertex) that visits each edge exactly once?
 * * Euler proved that a necessary condition for the existence of Eulerian circuits is that all vertices in the graph have an even degree, and stated without proof that connected
 * * graphs with all vertices of even degree have an Eulerian circuit.
 * * This is known as Euler's Theorem:
 * * <p>
 * * A connected graph has an Euler cycle iff every vertex has even degree.
 * <p>
 * A Graph must have nodes with even degree and odd degree. All the odd degree nodes are either start or end but all the even degree node will be only intermediate nodes.
 * But in case when all the nodes has even degree, then it contains the Eulerian Tour, as we we'll start from some node and will end to this same node, as we need to consume all the edges.
 * <p>
 * <p>
 * Important:
 * If all nodes in graph has odd degree than its not possible to build Eulerian Path, as for coming and going from/to a node we require even degree for some of the node.
 *
 * <p>
 * Must Watch:
 * Eulerian Path - Intro to Algorithms: https://www.youtube.com/watch?v=ycRuO-u6rt8
 * Eulerian Path Solution - Intro to Algorithms: https://www.youtube.com/watch?v=Dx1lpbpSHwI
 */
public class ReconstructItinerary {

    public static void main(String[] args) {


        test(Arrays.asList(
                Arrays.asList("JFK", "A"),
                Arrays.asList("JFK", "D"),
                Arrays.asList("A", "C"),
                Arrays.asList("B", "C"),
                Arrays.asList("C", "JFK"),
                Arrays.asList("C", "D"),
                Arrays.asList("D", "A"),
                Arrays.asList("D", "B"))
                , "[JFK, A, C, D, B, C, JFK, D, A]");

        test(Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO"))
                , "[JFK, MUC, LHR, SFO, SJC]");


        test(Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO"))
                , "[JFK, ATL, JFK, SFO, ATL, SFO]");


        test(Arrays.asList(
                Arrays.asList("JFK", "KUL"),
                Arrays.asList("JFK", "NRT"),
                Arrays.asList("NRT", "JFK"))
                , "[JFK, NRT, JFK, KUL]");

    }

    private static void test(List<List<String>> tickets, String expected) {
        System.out.println("-------");
        System.out.println("tickets         :" + tickets);
        System.out.println("Expected        :" + expected);
        final List<String> itinerary = new ReconstructItineraryGraph().findItinerary(tickets);
        System.out.println("Itinerary       :" + itinerary);
    }
}

/**
 * From : https://leetcode.com/problems/reconstruct-itinerary/discuss/78768/Short-Ruby-Python-Java-C%2B%2B
 * https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 */
interface IReconstructItinerary {
    List<String> findItinerary(List<List<String>> tickets);
}


/**
 * Algo:
 * 1. Build a directed graph LinkedList representation, source -> [destination1, destination2....] And each destination is sorted lexical
 * 2. Run DFS on it.
 * <p>
 * We can do either backtracking or just modify normal dfs to Eular path algorithm.
 * Where we approach from top to down and once we reach down with all node visited , we'll build itinerary from bottom.
 * At last shortestPath it.
 * <p>
 * Runtime: 5 ms, faster than 81.48% of Java online submissions for Reconstruct Itinerary.
 * Memory Usage: 44 MB, less than 61.19% of Java online submissions for Reconstruct Itinerary.
 */
class ReconstructItineraryGraph implements IReconstructItinerary {

    /**
     * O(ELogE)
     * where E is number of edges. Because we offer each edge into queue once and then poll it out once.
     * Space complexity is O(E).
     *
     * @param tickets
     * @return
     */
    @Override
    public List<String> findItinerary(List<List<String>> tickets) {

        if (tickets == null || tickets.isEmpty())
            return new ArrayList<>();

        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);

        List<String> itinerary = new ArrayList<>(graph.size());
        dfs(itinerary, graph, "JFK");

        Collections.reverse(itinerary);
        return itinerary;
    }


    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets) {

        //Key -> Source
        //Value -> lexical sorted destinations
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        for (List<String> ticket : tickets) {
            String source = ticket.get(0);
            String destination = ticket.get(1);

            if (!graph.containsKey(source))
                graph.put(source, new PriorityQueue<>());

            graph.get(source).add(destination);

        }
        return graph;
    }

    /**
     * https://leetcode.com/problems/reconstruct-itinerary/discuss/78768/Short-Ruby-Python-Java-C%2B%2B
     * <p>
     * First keep going forward until you get stuck. That's a good main path already. Remaining tickets form cycles which are found on the
     * way back and get merged into that main path. By writing down the path backwards when retreating from recursion, merging the cycles into the main path is easy -
     * the end part of the path has already been written, the start part of the path hasn't been written yet, so just write down the cycle now and then keep backwards-writing the path.
     * <p>
     * Walk-through
     * Input:
     * [[JFK, A], [JFK, D] , [A,C], [B,C] , [C, JFK], [C , D], [D,A] , [D, B] ]
     * <p>
     * Graph = {'JFK': ['A', 'D'], 'A': ['C'], 'B': ['C'], 'C': ['D', 'JFK'], 'D': ['A', 'B']}
     * route = []
     * stack = ['JFK']
     * First point at which we get stuck:
     * <p>
     * <p>
     * Graph = {'JFK': ['D'], 'A': [], 'B': ['C'], 'C': ['D' , 'JFK',], 'D': ['B']}
     * route = []
     * stack = ['JFK', 'A', 'C', 'D', 'A']
     * Update route:
     * <p>
     * <p>
     * Graph = {'JFK': ['D'], 'A': [], 'B': ['C'], 'C': ['JFK'], 'D': ['B']}
     * route = ['A'] Last node of path {['JFK', 'A', 'C', 'D', 'A']}
     * stack = ['JFK', 'A', 'C', 'D']
     * Search forward again until stuck:
     * <p>
     * <p>
     * Graph = {'JFK': [], 'A': [], 'B': [], 'C': [], 'D': []}
     * route = ['A']
     * stack = ['JFK', 'A', 'C', 'D', 'B', 'C', 'JFK', 'D']
     * Update route:
     * After updating all the way
     * <p>
     * <p>
     * Graph = {'JFK': ['D'], 'A': [], 'B': [], 'C': ['JFK'], 'D': []}
     * route = ['A', 'D', 'JFK', 'C', 'B', 'D', 'C', 'A', 'JFK']
     * stack = []
     * Return route in shortestPath:
     * <p>
     * <p>
     * route = JFK, A, C, D, B, C, JFK, D, A { reverse of above route}
     * <p>
     * <p>
     * From JFK we first visit JFK -> A -> C -> D -> A. There we're stuck, so we write down A as the end of the route and retreat back to D.
     * There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again, retreat and write down the airports while doing so:
     * Write down D before the already written A, then JFK before the D, etc. When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A.
     * Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.
     *
     * @param itinerary
     * @param graph
     * @param source
     */
    private void dfs(List<String> itinerary, Map<String, PriorityQueue<String>> graph, String source) {

        /**
         * if now more destination possible from this source, then we are at the bottom most.
         */
        if (graph.get(source) == null || graph.get(source).isEmpty()) {
            itinerary.add(source);
            return;
        }


        /**
         * Try all the destination from this source incrementally.
         * This is important for input like [[JFK, KUL], [JFK, NRT], [NRT, JFK]]
         * because once you reach KUL, you can't go anywhere but we have tickets left, so we should go NTR first
         */
        while (!graph.get(source).isEmpty()) {
            String nextDestination = graph.get(source).poll();
            dfs(itinerary, graph, nextDestination);
        }
        /**
         * We are at the bottom, traverse back
         */
        itinerary.add(source);

    }

    /**
     * Runtime: 8 ms, faster than 40.90% of Java online submissions for Reconstruct Itinerary.
     * Memory Usage: 43.2 MB, less than 64.18% of Java online submissions for Reconstruct Itinerary.
     *
     * @param itinerary
     * @param graph
     * @param source
     */
    private void dfsIterative(List<String> itinerary, Map<String, PriorityQueue<String>> graph, String source) {

        Stack<String> stack = new Stack<>();
        stack.push(source);

        while (!stack.isEmpty()) {

            while (graph.containsKey(stack.peek()) && !graph.get(stack.peek()).isEmpty()) {
                String nextDestination = graph.get(stack.peek()).poll();
                stack.push(nextDestination);
            }
            itinerary.add(stack.pop());
        }
    }
}
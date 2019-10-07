package Java.companyWise.thoughtspot;

import Java.helpers.GenericPrinter;
import Java.nonleetcode.graph.questions.shortest.path.DijkstraShortestPath;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 20/09/19
 * Description:
 * Given list of cities and the time taken to reach from a city to a city by
 * q. Road
 * B. By flight
 * <p>
 * There may be some city to city which has either road or flight or both
 * <p>
 * Find the shortest path from source to destination subject to having only one flight almost
 * <p>
 * Example: A,B,C,D,E cities
 * <p>
 * A -> Road -> B = 30
 * A -> Flight -> B = 20
 * B -> Road -> D = 30
 * B -> Road -> E = 100
 * B -> Flight -> E = 40
 * B -> Road -> C = 20
 * C -> Road -> E = 5
 * D -> Road -> E = 5
 * A -> Flight -> D = 4
 * D -> flight -> E = 2
 * <p>
 * <p>
 * Source: A , Destination: E
 * <p>
 * Paths
 * A -> Road -> B -> Road -> D -> Road -> E = 30 + 30 + 5 = 65
 * A -> Road -> B -> Road -> E = 30 + 100 = 130
 * A -> Road -> B -> Flight -> E = 30 + 40 = 70
 * A -> Road -> B -> Road -> C -> Road -> E = 30+20+5 = 65
 * <p>
 * A -> Flight -> B -> Road -> D -> Road -> E = 20 + 30 + 5 = 55
 * A -> Flight -> B -> Road -> E = 20 + 100 = 120
 * A -> Flight -> B -> Flight -> E = 20 + 40 = 60 { discard, > 1 flight }
 * A -> Flight -> B -> Road -> C -> Road -> E = 20+20+5 = 45
 * <p>
 * A -> Flight -> D -> Flight -> E = 4+2=6  { discard, > 1 flight }
 * <p>
 * Shortest route is with at most 1 flight is
 * A -> Flight -> B -> Road -> C -> Road -> E = 20+20+5 = 45
 * <p>
 * Input: a matrix of n*n where n is number of cities. Each cell of matrix represent two values (r,f)
 * if there is a route from A -> B vai rode then r > -1 and vai flight then f > -1 otherwise they will be -1 if no route is there
 * cityMap = [
 * [
 * [( -1 , -1 ) ,( 30 , 20 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ]
 * [( -1 , -1 ) ,( -1 , -1 ) ,( 20 , -1 ) ,( 30 , -1 ) ,( 100 , 40 ) ]
 * [( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( 5 , -1 ) ]
 * [( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( 5 , 2 ) ]
 * [( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ,( -1 , -1 ) ]
 * ] flightCount 1
 * ]
 * expected: 45
 * <p>
 * path :A->(R:30)B->(R:20)C->(R:5)E road cost :55 flight cost :0
 * <p>
 * path :A->(R:30)B->(R:30)D->(R:5)E road cost :65 flight cost :0
 * <p>
 * path :A->(R:30)B->(R:30)D->(F:2)E road cost :60 flight cost :2
 * <p>
 * path :A->(R:30)B->(R:100)E road cost :130 flight cost :0
 * <p>
 * path :A->(R:30)B->(F:40)E road cost :30 flight cost :40
 * <p>
 * path :A->(F:20)B->(R:20)C->(R:5)E road cost :25 flight cost :20
 * <p>
 * path :A->(F:20)B->(R:30)D->(R:5)E road cost :35 flight cost :20
 * <p>
 * path :A->(F:20)B->(R:100)E road cost :100 flight cost :20
 * Best Path A->(F:20)B->(R:20)C->(R:5)E
 * BackTracking    :45
 *
 * Similar {@link Java.companyWise.Google.ShortestPathBreakingThroughWalls}
 */
class CityCell {

    final int r;
    final int f;

    public CityCell(int r, int f) {
        this.r = r;
        this.f = f;
    }

    @Override
    public String toString() {
        return "( " + r + " , " + f + " ) ";
    }
}

public class ShortestPathRoadFlightAtMostNFlight {

    public static void main(String[] args) {
        test(new CityCell[][]
                {
                        {new CityCell(-1, -1), new CityCell(30, 20), new CityCell(-1, -1), new CityCell(-1, 4), new CityCell(-1, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(20, -1), new CityCell(30, -1), new CityCell(100, 1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, 2)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1)}
                }, 1, 9);

        test(new CityCell[][]
                {
                        {new CityCell(-1, -1), new CityCell(30, 20), new CityCell(-1, -1), new CityCell(-1, 4), new CityCell(-1, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(20, -1), new CityCell(30, -1), new CityCell(100, 40)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, 2)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1)}
                }, 1, 9);

        test(new CityCell[][]
                {
                        {new CityCell(-1, -1), new CityCell(30, 20), new CityCell(-1, -1), new CityCell(-1, 4), new CityCell(-1, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(20, -1), new CityCell(30, -1), new CityCell(100, 40)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, 2)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1)}
                }, 2, 6);

        test(new CityCell[][]
                {
                        {new CityCell(-1, -1), new CityCell(30, 20), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(20, -1), new CityCell(30, -1), new CityCell(100, 40)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, -1)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(5, 2)},
                        {new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1), new CityCell(-1, -1)}
                }, 1, 45);
    }

    private static void test(CityCell[][] cityMap, int flightCount, int expected) {
        System.out.println("\nCity Map :\n" + GenericPrinter.toStringT2D(cityMap) + " flightCount " + flightCount);
        System.out.println("Expected                :" + expected);
        System.out.println("BackTracking            :" + new ShortestPathRoadFlightAtMostNFlightBacktracking(cityMap.length).shortestPathCost(cityMap, 0, 4, flightCount));
        System.out.println("DijkstraShortestPath    :" + new ShortestPathRoadFlightAtMostNFlightDijkstraShortestPath(cityMap.length).shortestPathCost(cityMap, 0, 4, flightCount));

    }
}

/**
 * We can use backtracking approach to solve this problem.
 * 1. Our goal : to reach destination. Whenever we reach destination, we'll record least cost and best path
 * 2. Our constraint: a) we can only take a path if exist either vai road or flight . b) we can use at most number of given flight
 * 3. Our choices: we can try all the path that is connected either by road or flight or both.
 * <p>
 * Complexity:
 * In worst there is a road to every city to every city by both road and flight. In this case we evaluate every possible path. if N is number of cities then
 * there is (N-1) other paths are there originated from a source. And we need to evaluate both using road and flight (N-1)
 * T(N) = T(N-1) + T(N-1)
 * => O(2^N)
 */
class ShortestPathRoadFlightAtMostNFlightBacktracking {
    final Map<Integer, Character> map = new HashMap<>();

    public ShortestPathRoadFlightAtMostNFlightBacktracking(int n) {
        for (int i = 0; i < n; i++) {
            map.put(i, (char) (i + 65));
        }
    }


    public int shortestPathCost(final CityCell[][] cityMap, final int sourceX, final int destinationX, final int flightsCount) {
        if (cityMap == null || cityMap.length == 0 || cityMap[0].length == 0)
            return 0;

        String[] path = {""};

        int[] cost = {1000};

        StringBuilder temp = new StringBuilder();
        temp.append(map.get(sourceX));

        shortestPathCost(cityMap, sourceX, destinationX, flightsCount, temp, path, cost, 0, 0);
        System.out.println("Best Path " + path[0]);
        return cost[0];
    }


    private void shortestPathCost(CityCell[][] cityMap, int sourceX, int destinationX, int flightsCount, StringBuilder currentPath, String[] bestPath, int[] cost, int roadCost, int flightCost) {

        //2. Our constraint: a) we can only take a path if exist either vai road or flight . b) we can use at most number of given flight
        if (flightsCount < 0)
            return;

        //1. Our goal : to reach destination. Whenever we reach destination, we'll record least cost and best path
        if (sourceX == destinationX) {
//            System.out.println("\npath :" + currentPath + " road cost :" + roadCost + " flight cost :" + flightCost);
            if (cost[0] > flightCost + roadCost) {
                cost[0] = flightCost + roadCost;
                bestPath[0] = currentPath.toString();
            }

            return;
        }

        //3. Our choices: we can try all the path that is connected either by road or flight or both.
        for (int i = 0; i < cityMap.length; i++) {
            CityCell current = cityMap[sourceX][i];
            if (i == sourceX || (current.r == -1 && current.f == -1))
                continue;


            int length = currentPath.length();

            //By road
            if (current.r != -1) {
                currentPath.append("->(R:").append(current.r).append(")").append(map.get(i));

                shortestPathCost(cityMap, i, destinationX, flightsCount, currentPath, bestPath, cost, roadCost + current.r, flightCost);
                currentPath.setLength(length);
            }

            //By flight
            if (current.f != -1) {
                currentPath.append("->(F:").append(current.f).append(")").append(map.get(i));
                shortestPathCost(cityMap, i, destinationX, flightsCount - 1, currentPath, bestPath, cost, roadCost, flightCost + current.f);
                currentPath.setLength(length);
            }


        }
    }


}

/**
 * {@link DijkstraShortestPath} + BFS
 * <p>
 * Complexity: O((m*n)^2)
 */
class ShortestPathRoadFlightAtMostNFlightDijkstraShortestPath {
    final Map<Integer, Character> map = new HashMap<>();

    public ShortestPathRoadFlightAtMostNFlightDijkstraShortestPath(int n) {
        for (int i = 0; i < n; i++) {
            map.put(i, (char) (i + 65));
        }
    }

    static class Distance {
        int withoutFlight, withFlight;

        public Distance() {
            this.withoutFlight = Integer.MAX_VALUE;
            this.withFlight = Integer.MAX_VALUE;

        }

        @Override
        public String toString() {
            return "Distance{" +
                    "withoutFlight=" + withoutFlight +
                    ", withFlight=" + withFlight +
                    '}';
        }
    }

    static class Node {
        int distance;
        int node;
        int flightCount;

        public Node(int distance, int node, int isFlight) {
            this.distance = distance;
            this.node = node;
            this.flightCount = isFlight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "distance=" + distance +
                    ", node=" + node +
                    ", flightCount=" + flightCount +
                    '}';
        }
    }


    public int shortestPathCost(final CityCell[][] cityMap, final int sourceX, final int destinationX, final int flightsCount) {
        if (cityMap == null || cityMap.length == 0 || cityMap[0].length == 0)
            return 0;


        final Queue<Node> queue = new LinkedList<>();

        final Distance[] distance = new Distance[cityMap.length];
        for (int i = 0; i < cityMap.length; i++)
            distance[i] = new Distance();

        //Offer the start node in the queue with 0 distance and no flight
        queue.offer(new Node(0, sourceX, 0));
        distance[sourceX].withFlight = 0;
        distance[sourceX].withoutFlight = 0;


        //Process all paths
        while (!queue.isEmpty()) {

            //pull first path
            Node current = queue.poll();

            // all neighbours
            CityCell[] connectedCities = cityMap[current.node];

            for (int i = 0; i < connectedCities.length; i++) {
                CityCell cityCell = connectedCities[i];

                if (i == current.node || (cityCell.f == -1 && cityCell.r == -1))
                    continue;

                //See if this neighbour is gives minimum distance by road
                if (cityCell.r != -1 && distance[i].withoutFlight >= current.distance + cityCell.r) {
                    distance[i].withoutFlight = current.distance + cityCell.r;
                    queue.offer(new Node(distance[i].withoutFlight, i, current.flightCount));
                }


                //See if this neighbour is gives minimum distance by flight and we have 1 more flight left from current location so far
                if (cityCell.f != -1 && current.flightCount + 1 <= flightsCount && distance[i].withFlight >= current.distance + cityCell.f) {
                    distance[i].withFlight = current.distance + cityCell.f;
                    queue.offer(new Node(distance[i].withFlight, i, current.flightCount + 1));
                }


            }

        }
        //get minimum distance with/without flight
        return Math.min(distance[destinationX].withFlight, distance[destinationX].withoutFlight);


    }

}
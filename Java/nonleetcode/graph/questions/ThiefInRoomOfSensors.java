package Java.nonleetcode.graph.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 13/04/19
 * Description: https://www.careercup.com/question?id=6266160824188928
 * Given a room with thief on left side of the room with finite number of sensors.
 * He has to reach on right side missing the sensors. Each sensor is placed at any random point in the room and has its coverage in the radius r.
 * Find out if the thief can reach to the right side without touching the range of any sensor.?
 */
public class ThiefInRoomOfSensors {

    static class Subsets {
        int parent;
        int rank;

        public Subsets(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return "{" +
                    "parent=" + parent +
                    ", rank=" + rank +
                    '}';
        }
    }

    static class GraphUnionFind {
        Subsets parent[];

        public GraphUnionFind(int nodes) {
            parent = new Subsets[nodes + 1]; //since id start from 1

            for (int i = 1; i <= nodes; i++) {
                parent[i] = new Subsets(i, 1);
            }

        }

        public int find(int i) {
            if (parent[i].parent == i)
                return i;
            return parent[i].parent = find(parent[i].parent);
        }

        public void union(int i, int j) {

            int p1 = find(i);
            int p2 = find(j);
            if (p1 != p2)
                if (parent[p1].rank < parent[p2].rank)
                    parent[p1].parent = p2;
                else if (parent[p1].rank > parent[p2].rank)
                    parent[p2].parent = p1;
                else {
                    parent[p2].parent = p1;
                    parent[p1].rank++;
                }


        }
    }


    static class Sensors {
        int id ;
        double x, y;
        double r;

        public Sensors(double x, double y, double r, int id) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.id = id;
        }

        @Override
        public String toString() {
            return "{" +
                    "id=" + id +
                    ", x=" + x +
                    ", y=" + y +
                    ", r=" + r +
                    '}';
        }
    }

    static class Room {
        double height;
        double width;

        List<Sensors> sensors;

        public Room(int s, double h, double w) {
            sensors = new ArrayList<>(s);
            height = h;
            width = w;
        }

        public void putSensors(double x, double y, double r, int id) {
            sensors.add(new Sensors(x, y, r, id));
        }
    }

    public static void main(String []args) {

        Room room = new Room(5, 1, 1);

        room.putSensors(0, 0, 0.5, 1);
        room.putSensors(0.5, 0.2, 0.5, 2);
        room.putSensors(0.7, 0.4, 0.5, 3);
        room.putSensors(0.6, 0.6, 0.5, 4);
        room.putSensors(1, 1, 0.5, 5);


        Room room2 = new Room(3, 1, 1);

        room2.putSensors(0, 0, 0.5, 1);
        room2.putSensors(0.5, 0.2, 0.5, 2);
        room2.putSensors(0.7, 0.4, 0.5, 3);


        System.out.println(canGoFromLeftToRight(room));
        System.out.println(canGoFromLeftToRight(room2));
    }

    private static boolean canGoFromLeftToRight(Room room) {
        double roomH = room.height;
        List<Sensors> sensors = room.sensors;


        List<Sensors> sensorsCoveringTop = new ArrayList<>();
        List<Sensors> sensorsCoveringBottom = new ArrayList<>();

        //Find that overlaps room height
        for (int i = 0; i < sensors.size(); i++) {
            Sensors s = sensors.get(i);

            if (s.y + s.r >= roomH) // overlap top; from the center of the sensors, if we add radius to its height(y) and its beyond or touch room height
                sensorsCoveringTop.add(s);

            if (s.y <= s.r)
                sensorsCoveringBottom.add(s);
        }

        //nothing overlaps;
        if (sensorsCoveringBottom.isEmpty() || sensorsCoveringTop.isEmpty())
            return true;

        //means either of them overlap, find the overlaps and combine them
        GraphUnionFind graphUnionFind = new GraphUnionFind(sensors.size());

        //Combine overlapping top sensors
        for (int i = 0; i < sensorsCoveringTop.size() - 1; i++) {
            Sensors x = sensorsCoveringTop.get(i);
            Sensors y = sensorsCoveringTop.get(i + 1);
            graphUnionFind.union(x.id, y.id);
        }

        //Combine overlapping bottom sensors
        for (int i = 0; i < sensorsCoveringBottom.size() - 1; i++) {
            Sensors x = sensorsCoveringBottom.get(i);
            Sensors y = sensorsCoveringBottom.get(i + 1);
            graphUnionFind.union(x.id, y.id);
        }

        //Combine overlapping sensors
        for (int i = 0; i < sensors.size(); i++) {
            for (int j = i + 1; j < sensors.size(); j++) {

                //if they overlap
                Sensors x = sensors.get(i);
                Sensors y = sensors.get(j);

                if ((Math.pow((x.x - y.x), 2) + Math.pow((x.y - y.y), 2)) <= 4 * Math.pow(x.r, 2)) { //x^2 + y^2 <= 4*r^2
                    graphUnionFind.union(x.id, y.id);
                }
            }
        }
        return (graphUnionFind.find(sensorsCoveringTop.get(0).id) != graphUnionFind.find(sensorsCoveringBottom.get(0).id));
    }
}

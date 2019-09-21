package Java.graph.graph.types;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public class Edges {

    public int source;
    public int destination;
    public double weight;

    public Edges(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public Edges(int source, int distance) {
        this.source = source;
        this.destination = distance;
    }

    public Edges() {
    }

    @Override
    public String toString() {
        return source + "->" + destination + " w: " + weight;
    }

}

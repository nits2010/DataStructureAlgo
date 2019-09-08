package Java.graph.questions.minimum.spanning.tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-08
 * Description:
 */
public class Edges {

    int source;
    int destination;
    double weight;

    public Edges(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public Edges() {
    }

    @Override
    public String toString() {
        return source + "->" + destination + " w: " + weight;
    }
}

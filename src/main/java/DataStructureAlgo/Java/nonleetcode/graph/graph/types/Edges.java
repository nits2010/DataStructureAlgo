package DataStructureAlgo.Java.nonleetcode.graph.graph.types;

/**
 * Author: Nitin Gupta
 * Date: 2019-09-08
 * Question Title: Edges
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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

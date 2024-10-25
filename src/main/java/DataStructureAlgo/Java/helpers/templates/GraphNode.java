package DataStructureAlgo.Java.helpers.templates;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 9/11/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class GraphNode {

   public static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

package DataStructureAlgo.Java.LeetCode.flatten.list;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Question Title: Singly Node
 * Link: https://leetcode.com/problems/singly-node/
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

public class SinglyNode extends Base {
    public SinglyNode next;
    public SinglyNode child;

    public SinglyNode() {
    }

    public SinglyNode(int _val) {
        val = _val;
    }

    public SinglyNode(int _val, SinglyNode _next, SinglyNode _child) {
        val = _val;
        next = _next;
        child = _child;
    }

    @Override
    public String toString() {
        return val+"";
    }
}


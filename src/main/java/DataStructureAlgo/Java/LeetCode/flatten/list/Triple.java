package DataStructureAlgo.Java.LeetCode.flatten.list;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Question Title: Triple
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

public class Triple<T> {

    T head;
    List<Integer> levelWise;
    List<Integer> depthWise;

    public Triple() {

    }

    public Triple(T head, List<Integer> l, List<Integer> d) {
        this.head = head;
        this.levelWise = l;
        this.depthWise = d;
    }
}

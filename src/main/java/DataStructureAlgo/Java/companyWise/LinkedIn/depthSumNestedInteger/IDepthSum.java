package DataStructureAlgo.Java.companyWise.LinkedIn.depthSumNestedInteger;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 31/03/19
 * Question Title: I Depth Sum
 * Link: https://leetcode.com/problems/i-depth-sum/
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

public interface IDepthSum {

    int getDepth(List<NestedInteger> input, int depth);

    int reversDepthSum(List<NestedInteger> input);

    int forwardDepthSum(List<NestedInteger> input);
}

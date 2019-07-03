package LinkedIn.depthSumNestedInteger;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 31/03/19
 * Description:
 */
public interface IDepthSum {

    int getDepth(List<NestedInteger> input, int depth);

    int reversDepthSum(List<NestedInteger> input);

    int forwardDepthSum(List<NestedInteger> input);
}

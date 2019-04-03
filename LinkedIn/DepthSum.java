package LinkedIn;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 08/03/19
 * Description:
 */
public class DepthSum implements IDepthSum {

    public int getDepth(List<NestedInteger> input, int depth) {
        int myDepth = depth;
        if (input.size() == 1 && input.get(0).isInteger())
            return myDepth;

        for (NestedInteger nst : input) {
            if (!nst.isInteger()) {
                int d = getDepth(nst.getList(), depth + 1);
                myDepth = Math.max(d, myDepth);
            }
        }
        return myDepth;
    }


    @Override
    public int reversDepthSum(List<NestedInteger> input) {

        int myDepth = getDepth(input, 1);

        return reverseDepthSum(input, 1, myDepth);

    }


    private int reverseDepthSum(List<NestedInteger> input, int depth, int myDepth) {
        int sum = 0;
        for (NestedInteger nst : input) {
            if (!nst.isInteger()) {
                sum += reverseDepthSum(nst.getList(), depth + 1, myDepth);

            } else {
                sum += (myDepth - depth + 1) * nst.getInteger();
            }
        }

        return sum;
    }

    @Override
    public int forwardDepthSum(List<NestedInteger> input) {
        return forwardDepthSum(input, 1);

    }

    private int forwardDepthSum(List<NestedInteger> input, int depth) {

        int sum = 0;


        for (NestedInteger nst : input) {
            if (nst.isInteger()) {
                sum += depth * nst.getInteger();
            } else
                sum += forwardDepthSum(nst.getList(), depth + 1);
        }

        return sum;

    }

}

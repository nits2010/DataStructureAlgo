package DataStructureAlgo.Java.companyWise.LinkedIn.depthSumNestedInteger;

import java.util.List;

/**
 * Author: Nitin Gupta
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


    //https://www.careercup.com/question?id=5637861714952192
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

    //https://www.programcreek.com/2014/05/leetcode-nested-list-weight-sum-java/
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

package LinkedIn;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 08/03/19
 * Description:
 */
public class ReverseDepthSum {

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


    public int reversDepthSum(List<NestedInteger> input) {

        int myDeptht = getDepth(input,1);

        return reverseDepthSum(input, 1, myDeptht);

    }

    private int reverseDepthSum(List<NestedInteger> input, int depth, int myDepth) {
       int sum = 0;
       for (NestedInteger nst: input){
          // System.out.println(nst);
           if(!nst.isInteger()){
               sum += reverseDepthSum(nst.getList(), depth+1, myDepth);

           }else {
               sum += (myDepth - depth +1)*nst.getInteger();
           }
       }

        return sum;
    }




}

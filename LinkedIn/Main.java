package LinkedIn;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 08/03/19
 * Description:
 */
public class Main {

    public static void main(String []ar){

        List<NestedInteger> sample1 =  Arrays.asList(
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
                new NestedIntegerValue(2),
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));


        List<NestedInteger> sample2 =  Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));

        System.out.println(sample1);

        System.out.println(sample2);

        ReverseDepthSum reverseDepthSum = new ReverseDepthSum();

        System.out.println(reverseDepthSum.getDepth(sample1,1));
        System.out.println(reverseDepthSum.getDepth(sample2,1));

        System.out.println(reverseDepthSum.reversDepthSum(sample1));
        System.out.println(reverseDepthSum.reversDepthSum(sample2));
    }
}

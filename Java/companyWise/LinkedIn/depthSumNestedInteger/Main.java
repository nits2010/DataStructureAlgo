package Java.companyWise.LinkedIn.depthSumNestedInteger;

import java.util.Arrays;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 08/03/19
 * Description:
 */
public class Main {

    public static void main(String[] ar) {
        IDepthSum depthSum = new DepthSum();

        List<NestedInteger> sample1 = Arrays.asList(
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
                new NestedIntegerValue(2),
                new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));


        List<NestedInteger> sample2 = Arrays.asList(
                new NestedIntegerValue(1),
                new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));

        System.out.println(sample1);
        System.out.println("depth: " + depthSum.getDepth(sample1, 1));

        System.out.println(sample2);
        System.out.println("depth: " + depthSum.getDepth(sample2, 1));

        System.out.println("Reverse depth sum");
        System.out.println(depthSum.reversDepthSum(sample1));
        System.out.println(depthSum.reversDepthSum(sample2));

        System.out.println("Forward depth sum");
        System.out.println(depthSum.forwardDepthSum(sample1));
        System.out.println(depthSum.forwardDepthSum(sample2));

    }
}

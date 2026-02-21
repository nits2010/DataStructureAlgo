#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
        #parse("File Header.java")
import DataStructureAlgo.Java.helpers.*;
import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: ${DATE}
 * Question Title:
 * Link:
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

public class ${NAME} {

public static void main(String[] args) {
    List<Boolean> tests = new ArrayList<>();
    //add tests cases here
    CommonMethods.printAllTestOutCome(tests);
}

private static boolean test(int []nums, int expected) {
    //add print here
    CommonMethods.printTest(new String[]{"nums", "Expected"}, true, nums, expected);

    int output = 0;
    boolean pass, finalPass = true;

    //add logic here
    pass = CommonMethods.compareResultOutCome(output, expected,true);
    finalPass &= pass;

    CommonMethods.printTest(new String[]{"Output", "Pass"}, false, output, pass?"PASS":"FAIL");

    return finalPass;

}
}

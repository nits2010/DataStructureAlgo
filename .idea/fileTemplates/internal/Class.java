#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

import DataStructureAlgo.Java.helpers.CommonMethods;
import DataStructureAlgo.Java.helpers.*;
import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: ${DATE}
 * Question Title:
 * Link: 
 * Description:
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 * @easy
 * @medium
 * @hard
  <p><p>
 * Company Tags
 * -----
 *  <p>
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

    private static boolean test(int[] nums, int k, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"Nums", "k", "Expected"}, true, nums, k, expected);

        int output;
        boolean pass, finalPass = true;

        //add logic here
        
        return finalPass;

    }
}

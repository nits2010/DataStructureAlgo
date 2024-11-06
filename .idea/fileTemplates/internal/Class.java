#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#parse("File Header.java")

/**
 * Author: Nitin Gupta
 * Date:${DATE}
 * Question Category: 
 * Description: 
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 * 
 *
 * <p>
 * Company Tags
 * -----

 *
 * @Editorial
 */

public class ${NAME} {

 public static void main(String[] args) {
        List<Boolean> tests = new ArrayList<>();
        //add tests cases here
   

        CommonMethods.printAllTestOutCome(tests);
    }


    private static boolean tests(String input, int expected) {
        //add print here
        CommonMethods.printTestOutcome(new String[]{"input", "Expected"}, true, input, expected);

        int output;
        boolean pass, finalPass = true;

        Solution solution = new Solution();
##        output = solution.minChanges(input);
        pass = output == expected;
        finalPass &= pass;

        CommonMethods.printTestOutcome(new String[]{"Output", "Pass"}, false, output, pass ? "Pass" : "Fail");
        return finalPass; 

    }
}

package Java.companyWise.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-14
 * Description: https://leetcode.com/discuss/interview-question/357345/uber-phone-screen-sum-to-100/324193
 * Add the mathematical operators + or - before any of the digits in the decimal numeric string 123456789 such that the resulting mathematical expression adds up to 100. Return all possible solutions.
 * <p>
 * There are 12 solutions:
 * <p>
 * 1+2+3-4+5+6+78+9
 * 1+2+34-5+67-8+9
 * 1+23-4+5+6+78-9
 * 1+23-4+56+7+8+9
 * 12+3+4+5-6-7+89
 * 12+3-4+5+67+8+9
 * 12-3-4+5-6+7+89
 * 123+4-5+67-89
 * 123+45-67+8-9
 * 123-4-5-6-7+8-9
 * 123-45-67+89
 * -1+2-3+4+5+6+78+9
 */
public class SumTo100 {

    public static void main(String[] args) {
        List<String> solutions = sumTo(100);
        System.out.println("total solutions: " + solutions.size());
        System.out.println(String.join("\n", solutions));
    }

    /**
     * O(9^9) + O(256 * 2^9 ) as maximum length of a combination is 9 at max when all digits are different and single digit value like {1, 2, 3, 4, 5, 6, 7, 8, 9}
     *
     * We'll have 256 combination of single digit to 9 digit number.
     *
     * @param target
     * @return
     */
    public static List<String> sumTo(int target) {

        final List<String> solution = new ArrayList<>();

        final List<List<Long>> allCombinationFrom0To9 = combination(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        for (List<Long> combination : allCombinationFrom0To9)
            dfs(combination, target, new StringBuilder(), solution, 0, 0);

        return solution;

    }

    private static void dfs(List<Long> combinations, int target, StringBuilder current, List<String> solution, long currentVal, int pos) {

        if (pos == combinations.size()) {

            if (currentVal == target) {
                solution.add(current.toString().substring(1));

            }
            return;

        }

        long v = combinations.get(pos);
        int len = current.length();
        dfs(combinations, target, current.append("+").append(v), solution, currentVal + v, pos + 1);
        current.setLength(len);

        dfs(combinations, target, current.append("-").append(v), solution, currentVal - v, pos + 1);
        current.setLength(len);


    }

    /**
     * Run dfs to build all combination from 0 to 9
     *
     * @return
     *
     * O(9^9)
     */
    private static List<List<Long>> combination(int options[]) {

        List<List<Long>> list = new ArrayList<>();

        int pos = 0;
        combination(options, pos, list, new ArrayList<>());

        return list;
    }


    private static void combination(int[] options, int pos, List<List<Long>> result, List<Long> current) {
        if (pos == options.length) {
            result.add(new ArrayList<>(current));
            return;
        }


        long t = 0;
        for (int i = pos; i < options.length; i++) {
            t = t * 10 + options[i];
            current.add(t);
            combination(options, i + 1, result, current);
            current.remove(current.size() - 1);
        }
    }
}

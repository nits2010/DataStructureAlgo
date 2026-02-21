package DataStructureAlgo.Java.LeetCode2025.ProblemSet.backtrackings.permutations._60;

import java.util.ArrayList;
import java.util.List;

class PermutationSequence_60 {
    private int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public String getPermutation(int n, int k) {
        StringBuilder result = new StringBuilder("");

        List<Integer> items = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            items.add(i);
        }

        int p = 0;
        while (p < k && items.size() > 1) {

            int fact = factorial(items.size() - 1);

            int i = 0;
            for (int item : items) {

                if (p + fact >= k) {
                    result.append(item);
                    items.remove(i);
                    break;
                } else {
                    p += fact;
                }
                i++;
            }
        }

        for (int item : items) {
            result.append(item);
        }
        return result.toString();

    }
}
package DataStructureAlgo.Java.LeetCode2025.ProblemSet.backtrackings.combinationalSum._40;

import java.util.*;

class CombinationSumII_40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

       if (candidates == null || candidates.length == 0)
           return Collections.emptyList();

       List<List<Integer>> response = new ArrayList<>();
       Arrays.sort(candidates);

       combinationSum2(candidates, 0, target, response, new ArrayList<>());
       return response;
   }

   private void combinationSum2(int[] candidates, int i, int target, List<List<Integer>> response, List<Integer> temp) {

       //3. Our goal: when currentSum = target
       if (0 == target) {
           response.add(new ArrayList<>(temp));
           return;
       }

       if (i == candidates.length)
           return;

       //1. Our choices: We can choose a number from the list any number of times and all the numbers
       for (int s = i; s < candidates.length; s++) {

           //if this element is greater than target, then adding it to current sum make bigger than target
           //since,elements are sorted, then all the element after this element are > target
           if (candidates[s] > target)
               break;

           //Avoid duplicates [1,1,7]; once we take the first 1 and found 7 as our solution [1,7] . Then we should not take second 1 as our first element
           //and try to find a solution.
           if (s > i && candidates[s] == candidates[s - 1])
               continue;

           //Our constraints : We can't go beyond target, we can take more element than available in array
           if (target - candidates[s] >= 0) {
               target -= candidates[s];
               temp.add(candidates[s]);

               combinationSum2(candidates, s + 1, target, response, temp);

               //backtrack
               temp.remove(temp.size() - 1);
               target += candidates[s];
           }
       }

   }
}
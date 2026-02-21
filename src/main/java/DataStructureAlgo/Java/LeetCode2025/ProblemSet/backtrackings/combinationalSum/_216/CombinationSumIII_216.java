package DataStructureAlgo.Java.LeetCode2025.ProblemSet.backtrackings.combinationalSum._216;

import java.util.*;
class CombinationSumIII_216 {
    public List<List<Integer>> combinationSum3(int k, int n) {

      /**
       * You can't make sum n=2 when k=3. to run at least n>=k
       * and using [1,9] you can't make sum more than 45.
       * K can not be more than 9 since we have 9 choices only
       */
      if (n < k || n > 45 || k > 9)
          return Collections.emptyList();


      List<List<Integer>> response = new ArrayList<>();
      combinationSum3(k, 1, 0, n, response, new ArrayList<>());
      return response;
  }

  private void combinationSum3(int k, int i, int currentSum, int target, List<List<Integer>> response, List<Integer> temp) {

      //3. Our goal: when currentSum = target & k numbers only
      if (temp.size() == k && currentSum == target) {
            response.add(new ArrayList<>(temp));
            return;
      }

      if (i == 10)
          return;


      //1. Our choices: We can choose a number from the list any number of times and all the numbers
      for (int s = i; s <= 9; s++) {

          //if this element is greater than target, then adding it to current sum make bigger than target
          //since,elements are sorted, then all the element after this element are > target
          if (s > target)
              break;

          //Our constraints : We can't go beyond target, we can take more element than available in array
          if (currentSum + s <= target) {
              currentSum += s;
              temp.add(s);

              combinationSum3(k, s + 1, currentSum, target, response, temp);

              //backtrack
              temp.remove(temp.size() - 1);
              currentSum -= s;
          }
      }

  }
}
package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Arrays;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 10/4/2024
 * Question Category: 2491 Divide Players Into Teams of Equal Skill
 *
 * Description: https://leetcode.com/problems/divide-players-into-teams-of-equal-skill
 * You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
 *
 * The chemistry of a team is equal to the product of the skills of the players on that team.
 *
 * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: skill = [3,2,5,1,3,4]
 * Output: 22
 * Explanation:
 * Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
 * The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
 * Example 2:
 *
 * Input: skill = [3,4]
 * Output: 12
 * Explanation:
 * The two players form a team with a total skill of 7.
 * The chemistry of the team is 3 * 4 = 12.
 * Example 3:
 *
 * Input: skill = [1,1,2,3]
 * Output: -1
 * Explanation:
 * There is no way to divide the players into teams such that the total skill of each team is equal.
 *
 *
 * Constraints:
 *
 * 2 <= skill.length <= 105
 * skill.length is even.
 * 1 <= skill[i] <= 1000
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 * @medium
 * @Array
 * @HashTable
 * @TwoPointers
 * @Sorting
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/editorial/
 */
public class DividePlayersIntoTeamsOfEqualSkill_2491 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new int[]{3,2,5,1,3,4}, 22);
        test &= test(new int[]{3,4}, 12);
        test &= test(new int[]{1,1,2,3}, -1);
        CommonMethods.printResult(test);
    }


    private static boolean test(int[] skill, long expected) {
        System.out.println("----------------------------------");
        System.out.println("Skill : " + Arrays.toString(skill));
        System.out.println("Expected :" + expected);

        long actual = 0;
        boolean pass ;
        boolean finalPass = true;

        Solution_Sorting_TwoPointers solution = new Solution_Sorting_TwoPointers();
        actual = solution.dividePlayers(skill);
        pass = actual == expected;
        System.out.println("Output Two pointers : " + actual + " Pass : " + (pass ? "PASS" : "FAIL"));
        finalPass &= pass;

        Solution_HashTable solutionHashTable = new Solution_HashTable();
        actual = solutionHashTable.dividePlayers(skill);
        pass = actual == expected;
        System.out.println("Output Hashtable : " + actual + " Pass : " + (pass ? "PASS" : "FAIL"));
        finalPass &= pass;


        Solution_HashTable2 solutionHashTable2 = new Solution_HashTable2();
        actual = solutionHashTable2.dividePlayers(skill);
        pass = actual == expected;
        System.out.println("Output Hashtable2 : " + actual + " Pass : " + (pass ? "PASS" : "FAIL"));
        finalPass &= pass;
        return finalPass;

    }


    /**
     * The array can only be divided into teams of size 2 with equal skill and total n/2 team, only if
     * 1. The Total sum of the skill of all players is even
     * 2. The Minimum possible skill sum of a team would be the minimum skill + maximum skill. If any team violates it, then n/2 pairs are not possible.
     *
     * The one way to find the minimum skill sum of a team is to sort the array and then use two pointers.The pointers will point at each corner of the array
     * and start calculating the sum of the skill of the team. If any pair violates the minimum skill sum, then n/2 pairs are not possible.
     *
     * T/S: O(n*log(n)) / o(1)
     */
    static class Solution_Sorting_TwoPointers {


         public long dividePlayers(int[] skill) {
             if(skill == null || skill.length == 0)
                 return -1;

             //Sort the array in ascending order
             Arrays.sort(skill);
             int n = skill.length;

             //two pointers
             int i = 0, j = n - 1;
             long chemistry = 0 ;

             //Minimum skill sum of a team would be the minimum skill + maximum skill.
             int target = skill[i] + skill [j] ;
             chemistry = (long) skill[i] * skill[j] ;

             //move to next pair
             i++;
             j--;

             while(i<j){
                 //current skill sum
                 int sum = skill[i] + skill [j] ;

                 //if any pair violates the minimum skill sum, then n/2 pairs are not possible.
                 if(sum != target)
                     return -1;

                 chemistry += (long) skill[i] * skill[j] ;
                 i++;
                 j--;
             }
             return chemistry;
         }
    }

    /**
     * In sorting solution, we have observed that all the skills that are same will pull together next to each other.
     * And a pair will be separated out in each corner of the array Left to right(i pointer) and right to left(j pointer).
     *
     * Now if we know the minimum target skill, then we can find the remaining skill for each skill by looking up in the array and find its present.
     * Means, if minimum Target Skill is noted by T and the current skill by cs then
     * remainingSKill = (T - cs)
     * which can be looked up in the array. For which we can use the hashmaps/arrays (since skill is limited to 1000 only).
     *
     * Now the minimum target skill is nothing but the sum of a pair skill. If we know the totalSkill (sum of elements in array ) then
     * we can find the target skill as we need to divide the entire array in equal pairs and a total pair should be n/2;
     * Hence the target skill = totalSkill / (n/2);
     *
     * Algorithm:
     * 1. Get the total skill of all players, also put each skill in the map with frequency
     * 2. Check if we can divide the array in n/2 pairs
     *    a. This only be possible if totalSkill % (n/2) == 0. If yes, then get the minimum target skill
     *    b. If not, then return -1
     * 3. Now we know the minimum target skill. Now we can find the remaining skill for each skill by looking up in the array and finding its present.
     *
     *
     * T/S: O(n) / o(1)
     */
    static class Solution_HashTable {
          public long dividePlayers(int[] skill) {
             if(skill == null || skill.length == 0)
                 return -1;

             final int N = 1001 ;
             int n = skill.length;
             long chemistry = 0 ;

             //skill freq map
             int []skillFreq = new int[N] ;
             int totalSkill = 0;

             for(int sk : skill){

                 //count the freq of each skill
                 skillFreq[sk]++;

                 totalSkill += sk;
             }

             //[3,2,5,1,3,4] n = 6
             // groups = (1, 5), (2, 4), (3, 3) ; total groups = 3
             //total = 18 , targetSkill = 18 / (6/2) = 18 / 3 = 6
             //means each pair should add up to 6 only.
             int targetSkill = totalSkill / (n/2) ;

             //can we divide in n / 2 groups ?
             if ( totalSkill % (n/2) != 0 )
                 return -1;


             for(int cSkill : skill){

                 int remainingSkill = targetSkill - cSkill;

                 //if this pair possible, then remainingSkill should be there in skillFreq
                 if(skillFreq[remainingSkill] == 0)
                     return -1;

                 //remove used skill, as there could be multiple pairs of same skill
                 skillFreq[remainingSkill]--;

                 chemistry += (long) cSkill * remainingSkill ;
             }

             //we would have counted each pair two times, so divide by 2
             return chemistry/2;

         }
    }

    /**
     * This solution is ame as above, instead of checking each remaining Skill, we can check its frequency
     * This is because, for every skill 'cs' if there exist another skill 'rs' such that 'rs' + 'cs' = 'targetSkill'
     * then all skill which is same as 'cs' will always pair with 'rs' only to satisfy the targetSkill.
     *
     * Hence, if the frequency of 'rs' and 'cs' match then only the pair will be possible. These are duplicate pairs.
     */
    static class Solution_HashTable2 {
        public long dividePlayers(int[] skill) {
            if(skill == null || skill.length == 0)
                return -1;
            final int N = 1001 ;
            int n = skill.length;

            long chemistry = 0 ;

            int []skillFreq = new int[N] ;
            int totalSkill = 0;

            for(int sk : skill){

                //count the freq of each skill
                skillFreq[sk]++;

                totalSkill += sk;
            }

            //[3,2,5,1,3,4] n = 6
            // groups = (1, 5), (2, 4), (3, 3) ; total groups = 3
            //total = 18 , targetSkill = 18 / (6/2) = 18 / 3 = 6
            //means each pair should add up to 6 only.
            int targetSkill = totalSkill / (n/2) ;

            //can we divide in n / 2 groups ?
            if ( totalSkill % (n/2) != 0 )
                return -1;


            for(int cSkill : skill){

                int remainingSkill = targetSkill - cSkill;
                int cSkillFreq = skillFreq[cSkill];
                int remainingSkillFreq = skillFreq[remainingSkill];

                //if this pair possible, then remainingSkill should be there in skillFreq
                if(cSkillFreq!=remainingSkillFreq)
                    return -1;

                // //remove used skill
                // skillFreq[remainingSkill]--;

                chemistry += cSkill * remainingSkill ;
            }

            System.out.println(chemistry);
            return chemistry/2;

        }
    }
}

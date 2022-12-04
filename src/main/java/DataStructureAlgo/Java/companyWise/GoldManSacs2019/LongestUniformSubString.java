package DataStructureAlgo.Java.companyWise.GoldManSacs2019;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-01
 * Description:
 * LongestUniformSubString
 * return start and length
 *
 * input: 10000111
 * output 0000 [ 1, 4 ]
 *
 * input: aabbbbbCdAA
 * output: bbbbb [ 2 , 5 ]
 *
 *
 */
import java.util.*;

public class LongestUniformSubString {

    private static final Map<String, int[]> testCases = new HashMap<>();

    static int[] longestUniformSubstring(String input){


        if(input.isEmpty())
            return new int[]{ -1, 0 };

        int longestStart = 0;
        int longestLength = 0;
        int start = 0;
        int end = 0;

        int n = input.length();
        int counter = 0;
        Map<Character, Integer> seen = new HashMap<>();

        for (int i = 0; i<n; i++){

            char c = input.charAt(i);

            if(!seen.containsKey(c)){
                seen.put(c, 1) ;
                counter ++;
            }else {
                seen.put(c , seen.getOrDefault(c, 0)+1) ;

            }


            if(counter ==1 ) {
                if(longestLength< i-start+1){
                    longestLength = i-start+1;
                    longestStart = start;
                }

            }

            if(counter > 1){

                while (counter > 1){

                    char x = input.charAt(start);
                    if(seen.get(x) > 0){
                        seen.put(x , seen.getOrDefault(x, 1)-1) ;
                    }

                    if(seen.get(x) == 0 ){
                        counter--;
                        seen.remove(x);

                    }


                    start++;

                }
            }

        }

        // todo: implement the longestUniformSubstring logic
        return new int[]{ longestStart, longestLength };
    }

    public static void main(String[] args) {
        testCases.put("", new int[]{-1, 0});
        testCases.put("10000111", new int[]{1, 4});
        testCases.put("aabbbbbCdAA", new int[]{2, 5});
        // todo: implement more tests, please
        // feel free to make testing more elegant

        boolean pass = true;
        for(Map.Entry<String,int[]> testCase : testCases.entrySet()){
            int[] result = longestUniformSubstring(testCase.getKey());
            pass = pass && (Arrays.equals(result, testCase.getValue()));
        }
        if(pass){
            System.out.println("All tests pass!");
        } else {
            System.out.println("At least one failure! :( ");
        }
    }
}
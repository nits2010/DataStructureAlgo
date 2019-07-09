package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-09
 * Description:
 */
public class HammingDistance {
}

class SolutionHammingDistance {

    public int totalHammingDistance(int[] nums) {

        // return totalHammingDistanceNaive(nums);
        return totalHammingDistanceOptimized(nums);


    }


    private int totalHammingDistanceOptimized(int [] nums){
        int count = 0;
        int hammers = 0 ;
        for (int i=0; i<32; i++){

            count =0 ;
            for (int j = 0; j<nums.length; j++){

                if ( (nums[j] & (1<<i))!=0)
                    count++;
            }
            hammers += count * (nums.length - count);
        }
        return hammers;
    }

    private int totalHammingDistanceNaive(int [] nums){
        int count = 0;

        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++)
                count+= hammingDistance(nums[i], nums[j] );
        }
        return count;
    }
    public int hammingDistance(int x, int y) {

        int hammer = x ^ y; // using this, it will change the bits where they are change

        int count = 0;

        //count the bits which are set :)
        while (hammer > 0) {
            count += (hammer % 2);
            hammer /= 2;
        }
        return count;

    }
}
package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-23
 * Description:
 */
public class MaxProductSubArray {

    public static void main (String args[]){
        int nums[] = {-2,0,-1};
        int nums2[] = {-2,-1,0,-4} ;
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct(nums2));
    }
    public static int maxProduct(int[] nums){
        return maxProductEasy(nums);
    }

    public static int maxProductEasy(int[] nums){

        int min = nums[0] ;
        int max = nums[0] ;
        int maxProd = nums[0];

        for ( int i = 1; i< nums.length ; i++){

            if ( nums[i] < 0){
                //current element is neg, this means whatever max is , by multiplying it with a neg
                // number make it min
                int x = min;
                min  = max ;
                max = x;
            }


            //if current number is >=0; then either our answer is 0 or max pos
            max = Math.max ( nums[i] , nums[i]*max) ;
            min = Math.min ( nums[i], nums[i] * min) ;

            maxProd = Math.max(maxProd, max);


        }
        return maxProd;
    }

    public static int maxProductWithCornerCaseFails(int[] nums) {

        if(nums.length == 1)
            return nums[0] ;

        int maxPositiveProduct = 1;
        int maxNegitiveProduct = 1 ; //keeping at least 1
        int max = Integer.MIN_VALUE;
        int maxNeg = Integer.MIN_VALUE ;

        /*
       Case 1:  +ve * +ve  = +ve  [ means if we multipley both positive numbers we get bigger positive product]
       case 2:  -ve * +ve = -ve  [ means if we multipley  positive and neg numbers we get bigger negetive product]
       case 3:  -ve * -ve = +ve  [ means if we multipley  neg and neg numbers we get bigger positive product]
        */

        for (int i=0 ; i<nums.length ; i++){

            if ( nums[i] > 0) {

                //current number is positive
                maxPositiveProduct = maxPositiveProduct* nums[i] ;
                maxNegitiveProduct = Math.min( 1, maxNegitiveProduct*nums[i]) ;

            }else if (nums[i] < 0){
                //current number is negetive

                //max positive will be obtained by mul both neg
                int temp = maxPositiveProduct;

                maxPositiveProduct = Math.max ( maxNegitiveProduct * nums[i] , 1);
                maxNegitiveProduct = temp * nums[i] ;

                maxNeg = Math.max (maxNeg, nums[i]);
            }else {

                maxPositiveProduct = maxNegitiveProduct = 1;
            }

            max= Math.max ( max, maxPositiveProduct);
        }


        return max;
    }
}

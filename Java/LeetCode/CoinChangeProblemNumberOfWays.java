package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description: https://leetcode.com/problems/coin-change-2/
 */
public class CoinChangeProblemNumberOfWays {

    public static void main(String args[]){
        int coins[] = {1, 2, 5};
        System.out.println(change(5, coins));
    }

    //total[i][j] = number of ways to make sum i using j coins
    //total[i][j] = total[i][j-1] + total[i-coins[j]][j]
    // total[i][j-1] we dont take the coin
    //total[i-coin[j]][j] we take the coin j then we need to find the sum of i-coin[j]
    //total[0][j(0...coins.lenght-1)] = 1

    public  static int change(int amount, int[] coins) {

        int total[] = new int [amount+1] ;
        total[0] = 1;
        int n = coins.length;

        for (int c=0; c<n; c++){
            for (int i = coins[c]; i<=amount; i++)
                total[i] += total[i -coins[c]] ;
        }
        return total[amount];
    }
}

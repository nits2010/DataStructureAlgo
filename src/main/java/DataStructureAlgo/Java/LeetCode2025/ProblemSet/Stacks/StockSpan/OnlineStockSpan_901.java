package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Stacks.StockSpan;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 901. Online Stock Span [medium]
 * Description: https://leetcode.com/problems/online-stock-span
 * <p>
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 * <p>
 * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
 * <p>
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
 * Implement the StockSpanner class:
 * <p>
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 * <p>
 * <p>
 * Explanation
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
 * stockSpanner.next(85);  // return 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= price <= 105
 * At most 104 calls will be made to next.
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @Stack
 * @Design
 * @MonotonicStack
 * @DataStream
 * <p>
 * Company Tags
 * -----
 * @Amazon
 * @Bloomberg
 * @Adobe
 * @Editorial
 */

public class OnlineStockSpan_901 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new int[]{100, 80, 60, 70, 60, 75, 85}, new int[]{1, 1, 1, 2, 1, 4, 6});
        testResult &= test(new int[]{10, 4, 5, 90, 120, 80}, new int[]{1, 1, 2, 4, 5, 1});
        testResult &= test(new int[]{10, 10, 10, 10}, new int[]{1, 2, 3, 4});
        testResult &= test(new int[]{28, 14, 28, 35, 46, 53, 66, 80, 87, 88}, new int[]{1, 1, 3, 4, 5, 6, 7, 8, 9, 10});


        System.out.println((testResult ? "All passed" : "Failed"));

    }

    private static boolean test(int[] prices, int[] expected) {
        System.out.println("\n-----Test case: " + Arrays.toString(prices) + " expected " + Arrays.toString(expected));
        int[] output = new int[prices.length];
        boolean testResult = true;
        StockSpanner stockSpanner = new StockSpanner();
        for (int i = 0; i < prices.length; i++) {
            output[i] = stockSpanner.next(prices[i]);
            if (output[i] != expected[i])
                testResult = false;
        }
        System.out.println("Output: " + Arrays.toString(output) + " Test Pass " + (testResult ? "All passed" : "Failed"));
        return testResult;

    }
}



class StockSpanner {

    static class Node {
        final int stockPrice;
        final int day;

        Node(int price, int day) {
            this.stockPrice = price;
            this.day = day;
        }
    }

    private final Stack<Node> stack;
    int day = 0;

    public StockSpanner() {
        stack = new Stack<>();
    }

    /**
     * [100, 80, 60, 70, 60, 75, 85] => {1,1,1,2,1,4,6}
     * day = 0 -> 100 {{100, 0} -> day = 1 => 1
     * day = 1 -> 80 {{100,0}, {80,1}} -> day =2 =>span = 1-0 = 1
     * day = 2 -> 60 {{100,0}, {80,1}, {60,2}} -> day = 3 => span = 2-1 = 1
     * day = 3 -> 70 {{100,0}, {80,1}, {70,3}} -> {{60,2}} -> day = 4 => span = 3 - 1 = 2
     * day = 4 -> 60 {{100,0}, {80,1}, {70,3, {60,4}}} -> day = 5 => span = 4 - 3 = 1
     * day = 5 -> 75 {{100,0}, {80,1}, {75,5}}} -> {{60,4}, {70,3}} day = 6 => span = 5 - 1 = 4
     * day = 6 -> 85 {{100,0}, {85,6}} -> {{75,5}, {80,1}} day = 7 => span = 6 - 0 = 6
     *
     * @param price
     * @return span
     */
    public int next(int price) {
        int span = 1; // own span


        //for which the stock price was "less" than or "equal to" the price of that day.
        while (!stack.isEmpty() && stack.peek().stockPrice <= price)
            stack.pop(); //pop all the element which are less than current price

        if (stack.isEmpty()) {
            span = span + day;
        } else {
            int lastStockDay = stack.peek().day; //last day when the stock price was greater than current price
            span = day - lastStockDay; //current day - last day + 1
        }


        stack.push(new Node(price, day++));
        return span;
    }
}


class StockSpannerNonStreaming {

    public int[] stockSpan(int[] price) {
        if(price == null || price.length == 0)
            return price;
       final Stack<Integer> span = new Stack<>();
       final int[] spanPrice = new int[price.length];

       for (int i = 0; i<price.length; i++){
           while(!span.isEmpty() && price[span.peek()] <= price[i])
               span.pop();

           if(span.isEmpty()){
                spanPrice[i] = i+1;
           }else{
               spanPrice[i] = i - span.peek();
           }
           span.push(i);
       }

       return spanPrice;

    }
}
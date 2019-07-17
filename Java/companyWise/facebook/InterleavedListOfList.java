package Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 02/04/19
 * Description:https://www.careercup.com/question?id=5156596605779968
 * <p>
 * Interleave list of lists in Java
 * Example:
 * input = [[1,2,3], [9, 0], [5], [-4,-5,-2,-3,-1]];
 * output = [1,9,5,-4,2,0,-5,3,-2,-3,-1]
 */
public class InterleavedListOfList {

    static class Helper {
        public int listIndex = 0;
        public int selfIndex = 0;

        public Helper(int listIndex) {
            this.listIndex = listIndex;
        }

        @Override
        public String toString() {
            return "{" +
                    "listIndex=" + listIndex +
                    ", selfIndex=" + selfIndex +
                    '}';
        }
    }

    public static void main(String args[]) {

        test1();
        test2();
        test3();
        test4();
        test5();
    }

    private static void test5(){
        System.out.println("test5");
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> one = new ArrayList<>();

        List<Integer> two = new ArrayList<>();

        List<Integer> three = new ArrayList<>();


        List<Integer> four = new ArrayList<>();

        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);

        System.out.println(interleavedList(input));
        System.out.println(interleavedUsingDelegatingIndexing(input));
    }

    private static void test4() {
        System.out.println("test4");
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> one = new ArrayList<>();
        one.add(1);

        List<Integer> two = new ArrayList<>();
        two.add(9);

        List<Integer> three = new ArrayList<>();


        List<Integer> four = new ArrayList<>();

        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);

        System.out.println(interleavedList(input));
        System.out.println(interleavedUsingDelegatingIndexing(input));

    }

    private static void test3() {
        System.out.println("test3");
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> one = new ArrayList<>();
        one.add(1);

        List<Integer> two = new ArrayList<>();
        two.add(9);

        List<Integer> three = new ArrayList<>();
        three.add(5);


        List<Integer> four = new ArrayList<>();

        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);

        System.out.println(interleavedList(input));
        System.out.println(interleavedUsingDelegatingIndexing(input));

    }

    private static void test2() {
        System.out.println("test2");
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> one = new ArrayList<>();
        one.add(1);
        one.add(2);
        one.add(3);

        List<Integer> two = new ArrayList<>();
        two.add(9);
        two.add(0);

        List<Integer> three = new ArrayList<>();
        three.add(5);
        three.add(9);


        List<Integer> four = new ArrayList<>();
        four.add(-4);

        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);

        System.out.println(interleavedList(input));
        System.out.println(interleavedUsingDelegatingIndexing(input));
    }

    private static void test1() {
        System.out.println("test1");
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> one = new ArrayList<>();
        one.add(1);
        one.add(2);
        one.add(3);

        List<Integer> two = new ArrayList<>();
        two.add(9);
        two.add(0);

        List<Integer> three = new ArrayList<>();
        three.add(5);


        List<Integer> four = new ArrayList<>();
        four.add(-4);
        four.add(-5);
        four.add(-2);
        four.add(-3);
        four.add(-1);

        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);


        System.out.println(interleavedList(input));
        System.out.println(interleavedUsingDelegatingIndexing(input));
    }

    /**
     * iterate through list, and find the value at the given "index" in the list;
     * increase the index 1 by 1 once you complete a round.
     * <p>
     * total round need to complete would = max size of inner list
     *
     * @param lists
     * @return
     */
    private static List<Integer> interleavedList(List<List<Integer>> lists) {

        List<Integer> interleaveList = new ArrayList<>();

        int maxSize = 0;

        for (List<Integer> list : lists)
            maxSize = Math.max(maxSize, list.size());

        int index = 0;
        while (index < maxSize) {
            for (int i = 0; i < lists.size(); i++) {
                if (index < lists.get(i).size()) {
                    interleaveList.add(lists.get(i).get(index));
                }
            }
            index++;
        }


        return interleaveList;
    }


    /**
     * Using iterator and delegating the task for index to an object
     *
     * @param input
     * @return
     */
    private static List<Integer> interleavedUsingDelegatingIndexing(List<List<Integer>> input) {

        int totalLists = input.size();

        //We use this list to iterate over input
        List<Helper> helpers = getIndexList(totalLists);

        List<Integer> result = new ArrayList<>();

        //We remove all the helper once their corresponding list exhaust


        while (true) {
            ListIterator<Helper> listIterator = helpers.listIterator();
            while (listIterator.hasNext()) {

                Helper h = listIterator.next();

                //get the inner list by listIndex
                List<Integer> inner = input.get(h.listIndex);

                if (h.selfIndex < inner.size()) {
                    result.add(inner.get(h.selfIndex++));
                } else {
                    //If all element traversed, remove it for next iteration
                    listIterator.remove();
                }

            }

            if (helpers.isEmpty())
                break;

        }


        return result;

    }

    private static List<Helper> getIndexList(int totalLists) {
        List<Helper> helpers = new ArrayList<>(totalLists);

        for (int i = 0; i < totalLists; i++) {
            helpers.add(new Helper(i));
        }

        return helpers;
    }

}

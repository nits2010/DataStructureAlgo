package Java.companyWise.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 02/04/19
 * Description:https://www.careercup.com/question?id=5156596605779968
 */
public class InterleavedList {

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

    }


    private static List<Helper> getIndexList(int totalLists) {
        List<Helper> helpers = new ArrayList<>(totalLists);

        for (int i = 0; i < totalLists; i++) {
            helpers.add(new Helper(i));
        }

        return helpers;
    }

    private static List<Integer> interleavedList(List<List<Integer>> input) {

        int totalLists = input.size();

        //We use this list to iterate over input
        List<Helper> helpers = getIndexList(totalLists);
        int x = totalLists;

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
}

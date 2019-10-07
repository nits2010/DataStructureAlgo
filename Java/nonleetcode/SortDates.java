package Java.nonleetcode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-15
 * Description: https://www.geeksforgeeks.org/sort-an-array-of-string-of-dates-in-ascending-order/
 * Given an array of strings dates[], the task is to sort these dates in ascending order.
 * Note: Each date is of the form dd mmm yyyy where:
 * <p>
 * Domain of dd is [0-31].
 * Domain of mmm is [Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec].
 * And, yyyy is a four digit integer.
 * Examples:
 * <p>
 * Input: dates[] = {“01 Mar 2015”, “11 Apr 1996”, “22 Oct 2007”}
 * Output:
 * 11 Apr 1996
 * 22 Oct 2007
 * 01 Mar 2015
 * <p>
 * Input: dates[] = {“03 Jan 2018”, “02 Jan 2018”, “04 Jan 2017”}
 * Output:
 * 04 Jan 2017
 * 02 Jan 2018
 * 03 Jan 2018
 */
public class SortDates {

    //01 Mar 2015


    static Map<String, Integer> sortMonths() {
        Map<String, Integer> monthsMap = new HashMap<>();
        monthsMap.put("Jan", 1);
        monthsMap.put("Feb", 2);
        monthsMap.put("Mar", 3);
        monthsMap.put("Apr", 4);
        monthsMap.put("May", 5);
        monthsMap.put("Jun", 6);
        monthsMap.put("Jul", 7);
        monthsMap.put("Aug", 8);
        monthsMap.put("Sep", 9);
        monthsMap.put("Oct", 10);
        monthsMap.put("Nov", 11);
        monthsMap.put("Dec", 12);

        return monthsMap;
    }

    static int comparator(String a, String b, Map<String, Integer> monthsMap) {

        int year1 = Integer.parseInt(a.substring(7));
        int year2 = Integer.parseInt(b.substring(7));

        if (year1 != year2) {

            return year1 - year2;
        }


        int month1 = monthsMap.get(a.substring(3, 6));
        int month2 = monthsMap.get(b.substring(3, 6));

        if (month2 != month1)
            return month1 - month2;


        int date1 = Integer.parseInt(a.substring(0, 2));
        int date2 = Integer.parseInt(b.substring(0, 2));

        return date1 - date2;

    }

    public static void sortDates(List<String> dates) {

        List<String> solution = new ArrayList<>(dates);

        Map<String, Integer> monthsMap = new HashMap<>();
        monthsMap.put("Jan", 1);
        monthsMap.put("Feb", 2);
        monthsMap.put("Mar", 3);
        monthsMap.put("Apr", 4);
        monthsMap.put("May", 5);
        monthsMap.put("Jun", 6);
        monthsMap.put("Jul", 7);
        monthsMap.put("Aug", 8);
        monthsMap.put("Sep", 9);
        monthsMap.put("Oct", 10);
        monthsMap.put("Nov", 11);
        monthsMap.put("Dec", 12);
        dates.sort((o1, o2) -> comparator(o1, o2, monthsMap));


    }

    public static void main(String[] args) {
        List<String> dates = Arrays.asList("24 Jul 2017", "25 Jul 2017", "11 Jun 1996",
                "01 Jan 2019", "12 Aug 2005", "01 Jan 1997");

        sortDates(dates);
        System.out.println(dates);

    }


}

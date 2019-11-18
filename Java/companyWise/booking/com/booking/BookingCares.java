package Java.companyWise.booking.com.booking;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
 * 1 1481122000 1481122020
 * 3 1481122000 1481122020
 * 1 1481122020 1481122040
 * 2 1481122020 1481122040
 * 3 1481122040 1481122060
 * 1 1481122050 1481122060
 * 3 1481122070 1481122090
 */
public class BookingCares {


    private static List<List<Integer>> input1(){
        List<List<Integer>> employeeCalls = new ArrayList<>();

        employeeCalls.add(Arrays.asList(1, 1481122000, 1481122090));
        employeeCalls.add(Arrays.asList(1, 1481122100, 1481122200));
        employeeCalls.add(Arrays.asList(1, 1481122300, 1481122500));

        employeeCalls.add(Arrays.asList(2, 1481122000, 1481122300));
        employeeCalls.add(Arrays.asList(2, 1481122100, 1481122300));
        employeeCalls.add(Arrays.asList(2, 1481122500, 1481122300));


        employeeCalls.add(Arrays.asList(3, 1481122000, 1481122100));
        employeeCalls.add(Arrays.asList(3, 1481122200, 1481122400));
        employeeCalls.add(Arrays.asList(3, 1481122900, 1481122400));

        return employeeCalls;
    }

    private static List<List<Integer>> input2() {

        List<List<Integer>> employeeCalls = new ArrayList<>();

        employeeCalls.add(Arrays.asList(1, 1481122000, 1481122020));
        employeeCalls.add(Arrays.asList(3, 1481122000, 1481122020));
        employeeCalls.add(Arrays.asList(1, 1481122020, 1481122040));
        employeeCalls.add(Arrays.asList(2, 1481122020, 1481122040));
        employeeCalls.add(Arrays.asList(3, 1481122040, 1481122060));
        employeeCalls.add(Arrays.asList(1, 1481122050, 1481122060));
        employeeCalls.add(Arrays.asList(3, 1481122070, 1481122090));

        return employeeCalls;

    }
    public static void main(String[] args) {


        System.out.println(employeeWithLesserThanKBreaks(input1(), 0));
        System.out.println(employeeWithLesserThanKBreaks(input2(), 10));
    }

    public static List<List<Integer>> employeeWithLesserThanKBreaks(List<List<Integer>> employeeCalls, int k) {


        if (employeeCalls.isEmpty() || k <= 0)
            return Collections.EMPTY_LIST;

        Map<Integer, List<List<Integer>>> map = new HashMap<>();

        for (List<Integer> emp : employeeCalls) {

            int id = emp.get(0);

            if (!map.containsKey(id))
                map.put(id, new ArrayList<>());

            List<Integer> subList = emp.subList(1, emp.size());
            map.get(id).add(subList);

        }


        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (Integer id : map.keySet()) {

            List<List<Integer>> sch = map.get(id);

            int breaks = getBreaks(sch);

            if (breaks < k)
                res.add(Arrays.asList(id, breaks));

            if (breaks > k)
                temp.add(id);

        }

//        Collections.sort(list, Collections.reverseOrder());
//        if(temp.size() == map.keySet().size())
//            return Collections.EMPTY_LIST;

        return res;

    }

    private static int getBreaks(List<List<Integer>> sch) {

        if (sch.isEmpty())
            return 0;

        if (sch.size() == 1)
            return 0;


        int end = sch.get(0).get(1);
        int breaks = 0;

        for (int i = 1; i < sch.size(); i++) {
            int currEnd = sch.get(i).get(1);
            int currStart = sch.get(i).get(0);

            if (end - currStart != 0)
                breaks++;

            end = currEnd;


        }
        return breaks;
    }



}

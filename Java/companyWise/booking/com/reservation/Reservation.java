package Java.companyWise.booking.com.reservation;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
 *
 */
public class Reservation {


    public static void main(String[] args) {

//        List
//        System.out.println(
//                missingReservations(
//                        Arrays.asList(Arrays.asList(1234, 532632), Arrays.asList(234, 632633), Arrays.asList(2354, 732634),
//                                Arrays.asList(Arrays.asList(1234, 532632), Arrays.asList(234, 632633), Arrays.asList(458, 642633), Arrays.asList(2354, 732634)))
//                ));
    }

    public static List<Integer> missingReservations(List<List<Integer>> firstReservationList, List<List<Integer>> secondReservationList) {

        Set<Integer> set = new HashSet<>();

        for (List<Integer> list : firstReservationList) {
            set.add(list.get(0));
        }


        List<List<Integer>> intersection = new ArrayList<>();

        for (List<Integer> list : secondReservationList) {
            if (!set.contains(list.get(0)))
                intersection.add(list);
        }

        Collections.sort(intersection, (Comparator.comparingInt(o -> o.get(1))));

        List<Integer> response = new ArrayList<>();
        for (List<Integer> list : intersection)
            response.add(list.get(0));

        return response;

    }
}

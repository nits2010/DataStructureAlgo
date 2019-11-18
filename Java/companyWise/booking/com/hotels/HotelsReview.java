package Java.companyWise.booking.com.hotels;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-31
 * Description:
 */
public class HotelsReview {

    public static void main(String[] args) {

        String keywords = "A B C D E F G";

        List<Integer> hotel_ids = Arrays.asList(1, 2, 1);
        List<String> reviews = Arrays.asList("A B C A B C W W P WETR ", "A B C A SD XD", "A B dC A SD XD");

        sort_hotels(keywords, hotel_ids, reviews);


    }

    public static List<Integer> sort_hotels(String keywords, List<Integer> hotel_ids, List<String> reviews) {
        String[] words = keywords.split(" ");

        Set<String> dict = new HashSet();

        for (String word : words) {

            dict.add(word.toLowerCase());

        }

        Map<Integer, List<String>> countMap = new HashMap<>();
        Map<Integer, Integer> rev = new HashMap<>();

        for (int i = 0; i < hotel_ids.size(); i++) {

            if (!countMap.containsKey(hotel_ids.get(i)))
                countMap.put(hotel_ids.get(i), new ArrayList<>());

            countMap.get(hotel_ids.get(i)).add(reviews.get(i));
        }

        List<Integer> response = new ArrayList<>();

        for (Integer id : countMap.keySet()) {

            List<String> review = countMap.get(id);

            for (String s : review) {

                String x[] = s.split(" ");

                for (String y : x) {

//                    y = y.replaceAll("[$,.!?]", "").toLowerCase();

                    y = y.toLowerCase();
                    if (dict.contains(y))
                        rev.put(id, rev.getOrDefault(id, 0) + 1);
                }
            }
        }

        final List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(rev.entrySet());


        Collections.sort(entries, ((o1, o2) -> {
            int a = o1.getValue();
            int b = o2.getValue();
            return b - a;
        }));

        for (int i = 0; i < entries.size(); i++) {
            response.add(entries.get(i).getKey());

        }

        System.out.println(response);
        return response;

    }
}

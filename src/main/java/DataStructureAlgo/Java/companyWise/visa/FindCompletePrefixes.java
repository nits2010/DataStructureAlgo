package DataStructureAlgo.Java.companyWise.visa;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-15
 * Description: Given List of names and list of query.
 * Find count corresponding to each query for which there is prefix available names list.
 * Names: ["Nitin", "NitinGupta", "Adarsh", "AdarshK"]
 * Query["Nitin", "Adarsh"]
 * <p>
 * Ans: [1, 1]
 * As "Nitin" is prefix of both "Nitin", "NitinGupta" but "Nitin" will be discarded as its a complete string.
 */
public class FindCompletePrefixes {

    public static void main(String[] args) {


    }


    public static List<Integer> findCompletePrefixes(List<String> names, List<String> query) {


        List<Integer> soution = new ArrayList<>();


        for (String quer : query) {

            int count = 0;

            for (String name : names) {

                if (quer.length() == name.length())
                    continue;

                if (name.startsWith(quer))
                    count++;
            }

            soution.add(count);
        }

        return soution;

    }

}

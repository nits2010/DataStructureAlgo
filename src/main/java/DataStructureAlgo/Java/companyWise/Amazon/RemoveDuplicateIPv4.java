package DataStructureAlgo.Java.companyWise.Amazon;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-05
 * Description:https://aonecode.com/aplusplus/interviewctrl/getInterview/5416219315773457606
 * <p>
 * You are given a huge Array and each element in the array contains a string representing an IPv4 address.
 * Remove all the duplicate Ipv4 s.t. keeping the first occurrence and removing rest of them.
 * Assume removing any IPv4 from list take at most O(1) time.
 * <p>
 * Try to optimize memory and time
 */
public class RemoveDuplicateIPv4 {
    public static void main(String[] args) {

        test(Arrays.asList("205.191.234.12", "23.90.78.23", "23.91.78.23", "234.90.78.23", "23.90.78.23", "205.191.234.12"));

        test(Arrays.asList("205.191.234.12", "23.90.78.23", "23.91.78.23", "234.90.78.23", "23.90.78.23", "205.191.234.12",
                "205.121.234.54", "191.90.89.123", "234.65.89.90", "12.23.34.55"));


    }

    private static void test(List<String> ipv4Addresses) {
        RemoveDuplicateIPv4UsingSet usingSet = new RemoveDuplicateIPv4UsingSet();
        System.out.println("Remove using Set -> " + usingSet.removeDuplicates(ipv4Addresses));

        RemoveDuplicateIPv4UsingFind usingFind = new RemoveDuplicateIPv4UsingFind();
        System.out.println("Remove using find -> " + usingFind.removeDuplicates(ipv4Addresses));

        RemoveDuplicateIPv4UsingBitMask usingBitMask = new RemoveDuplicateIPv4UsingBitMask();
        System.out.println("Remove using bit mask -> " + usingBitMask.removeDuplicates(ipv4Addresses));


    }
}

/**
 * O(n)/ O(n)
 * Wost case occurred when there is no duplicate, then we'll end up storing all the ipv4
 * <p>
 * As n >>> huge then this is not scalable solution in terms of memory
 */
class RemoveDuplicateIPv4UsingSet {

    public List<String> removeDuplicates(List<String> ipv4Addresses) {
        if (null == ipv4Addresses || ipv4Addresses.size() <= 1)
            return ipv4Addresses;

        /**
         * Create a set out of to remove duplicates
         */
        Set<String> unique = new HashSet<>(ipv4Addresses);
        List<String> uniqueIpv4Addresses = new ArrayList<>();

        for (String ipv4 : ipv4Addresses) {

            if (unique.contains(ipv4)) {
                uniqueIpv4Addresses.add(ipv4);
                unique.remove(ipv4);
            }
        }

        return uniqueIpv4Addresses;
    }
}

/**
 * O(n^2) / O(1)
 * As n >>> huge then this is not scalable solution in terms of time, but memory usage is constant
 * Note do not count the result list as memory usage, we can do it in place too.
 */
class RemoveDuplicateIPv4UsingFind {

    public List<String> removeDuplicates(List<String> ipv4Addresses) {
        if (null == ipv4Addresses || ipv4Addresses.size() <= 1)
            return ipv4Addresses;
        final List<String> uniqueIpv4Addresses = new LinkedList<>(ipv4Addresses);
        Collections.copy(uniqueIpv4Addresses, ipv4Addresses);

        //find a string in the list, if found remove it
        for (int i = 0; i < uniqueIpv4Addresses.size(); i++) { //O(n)
            String ipv4 = uniqueIpv4Addresses.get(i);

            for (int j = i + 1; j < uniqueIpv4Addresses.size(); j++) { //O(n)
                if (uniqueIpv4Addresses.get(j).equals(ipv4))
                    uniqueIpv4Addresses.remove(j); //O(1)
            }
        }
        return uniqueIpv4Addresses;


    }


}

/**
 * We can solve this using bit mask.
 * We know that each ipv4 address is divided in 4 portion A.B.C.D
 * and each portion can be from only 0 - 255 at max.
 * <p>
 * WE can have a bit mask for all this portion ( 4 arrays of size 256 ).
 * At any moment all the bitmask arrays says that it has seen the same combination before then this is possibly a duplicate
 * <p>
 * Memory is constant as we'll have at most 256*4*1 = bytes only
 * O(n)/ O(1)
 */
class RemoveDuplicateIPv4UsingBitMask {

    public List<String> removeDuplicates(List<String> ipv4Addresses) {
        if (null == ipv4Addresses || ipv4Addresses.size() <= 1)
            return ipv4Addresses;

        /**
         * We don't count this is solution memory, we can do the below operation in-place too.
         */
        final List<String> uniqueIpv4Addresses = new ArrayList<>(ipv4Addresses.size());

        boolean portionA[] = new boolean[256];
        boolean portionB[] = new boolean[256];
        boolean portionC[] = new boolean[256];
        boolean portionD[] = new boolean[256];

        //O(1)
        for (String ipv4 : ipv4Addresses) {

            String[] portions = ipv4.split(".");

            boolean duplicate = mapAndFindPortions(portions, portionA, portionB, portionC, portionD);
            if (!duplicate)
                uniqueIpv4Addresses.add(ipv4);
        }


        return uniqueIpv4Addresses;
    }

    //O(1)
    private boolean mapAndFindPortions(String[] portions, boolean[] portionA, boolean[] portionB, boolean[] portionC, boolean[] portionD) {


        int p1 = Integer.parseInt(portions[0]);
        int p2 = Integer.parseInt(portions[1]);
        int p3 = Integer.parseInt(portions[2]);
        int p4 = Integer.parseInt(portions[3]);

        if (portionA[p1] && portionB[p2] && portionC[p3] && portionD[p4])
            return true;

        portionA[p1] = true;
        portionB[p2] = true;
        portionC[p3] = true;
        portionD[p4] = true;


        return false;
    }
}
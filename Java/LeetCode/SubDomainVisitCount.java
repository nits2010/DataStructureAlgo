package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-16
 * Description: https://leetcode.com/problems/subdomain-visit-count/
 * A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level,
 * we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com".
 * When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.
 * <p>
 * Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space,
 * followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".
 * <p>
 * We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input,
 * and in any order), that explicitly counts the number of visits to each subdomain.
 * <p>
 * Example 1:
 * Input:
 * ["9001 discuss.leetcode.com"]
 * Output:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * Explanation:
 * We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited.
 * So they will all be visited 9001 times.
 * <p>
 * Example 2:
 * Input:
 * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * Output:
 * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
 * Explanation:
 * We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains,
 * we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 * <p>
 * Notes:
 * <p>
 * The length of cpdomains will not exceed 100.
 * The length of each domain name will not exceed 100.
 * Each address will have either 1 or 2 "." characters.
 * The input count in any count-paired domain will not exceed 10000.
 * The answer output can be returned in any order.
 */
public class SubDomainVisitCount {

    public static void main(String[] args) {
        SubDomainVisitCountUsingHashMap map = new SubDomainVisitCountUsingHashMap();
        System.out.println(map.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"}));

    }
}

/**
 * Runtime: 9 ms, faster than 81.52% of Java online submissions for Subdomain Visit Count.
 * Memory Usage: 37.4 MB, less than 96.97% of Java online submissions for Subdomain Visit Count.
 */
class SubDomainVisitCountUsingHashMap {

    public List<String> subdomainVisits(String[] cpDomains) {

        if (cpDomains == null || cpDomains.length == 0)
            return Collections.EMPTY_LIST;

        final List<String> solution = new ArrayList<>();

        Map<String, Integer> countMap = preProcess(cpDomains);

        for (String domain : countMap.keySet()) {
            int count = countMap.get(domain);
            solution.add(count + " " + domain);
        }


        return solution;

    }

    private Map<String, Integer> preProcess(String[] cpDomains) {
        final Map<String, Integer> countMap = new HashMap<>(cpDomains.length * 3);

        for (String cpDomain : cpDomains) {

            processDomain(cpDomain, countMap);

        }

        return countMap;
    }

    private void processDomain(String cpDomain, Map<String, Integer> countMap) {
        String pair[] = cpDomain.split(" ");
        int count = Integer.parseInt(pair[0]);


        String domain = pair[1];
        int n = domain.length();

        for (int i = n - 1; i >= 0; i--) {

            int j = i;
            while (j >= 0 && domain.charAt(j) != '.')
                j--;

            if (j < 0) {
                countMap.put(domain, countMap.getOrDefault(domain, 0) + count);
                break;
            } else {
                String subDomain = domain.substring(j + 1);
                countMap.put(subDomain, countMap.getOrDefault(subDomain, 0) + count);
            }

            i = j;

        }


    }
}

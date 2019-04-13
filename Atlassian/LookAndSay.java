package Atlassian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/04/19
 * Description:
 */
public class LookAndSay {

    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String start = scanner.next();
        int n = Integer.valueOf(scanner.next());

        String result = start;
        for (int i = 1; i <= n; i++) {
            result = lookAndSay(result);
        }
        System.out.print(result);

    }

    private static String lookAndSay(String start) {
        List<Integer> lookAndSay = lookAndSayBreaks(start);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lookAndSay.size(); ) {
            int ele = lookAndSay.get(i);

            int count = 0;
            while (i < lookAndSay.size() && ele == lookAndSay.get(i)) {
                count++;
                i++;
            }
            result.append(count).append(ele);

        }
        return result.toString();

    }

    private static List<Integer> lookAndSayBreaks(String start) {

        List<Integer> lookAndSay = new ArrayList<>();

        String x = start;

        while (!x.isEmpty()) {
            char last = x.charAt(x.length()-1);

            int r = last - '0';
            lookAndSay.add(r);
            x = x.substring(0, x.length()-1);
        }
        Collections.reverse(lookAndSay);
        return lookAndSay;
    }


}

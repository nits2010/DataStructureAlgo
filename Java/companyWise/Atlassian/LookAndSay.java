package Java.companyWise.Atlassian;

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

    public static void main(String []args) {

        Scanner scanner = new Scanner(System.in);

        String start = scanner.next();
        int n = Integer.valueOf(scanner.next());

        String result = start;
        for (int i = 1; i <= n; i++) {
            result = lookAndSay(result);
        }
        System.out.print(result);

    }

    public static String lookAndSay(String start) {
        List<Integer> lookAndSay = lookAndSayBreaksEasy(start);

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

    public static List<Integer> lookAndSayBreaksEasy(String start) {
        char chars[] = start.toCharArray();
        List<Integer> lookAndSay = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            lookAndSay.add(chars[i] - '0');
        }
        return lookAndSay;
    }


}

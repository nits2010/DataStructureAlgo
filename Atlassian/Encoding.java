package Atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 13/04/19
 * Description:
 */
public class Encoding {
    public static void main(String args[]) throws Exception {


        Map<Integer, Character> baseMap = new HashMap<>();
        baseMap.put(0, '0');
        baseMap.put(1, 'a');
        baseMap.put(2, 't');
        baseMap.put(3, 'l');
        baseMap.put(4, 's');
        baseMap.put(5, 'i');
        baseMap.put(6, 'n');

        Scanner scanner = new Scanner(System.in);

        int base10 = scanner.nextInt();

        if(base10 == 0)
            System.out.println(baseMap.get(0));
        else
            System.out.print(base7(base10, baseMap));


    }

    private static String base7(int base10, Map<Integer, Character> baseMap) {

        int temp = base10;
        StringBuilder base7 = new StringBuilder();

        while (temp != 0) {
            int reminder = temp % 7;
            base7.append(baseMap.get(reminder));
            temp = temp / 7;

        }

        return base7.reverse().toString();
    }
}

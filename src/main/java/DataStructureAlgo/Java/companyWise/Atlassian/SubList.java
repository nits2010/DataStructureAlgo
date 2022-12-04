package DataStructureAlgo.Java.companyWise.Atlassian;

import java.util.Scanner;

/**
 * Author: Nitin Gupta
 * Date: 13/04/19
 * Description:
 */
public class SubList {

    public static void main(String []args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int first[] = new int[Integer.valueOf(scanner.nextInt())];

        for (int i = 0; i < first.length; i++) {
            first[i] = Integer.valueOf(scanner.nextInt());
        }

        int second[] = new int[Integer.valueOf(scanner.nextInt())];
        for (int i = 0; i < second.length; i++) {
            second[i] = Integer.parseInt(scanner.next());
        }

        System.out.print(intersectionIndex(first, second));
    }

    private static int intersectionIndex(int[] first, int[] second) {

        int ele = second[0];

        int i ;
        for (i = 0; i < first.length; i++) {
            if (first[i] == ele)
                break;
        }
        if (i == first.length)
            return -1;

        int breakPoint = i;
        int j = 0;


        if(first.length - (i+1) > second.length)
            return -1;

        while (i < first.length && j < second.length) {
            if (first[i] != second[j])
                break;
            i++;
            j++;
        }

        if (i == first.length && j != second.length)
            return -1;

        return breakPoint;

    }
}
